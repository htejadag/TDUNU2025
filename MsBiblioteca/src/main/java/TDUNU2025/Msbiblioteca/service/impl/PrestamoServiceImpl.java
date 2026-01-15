package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.config.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.Prestamo;
import TDUNU2025.Msbiblioteca.model.event.PrestamoEvent;
import TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import TDUNU2025.Msbiblioteca.model.request.PrestamoRequest;
import TDUNU2025.Msbiblioteca.model.response.PrestamoResponse;
import TDUNU2025.Msbiblioteca.repository.DetalleUsuarioRepository;
import TDUNU2025.Msbiblioteca.repository.LibroRepository;
import TDUNU2025.Msbiblioteca.repository.PrestamoRepository;
import TDUNU2025.Msbiblioteca.service.MultaService;
import TDUNU2025.Msbiblioteca.service.PrestamoService;
import TDUNU2025.Msbiblioteca.service.kafka.KafkaProducerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final DetalleUsuarioRepository usuarioRepository;
    private final MultaService multaService; 
    private final KafkaProducerService kafkaProducerService;
    private final ModelMapper modelMapper;

    // Constantes de Negocio
    private static final Integer ESTADO_PRESTADO = 1;
    private static final Integer ESTADO_DEVUELTO = 2;
    private static final Double COSTO_POR_DIA_RETRASO = 2.50; 

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoResponse> listarPrestamos() {
        return prestamoRepository.findAll().stream()
                .map(p -> modelMapper.map(p, PrestamoResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoResponse obtenerPrestamoPorId(Integer id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Préstamo no encontrado con ID: " + id));
        return modelMapper.map(prestamo, PrestamoResponse.class);
    }

    @Override
    @Transactional
    public PrestamoResponse guardarPrestamo(PrestamoRequest request) {

        validarRequest(request);

        if (!libroRepository.existsById(request.getIdLibro())) {
            throw new BusinessException("El Libro indicado no existe");
        }
        if (!usuarioRepository.existsByIdUsuario(request.getIdUsuario())) {
            throw new BusinessException("El Usuario indicado no existe en la biblioteca");
        }
        
        if (prestamoRepository.existsByIdLibroAndIdEstadoPrestamo(request.getIdLibro(), ESTADO_PRESTADO)) {
            throw new BusinessException("El libro ya está prestado y no ha sido devuelto");
        }

        Prestamo prestamo = modelMapper.map(request, Prestamo.class);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setIdEstadoPrestamo(ESTADO_PRESTADO);
        
        if (prestamo.getFechaVencimiento() == null) {
            prestamo.setFechaVencimiento(LocalDate.now().plusDays(7));
        }

        Prestamo guardado = prestamoRepository.save(prestamo);
        
        enviarEventoKafka(guardado);
        
        return modelMapper.map(guardado, PrestamoResponse.class);
    }

    @Override
    @Transactional
    public PrestamoResponse registrarDevolucion(Integer id, String observaciones) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Préstamo no encontrado"));

        if (ESTADO_DEVUELTO.equals(prestamo.getIdEstadoPrestamo())) {
            throw new BusinessException("Este préstamo ya fue devuelto el " + prestamo.getFechaDevolucion());
        }

        LocalDate fechaDevolucionReal = LocalDate.now();
        
        if (fechaDevolucionReal.isAfter(prestamo.getFechaVencimiento())) {
            
            long diasRetraso = ChronoUnit.DAYS.between(prestamo.getFechaVencimiento(), fechaDevolucionReal);

            if (diasRetraso > 0) {
                log.info("Mora detectada: {} días de retraso. Generando multa...", diasRetraso);
                
                double montoMulta = diasRetraso * COSTO_POR_DIA_RETRASO;

                MultaRequest multaReq = MultaRequest.builder()
                        .idUsuario(prestamo.getIdUsuario())
                        .idPrestamo(prestamo.getIdPrestamo())
                        .monto(montoMulta)
                        .concepto("Mora automática por " + diasRetraso + " días de retraso.")
                        .idEstadoMulta(1) 
                        .build();

                multaService.registrar(multaReq);

                String notaMulta = " [SISTEMA: MULTA GENERADA POR " + diasRetraso + " DÍAS DE RETRASO]";
                observaciones = (observaciones == null ? "" : observaciones) + notaMulta;
            }
        }

        prestamo.setFechaDevolucion(fechaDevolucionReal);
        prestamo.setIdEstadoPrestamo(ESTADO_DEVUELTO);
        
        if (observaciones != null && !observaciones.isBlank()) {
            prestamo.setObservaciones(observaciones);
        }

        Prestamo actualizado = prestamoRepository.save(prestamo);
        log.info("Préstamo ID {} finalizado correctamente.", id);

        return modelMapper.map(actualizado, PrestamoResponse.class);
    }

    @Override
    @Transactional
    public void eliminarPrestamo(Integer id) {
        if (!prestamoRepository.existsById(id)) {
            throw new BusinessException("Préstamo no encontrado");
        }
        prestamoRepository.deleteById(id);
    }

    private void validarRequest(PrestamoRequest request) {
        if (request.getIdLibro() == null) throw new BusinessException("Debe indicar el libro");
        if (request.getIdUsuario() == null) throw new BusinessException("Debe indicar el usuario");
        if (request.getFechaVencimiento() != null && request.getFechaVencimiento().isBefore(LocalDate.now())) {
            throw new BusinessException("La fecha de vencimiento no puede ser anterior a hoy");
        }
    }

    private void enviarEventoKafka(Prestamo guardado) {
        try {
            PrestamoEvent evento = new PrestamoEvent(
                    "PRESTAMO_CREADO",
                    guardado.getIdPrestamo(),
                    guardado.getIdUsuario(),
                    guardado.getIdLibro().intValue(),
                    LocalDateTime.now(),
                    "Vencimiento: " + guardado.getFechaVencimiento()
            );
            kafkaProducerService.enviarEventoPrestamo(evento);
        } catch (Exception e) {
            log.error("Error al notificar Kafka (No crítico): {}", e.getMessage());
        }
    }
}