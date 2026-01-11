package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;
import TDUNU2025.Msbiblioteca.repository.DetalleUsuarioRepository;
import TDUNU2025.Msbiblioteca.service.DetalleUsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public DetalleUsuarioResponse registrar(DetalleUsuarioRequest request) { // Cambiado a registrar
        DetalleUsuario du = modelMapper.map(request, DetalleUsuario.class);
        DetalleUsuario saved = detalleUsuarioRepository.save(du);
        return modelMapper.map(saved, DetalleUsuarioResponse.class);
    }

    @Override
    public DetalleUsuarioResponse actualizar(long id, DetalleUsuarioRequest request) {
        DetalleUsuario existing = detalleUsuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleUsuario", "id", id));

        modelMapper.map(request, existing);
        existing.setIdDetalleUsuario(id); // Asegurar ID

        DetalleUsuario updated = detalleUsuarioRepository.save(existing);
        return modelMapper.map(updated, DetalleUsuarioResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!detalleUsuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("DetalleUsuario", "id", id);
        }
        detalleUsuarioRepository.deleteById(id);
    }
}