package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.config.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.Multa;
import TDUNU2025.Msbiblioteca.model.entity.Prestamo;
import TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import TDUNU2025.Msbiblioteca.model.response.MultaResponse;
import TDUNU2025.Msbiblioteca.repository.DetalleUsuarioRepository; 
import TDUNU2025.Msbiblioteca.repository.MultaRepository;
import TDUNU2025.Msbiblioteca.repository.PrestamoRepository;       
import TDUNU2025.Msbiblioteca.service.MultaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MultaServiceImpl implements MultaService {

    private final MultaRepository multaRepository;
    private final PrestamoRepository prestamoRepository;      // Inyectado para validar
    private final DetalleUsuarioRepository usuarioRepository; // Inyectado para validar
    private final ModelMapper modelMapper;

    // Constantes
    private static final Integer ESTADO_PENDIENTE = 1;
    private static final Integer ESTADO_PAGADA = 2;

    @Override
    @Transactional(readOnly = true)
    public List<MultaResponse> listar() {
        return multaRepository.findAll().stream()
                .map(multa -> modelMapper.map(multa, MultaResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MultaResponse obtener(Integer id) {
        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Multa no encontrada con ID: " + id));

        return modelMapper.map(multa, MultaResponse.class);
    }

    @Override
    @Transactional
    public MultaResponse registrar(MultaRequest request) {
        validarDatosMulta(request);

        // 1. Validar que el usuario exista (Integridad)
        if (!usuarioRepository.existsByIdUsuario(request.getIdUsuario())) {
            throw new BusinessException("El usuario indicado no existe en el registro de biblioteca");
        }

        // 2. Validar que el préstamo exista
        Prestamo prestamo = prestamoRepository.findById(request.getIdPrestamo())
                .orElseThrow(() -> new BusinessException("El préstamo indicado no existe"));

        // 3. Validar consistencia: ¿Este préstamo pertenece a este usuario?
        if (!prestamo.getIdUsuario().equals(request.getIdUsuario())) {
            throw new BusinessException("El préstamo ID " + request.getIdPrestamo() + 
                                        " no pertenece al usuario ID " + request.getIdUsuario());
        }

        Multa entity = modelMapper.map(request, Multa.class);
        entity.setFechaGeneracion(LocalDate.now());
        entity.setFechaPago(null);
        entity.setIdEstadoMulta(ESTADO_PENDIENTE);
        
        if (entity.getDiasRetraso() == null) {
            entity.setDiasRetraso(0);
        }

        Multa saved = multaRepository.save(entity);
        log.info("Multa generada. Usuario: {} | Préstamo: {} | Monto: {}", 
                 saved.getIdUsuario(), saved.getIdPrestamo(), saved.getMonto());

        return modelMapper.map(saved, MultaResponse.class);
    }

    @Override
    @Transactional
    public MultaResponse actualizar(Integer id, MultaRequest request) {
        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Multa no encontrada"));

        // Nota: Generalmente las multas no se editan manualmente, pero mantenemos la lógica
        if (request.getMonto() != null && request.getMonto() >= 0) {
            multa.setMonto(request.getMonto());
        }
        if (request.getConcepto() != null) {
            multa.setConcepto(request.getConcepto());
        }

        Multa updated = multaRepository.save(multa);
        return modelMapper.map(updated, MultaResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!multaRepository.existsById(id)) {
            throw new BusinessException("La multa no existe");
        }
        multaRepository.deleteById(id);
        log.warn("Multa eliminada ID: {}", id);
    }

    @Override
    @Transactional
    public MultaResponse registrarPago(Integer id) {
        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Multa no encontrada"));

        if (ESTADO_PAGADA.equals(multa.getIdEstadoMulta())) {
            throw new BusinessException("Esta multa ya fue pagada el: " + multa.getFechaPago());
        }

        multa.setIdEstadoMulta(ESTADO_PAGADA);
        multa.setFechaPago(LocalDate.now());

        Multa pagada = multaRepository.save(multa);
        log.info("💰 Multa ID {} pagada exitosamente.", id);

        // OJO: Aquí podrías llamar a 'usuarioRepository' para actualizar el historial de multas del usuario
        // pero lo dejaremos simple por ahora.

        return modelMapper.map(pagada, MultaResponse.class);
    }

    private void validarDatosMulta(MultaRequest request) {
        if (request.getMonto() == null || request.getMonto() < 0) {
            throw new BusinessException("El monto de la multa debe ser válido y positivo");
        }
        if (request.getIdUsuario() == null) {
            throw new BusinessException("Debe indicar el usuario a multar");
        }
        if (request.getIdPrestamo() == null) {
            throw new BusinessException("Debe asociar la multa a un préstamo");
        }
    }
}