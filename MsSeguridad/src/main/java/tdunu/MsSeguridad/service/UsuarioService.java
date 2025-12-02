package tdunu.MsSeguridad.service;

import tdunu.MsSeguridad.model.request.UsuarioRequest;
import tdunu.MsSeguridad.model.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse guardar(UsuarioRequest request);
    List<UsuarioResponse> listar();
    UsuarioResponse obtenerPorId(Long idUsuario);       
    void eliminar(Long idUsuario);                      

}

