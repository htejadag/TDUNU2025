package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.AutorRequest;
import TDUNU2025.Msbiblioteca.model.response.AutorResponse;
import java.util.List;

public interface AutorService {
    
    List<AutorResponse> listarAutores();
    
    AutorResponse obtenerAutorPorId(Integer id);
    
    AutorResponse guardarAutor(AutorRequest request);
    
    AutorResponse actualizarAutor(Integer id, AutorRequest request);
    
    void eliminarAutor(Integer id);
}