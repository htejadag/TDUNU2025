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
    public void eliminarPorCodigo(String codUsuario) {
        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
       
        user.setEstado(0); 
        usuarioRepository.save(user);
    }

    private UsuarioResponse toResponse(UsuarioModel model) {
        UsuarioResponse resp = new UsuarioResponse();
        resp.setIdUsuario(model.getIdUsuario());
        resp.setCodUsuario(model.getCodUsuario());
        resp.setEstado(model.getEstado());
        return resp;
    }

    @Override
    public UsuarioResponse obtenerPorCodigo(String codUsuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorCodigo'");
    }

    @Override
    public UsuarioResponse cambiarEstado(String codUsuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarEstado'");
    }
}
