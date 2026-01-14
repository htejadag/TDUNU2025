package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.AutorRequest;
import tdunu2025.msbiblioteca.model.response.AutorResponse;

public interface AutorService {
    
    List<AutorResponse> listar();
    
    AutorResponse obtener(Long id);
    
    AutorResponse registrar(AutorRequest request);
    
    AutorResponse actualizar (Long id, AutorRequest request);

    void eliminar(Long id);
}