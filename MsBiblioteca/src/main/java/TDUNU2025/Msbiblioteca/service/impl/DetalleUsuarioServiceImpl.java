package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.BusinessException;
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
    public DetalleUsuarioResponse obtenerPorId(Integer idUsuario) {
        DetalleUsuario detalle = repository.findById(idUsuario)
                .orElseThrow(() -> new BusinessException("Detalles del usuario no encontrados con ID: " + idUsuario));
        
        return modelMapper.map(detalle, DetalleUsuarioResponse.class);
    }

    @Override
    @Transactional
    public DetalleUsuarioResponse guardar(DetalleUsuarioRequest request) {
        
        validarDatosDetalle(request);

        log.info("Intentando guardar detalles para el usuario ID: {}", request.getIdUsuario());

        DetalleUsuario detalle = modelMapper.map(request, DetalleUsuario.class);
        // Si la relación es 1 a 1 con usuario y el ID no es autogenerado, asegúrate de setearlo si es necesario
        // detalle.setIdUsuario(request.getIdUsuario()); 

        DetalleUsuario guardado = repository.save(detalle);

        log.info("Detalles de usuario guardados correctamente con ID: {}", guardado.getIdUsuario());
        return modelMapper.map(guardado, DetalleUsuarioResponse.class);
    }

    @Override
    @Transactional
    public DetalleUsuarioResponse actualizar(Integer id, DetalleUsuarioRequest request) {
        DetalleUsuario detalle = repository.findById(id)
                .orElseThrow(() -> new BusinessException("No se encontraron detalles para actualizar con ID: " + id));

        validarDatosDetalle(request);

        // Mapeamos los nuevos datos sobre la entidad existente
        modelMapper.map(request, detalle);
        
        // Aseguramos que el ID se mantenga (por seguridad)
        detalle.setIdUsuario(id);

        DetalleUsuario actualizado = repository.save(detalle);
        log.info("Detalles de usuario actualizados correctamente con ID: {}", id);

        return modelMapper.map(actualizado, DetalleUsuarioResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Integer idUsuario) {
        if (!repository.existsById(idUsuario)) {
            throw new BusinessException("No se puede eliminar: Detalles de usuario no encontrados");
        }
        repository.deleteById(idUsuario);
        log.warn("Detalles del usuario con ID {} eliminados del sistema", idUsuario);
    }

    // Método privado para validaciones de negocio
    private void validarDatosDetalle(DetalleUsuarioRequest request) {
        if (request.getIdUsuario() == null || request.getIdUsuario() <= 0) {
            throw new BusinessException("El ID del usuario es obligatorio y debe ser válido");
        }

        // Ejemplo de validación (ajusta 'getDireccion' a tus campos reales)
        if (request.getDireccion() != null && request.getDireccion().trim().isEmpty()) {
            throw new BusinessException("La dirección no puede estar vacía si se envía");
        }
        
        // Ejemplo de validación (ajusta 'getTelefono' a tus campos reales)
        if (request.getTelefono() != null && request.getTelefono().length() < 7) {
             throw new BusinessException("El número de teléfono parece inválido");
        }
    }
}