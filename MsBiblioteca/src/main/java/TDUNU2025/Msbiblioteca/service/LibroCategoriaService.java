package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.LibroCategoriaRequest;
import tdunu2025.msbiblioteca.model.response.LibroCategoriaResponse;

public interface LibroCategoriaService {

    List<LibroCategoriaResponse> listar();

    LibroCategoriaResponse obtener(Long id);
    
    LibroCategoriaResponse registrar(LibroCategoriaRequest request);
    
    LibroCategoriaResponse actualizar(Long id, LibroCategoriaRequest request);
    
    void eliminar(Long id);
}
