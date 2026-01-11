package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.AutorRequest;
import TDUNU2025.Msbiblioteca.model.response.AutorResponse;

public interface AutorService {
    
    List<AutorResponse> listar();
    
    AutorResponse obtener(Long id);
    
    AutorResponse registrar(AutorRequest request);
    
    AutorResponse actualizar (Long id, AutorRequest request);

    void eliminar(Long id);
}