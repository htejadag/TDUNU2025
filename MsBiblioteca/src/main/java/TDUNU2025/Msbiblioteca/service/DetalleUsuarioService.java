package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;

public interface DetalleUsuarioService {

    List<DetalleUsuarioResponse> listar();

    DetalleUsuarioResponse obtener(Long idUsuario);

    DetalleUsuarioResponse registrar (DetalleUsuarioRequest request);
    
    DetalleUsuarioResponse actualizar (long idUsuario, DetalleUsuarioRequest request);
    
    void eliminar(Long idUsuario);
}