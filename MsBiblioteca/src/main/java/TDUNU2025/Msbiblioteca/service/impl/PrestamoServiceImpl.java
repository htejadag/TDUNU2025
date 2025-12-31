package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.Prestamo;
import TDUNU2025.Msbiblioteca.model.event.PrestamoEvent;
import TDUNU2025.Msbiblioteca.model.request.PrestamoRequest;
import TDUNU2025.Msbiblioteca.model.response.PrestamoResponse;
import TDUNU2025.Msbiblioteca.repository.PrestamoRepository;
import TDUNU2025.Msbiblioteca.service.PrestamoService;
import TDUNU2025.Msbiblioteca.service.kafka.KafkaProducerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final KafkaProducerService kafkaProducerService;
    private final ModelMapper modelMapper;

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
                .orElseThrow(() -> new BusinessException("Préstamo no encontrado"));
        return modelMapper.map(prestamo, PrestamoResponse.class);
    }

    @Override
    @Transactional
    public PrestamoResponse guardarPrestamo(PrestamoRequest request) {

        validarPrestamo(request);

        Prestamo prestamo = modelMapper.map(request, Prestamo.class);

        if (prestamo.getFechaPrestamo() == null) {
            prestamo.setFechaPrestamo(LocalDate.now());
        }

        if (prestamo.getIdEstadoPrestamo() == null) {
            prestamo.setIdEstadoPrestamo(1);
        }

        Prestamo guardado = prestamoRepository.save(prestamo);
        log.info("Préstamo registrado en BD Local con ID: {}", guardado.getIdPrestamo());

        try {
            PrestamoEvent evento = new PrestamoEvent(
                    "PRESTAMO_CREADO",
                    guardado.getIdPrestamo(),
                    guardado.getIdUsuario(),
                    guardado.getIdLibro(),
                    LocalDateTime.now(),
                    "Se ha generado un nuevo préstamo para el usuario " + guardado.getIdUsuario());

            kafkaProducerService.enviarEventoPrestamo(evento);
            log.info("Evento Kafka enviado correctamente para préstamo ID: {}", guardado.getIdPrestamo());

        } catch (Exception e) {

            log.error("Error al enviar evento a Kafka: {}", e.getMessage());
        }

        return modelMapper.map(guardado, PrestamoResponse.class);
    }

    @Override
    @Transactional
    public PrestamoResponse registrarDevolucion(Integer id, String observaciones) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Préstamo no encontrado"));

        if (prestamo.getFechaDevolucion() != null) {
            throw new BusinessException("Este préstamo ya fue devuelto anteriormente");
        }

        prestamo.setFechaDevolucion(LocalDate.now());
        prestamo.setIdEstadoPrestamo(2);
        if (observaciones != null) {
            prestamo.setObservaciones(observaciones);
        }

        Prestamo actualizado = prestamoRepository.save(prestamo);

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

    private void validarPrestamo(PrestamoRequest request) {
        if (request.getIdLibro() == null) {
            throw new BusinessException("Debe indicar el libro a prestar");
        }
        if (request.getIdUsuario() == null) {
            throw new BusinessException("Debe indicar el usuario");
        }
        if (request.getFechaPrestamo() != null && request.getFechaVencimiento() != null) {
            if (request.getFechaVencimiento().isBefore(request.getFechaPrestamo())) {
                throw new BusinessException("La fecha de vencimiento no puede ser anterior a la fecha de préstamo");
            }
        }
    }
}