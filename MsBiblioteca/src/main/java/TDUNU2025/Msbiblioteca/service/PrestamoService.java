package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.PrestamoRequest;
import tdunu2025.msbiblioteca.model.response.PrestamoResponse;

public interface PrestamoService {
    
    List<PrestamoResponse> listar();
    
    PrestamoResponse obtener(Long id);
    
    List<PrestamoResponse>listarporUsuario(Long idUsuario);
    
    PrestamoResponse registrar(PrestamoRequest request);

    PrestamoResponse actualizar (Long id, PrestamoRequest request);
    
    void registrarDevolucion(Long idPrestamo);

    void eliminar(Long id);
}