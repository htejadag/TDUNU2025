package tdunu.MsCaja.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsCaja.model.entity.Usuario;
import tdunu.MsCaja.model.request.UsuarioRequest;
import tdunu.MsCaja.model.response.UsuarioResponse;
import tdunu.MsCaja.repository.UsuarioRepository;
import tdunu.MsCaja.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
 

public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioResponse> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(u -> new UsuarioResponse(u.getIdUsuario(), u.getCodigo())).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse getUsuarioById(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario not found"));
        return new UsuarioResponse(usuario.getIdUsuario(), usuario.getCodigo());
    }

    @Override
    public UsuarioResponse createUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setCodigo(usuarioRequest.getCodigo());
        usuario.setPassword(usuarioRequest.getPassword());
        usuario = usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario.getIdUsuario(), usuario.getCodigo());
    }

    @Override
    public UsuarioResponse updateUsuario(int id, UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario not found"));
        usuario.setCodigo(usuarioRequest.getCodigo());
        usuario.setPassword(usuarioRequest.getPassword());
        usuario = usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario.getIdUsuario(), usuario.getCodigo());
    }

    @Override
    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}