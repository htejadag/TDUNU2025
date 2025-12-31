package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.CatalogoRequest;
import TDUNU2025.Msbiblioteca.model.response.CatalogoResponse;
import java.util.List;

public interface CatalogoService {
    

    List<CatalogoResponse> listarCatalogos();
    
    CatalogoResponse obtenerCatalogoPorId(Integer id);
    
    CatalogoResponse guardarCatalogo(CatalogoRequest request);

    CatalogoResponse actualizarCatalogo(Integer id, CatalogoRequest request);
    
    void eliminarCatalogo(Integer id);
}