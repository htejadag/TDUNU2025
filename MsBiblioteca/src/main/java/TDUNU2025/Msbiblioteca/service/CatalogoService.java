package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.CatalogoRequest;
import tdunu2025.msbiblioteca.model.response.CatalogoResponse;

public interface CatalogoService {
    
    List<CatalogoResponse> listar();
   
    CatalogoResponse obtener (Long id);
    
    CatalogoResponse registrar (CatalogoRequest request);
    
    CatalogoResponse actualizar (Long id, CatalogoRequest request);

    void eliminar(Long id);
}