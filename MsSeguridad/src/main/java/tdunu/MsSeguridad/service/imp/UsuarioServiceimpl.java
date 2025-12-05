package tdunu.MsSeguridad.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdunu.MsSeguridad.model.entity.UsuarioModel;
import tdunu.MsSeguridad.model.request.UsuarioRequest;
import tdunu.MsSeguridad.model.response.UsuarioResponse;
import tdunu.MsSeguridad.repository.UsuarioRepository;
import tdunu.MsSeguridad.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceimpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse guardar(UsuarioRequest request) {

        UsuarioModel user = new UsuarioModel();
        user.setCodUsuario(request.getCodUsuario());
        user.setContrasena(request.getContrasena());
        user.setEstado(1);

        UsuarioModel saved = usuarioRepository.save(user);
        return toResponse(saved);
    }

    @Override
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse obtenerPorId(Long idUsuario) {
        UsuarioModel user = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponse(user);
    }

    @Override
    public UsuarioResponse obtenerPorCodigo(String codUsuario) {
        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponse(user);
    }

    @Override
    public UsuarioResponse editar(String codUsuario, UsuarioRequest request) {

        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Editar campos si vienen
        if (request.getCodUsuario() != null)
            user.setCodUsuario(request.getCodUsuario());

        if (request.getContrasena() != null)
            user.setContrasena(request.getContrasena());

        if (request.getEstado() != null)
            user.setEstado(request.getEstado());

        UsuarioModel updated = usuarioRepository.save(user);
        return toResponse(updated);
    }

    @Override
    public UsuarioResponse eliminarPorCodigo(String codUsuario) {

        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setEstado(0);

        UsuarioModel updated = usuarioRepository.save(user);

        return toResponse(updated);
    }

    @Override
    public UsuarioResponse cambiarEstado(String codUsuario) {

        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setEstado(user.getEstado() == 1 ? 0 : 1);

        UsuarioModel updated = usuarioRepository.save(user);

        return toResponse(updated);
    }

    @Override
    public List<UsuarioResponse> listarActivos() {
        return usuarioRepository.findByEstado(1)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponse> listarDesactivados() {
        return usuarioRepository.findByEstado(0)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private UsuarioResponse toResponse(UsuarioModel model) {
        UsuarioResponse resp = new UsuarioResponse();
        resp.setIdUsuario(model.getIdUsuario());
        resp.setCodUsuario(model.getCodUsuario());
        resp.setEstado(model.getEstado());
        return resp;
    }
}
