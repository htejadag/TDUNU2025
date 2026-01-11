package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.config.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;
import TDUNU2025.Msbiblioteca.repository.DetalleUsuarioRepository;
import TDUNU2025.Msbiblioteca.service.DetalleUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DetalleUsuarioServiceImpl implements DetalleUsuarioService {

    private final DetalleUsuarioRepository repository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DetalleUsuarioResponse> listarTodo() {
        return repository.findAll()
                .stream()
                .map(detalle -> modelMapper.map(detalle, DetalleUsuarioResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleUsuarioResponse obtenerPorIdUsuario(Integer idUsuario) {
        // Buscamos por el ID del Usuario (campo de negocio), no por PK
        DetalleUsuario detalle = repository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new BusinessException("El usuario con ID " + idUsuario + " no tiene historial en biblioteca."));
        
        return modelMapper.map(detalle, DetalleUsuarioResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorIdUsuario(Integer idUsuario) {
        return repository.existsByIdUsuario(idUsuario);
    }

    @Override
    @Transactional
    public DetalleUsuarioResponse guardar(DetalleUsuarioRequest request) {
        validarDatosDetalle(request);

        if (repository.existsByIdUsuario(request.getIdUsuario())) {
            throw new BusinessException("Ya existe un historial para el usuario ID: " + request.getIdUsuario());
        }

        log.info("Creando historial manualmente para usuario ID: {}", request.getIdUsuario());
        DetalleUsuario detalle = modelMapper.map(request, DetalleUsuario.class);
        
        // Inicializar valores por defecto si vienen nulos
        if (detalle.getTotalPrestamos() == null) detalle.setTotalPrestamos(0);
        if (detalle.getTotalMultas() == null) detalle.setTotalMultas(0);

        DetalleUsuario guardado = repository.save(detalle);
        return modelMapper.map(guardado, DetalleUsuarioResponse.class);
    }

    // Sobrecarga para uso interno (KAFKA)
    @Override
    @Transactional
    public void guardar(DetalleUsuario entity) {
        if (entity.getIdUsuario() == null) {
            log.error("Intento de guardar DetalleUsuario sin ID de usuario");
            return;
        }
        repository.save(entity);
        log.info("Historial creado internamente (Kafka) para usuario ID: {}", entity.getIdUsuario());
    }

    @Override
    @Transactional
    public DetalleUsuarioResponse actualizar(Integer idUsuario, DetalleUsuarioRequest request) {
        // Buscamos por ID Usuario para asegurar que actualizamos el correcto
        DetalleUsuario detalle = repository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new BusinessException("No se encontraron detalles para el usuario ID: " + idUsuario));

        // Solo permitimos actualizar ciertos campos lógicos si fuera manual
        // Nota: Generalmente esto lo actualiza el sistema de Préstamos, no el usuario manual.
        if (request.getTotalPrestamos() != null) detalle.setTotalPrestamos(request.getTotalPrestamos());
        if (request.getTotalMultas() != null) detalle.setTotalMultas(request.getTotalMultas());
        if (request.getFechaUltimoPrestamo() != null) detalle.setFechaUltimoPrestamo(request.getFechaUltimoPrestamo());

        DetalleUsuario actualizado = repository.save(detalle);
        log.info("Detalles actualizados para usuario ID: {}", idUsuario);

        return modelMapper.map(actualizado, DetalleUsuarioResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Integer idUsuario) {
        DetalleUsuario detalle = repository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new BusinessException("No se encontraron detalles para eliminar"));
        
        repository.delete(detalle);
        log.warn("Historial eliminado para usuario ID: {}", idUsuario);
    }

    private void validarDatosDetalle(DetalleUsuarioRequest request) {
        if (request.getIdUsuario() == null || request.getIdUsuario() <= 0) {
            throw new BusinessException("El ID del usuario es obligatorio");
        }
        // Eliminada validación de dirección y teléfono porque NO existen en esta entidad
    }
}