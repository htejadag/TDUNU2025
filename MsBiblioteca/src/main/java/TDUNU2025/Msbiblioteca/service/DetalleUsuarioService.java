package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;

import java.util.List;

public interface DetalleUsuarioService {
    
    List<DetalleUsuarioResponse> listarTodo();

    DetalleUsuarioResponse obtenerPorIdUsuario(Integer idUsuario);

    DetalleUsuarioResponse guardar(DetalleUsuarioRequest request);

    void guardar(DetalleUsuario entity);

    boolean existePorIdUsuario(Integer idUsuario);
    
    DetalleUsuarioResponse actualizar(Integer idUsuario, DetalleUsuarioRequest request);
    
    void eliminar(Integer idUsuario);
}