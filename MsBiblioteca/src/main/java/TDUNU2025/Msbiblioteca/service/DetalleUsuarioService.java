package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;

import java.util.List;

public interface DetalleUsuarioService {
    
    List<DetalleUsuarioResponse> listarTodo();

    DetalleUsuarioResponse obtenerPorId(Integer idUsuario);

    DetalleUsuarioResponse guardar(DetalleUsuarioRequest request);
    
    DetalleUsuarioResponse actualizar(Integer id, DetalleUsuarioRequest request);
    
    void eliminar(Integer idUsuario);
}