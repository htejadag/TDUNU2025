package tdunu.MsSeguridad.service;

import tdunu.MsSeguridad.model.request.UsuarioRequest;
import tdunu.MsSeguridad.model.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse guardar(UsuarioRequest request);
    List<UsuarioResponse> listar();
    UsuarioResponse obtenerPorId(Long idUsuario);
    UsuarioResponse obtenerPorCodigo(String codUsuario);
    UsuarioResponse editar(String codUsuario, UsuarioRequest request);
    UsuarioResponse eliminarPorCodigo(String codUsuario);
    UsuarioResponse cambiarEstado(String codUsuario);
    List<UsuarioResponse> listarActivos();
    List<UsuarioResponse> listarDesactivados();
}


