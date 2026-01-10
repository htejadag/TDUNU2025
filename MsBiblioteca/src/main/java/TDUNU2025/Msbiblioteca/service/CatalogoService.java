package tdunu2025.Msbiblioteca.service;

import java.util.List;

import tdunu2025.Msbiblioteca.model.request.CatalogoRequest;
import tdunu2025.Msbiblioteca.model.response.CatalogoResponse;

public interface CatalogoService {
    
    List<CatalogoResponse> listar();
   
    CatalogoResponse obtener (Long id);
    
    CatalogoResponse registrar (CatalogoRequest request);
    
    CatalogoResponse actualizar (Long id, CatalogoRequest request);

    void eliminar(Long id);
}