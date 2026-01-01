package TDUNU2025.Msbiblioteca.service;

import java.util.List;
import java.util.Optional;

import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;

public interface DetalleUsuarioService {

    List<DetalleUsuarioResponse> listar();

    DetalleUsuarioResponse obtener(Long idUsuario);

    DetalleUsuarioResponse registrar (DetalleUsuarioRequest request);
    
    DetalleUsuarioRequest actualizar (long idUsuario, DetalleUsuarioRequest request);
    
    void eliminar(Long idUsuario);
}