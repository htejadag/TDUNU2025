package tdunu2025.Msbiblioteca.service;

import java.util.List;

import tdunu2025.Msbiblioteca.model.request.PrestamoRequest;
import tdunu2025.Msbiblioteca.model.response.PrestamoResponse;

public interface PrestamoService {
    
    List<PrestamoResponse> listar();
    
    PrestamoResponse obtener(Long id);
    
    PrestamoResponse registrar(PrestamoRequest request);

    PrestamoResponse actualizar (Long id, PrestamoRequest request);
    
    void eliminar(Long id);
}