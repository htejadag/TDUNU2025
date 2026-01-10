package tdunu2025.msbiblioteca.service;

import java.util.List;
import java.util.Optional;

import tdunu2025.msbiblioteca.model.request.DetalleUsuarioRequest;
import tdunu2025.msbiblioteca.model.response.DetalleUsuarioResponse;

public interface DetalleUsuarioService {

    List<DetalleUsuarioResponse> listar();

    DetalleUsuarioResponse obtener(Long idUsuario);

    DetalleUsuarioResponse registrar (DetalleUsuarioRequest request);
    
    DetalleUsuarioResponse actualizar (long idUsuario, DetalleUsuarioRequest request);
    
    void eliminar(Long idUsuario);
}