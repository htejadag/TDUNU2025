package tdunu.MsCaja.service;

import tdunu.MsCaja.model.entity.Usuario;
import tdunu.MsCaja.model.request.UsuarioRequest;
import tdunu.MsCaja.model.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    UsuarioResponse createUsuario(UsuarioRequest usuarioRequest);
    UsuarioResponse updateUsuario(int id, UsuarioRequest usuarioRequest);
    void deleteUsuario(int id);
    UsuarioResponse getUsuarioById(int id);
    List<UsuarioResponse> getAllUsuarios();
}