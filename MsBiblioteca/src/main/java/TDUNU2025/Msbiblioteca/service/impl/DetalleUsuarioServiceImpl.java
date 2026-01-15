package tdunu2025.msbiblioteca.service.impl;

import tdunu2025.msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.msbiblioteca.model.entity.DetalleUsuario;
import tdunu2025.msbiblioteca.model.request.DetalleUsuarioRequest;
import tdunu2025.msbiblioteca.model.response.DetalleUsuarioResponse;
import tdunu2025.msbiblioteca.repository.DetalleUsuarioRepository;
import tdunu2025.msbiblioteca.service.DetalleUsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleUsuarioServiceImpl implements DetalleUsuarioService {

    private final DetalleUsuarioRepository detalleUsuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DetalleUsuarioResponse> listar() {
        return detalleUsuarioRepository.findAll().stream()
                .map(du -> modelMapper.map(du, DetalleUsuarioResponse.class))
                .toList();
    }

    @Override
    public DetalleUsuarioResponse obtener(Long id) {
        DetalleUsuario du = detalleUsuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleUsuario", "id", id));
        return modelMapper.map(du, DetalleUsuarioResponse.class);
    }

    @Override
    public DetalleUsuarioResponse registrar(DetalleUsuarioRequest request) {
        DetalleUsuario du = modelMapper.map(request, DetalleUsuario.class);
        // Valores iniciales obligatorios
        du.setTotalPrestamos(0);
        du.setTotalMultas(BigDecimal.ZERO);
        du.setFechaUltimoPrestamo(null);
        
        DetalleUsuario saved = detalleUsuarioRepository.save(du);
        return modelMapper.map(saved, DetalleUsuarioResponse.class);
    }

    @Override
    public DetalleUsuarioResponse actualizar(Long id, DetalleUsuarioRequest request) {
        DetalleUsuario du = detalleUsuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleUsuario", "id", id));
        
        modelMapper.map(request, du);
        du.setId(id);

        DetalleUsuario updated = detalleUsuarioRepository.save(du);
        return modelMapper.map(updated, DetalleUsuarioResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!detalleUsuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("DetalleUsuario", "id", id);
        }
        detalleUsuarioRepository.deleteById(id);
    }

    @Override
    public boolean existePorIdUsuario(Long idUsuario) {
        return detalleUsuarioRepository.existsByIdUsuario(idUsuario);
    }

    @Override
    public DetalleUsuarioResponse obtenerPorIdExterno(Long idUsuarioExterno) {
        DetalleUsuario du = detalleUsuarioRepository.findByIdUsuario(idUsuarioExterno)
             .orElseThrow(() -> new ResourceNotFoundException("Usuario no registrado en biblioteca", "idUsuario", idUsuarioExterno));
        return modelMapper.map(du, DetalleUsuarioResponse.class);
    }
}