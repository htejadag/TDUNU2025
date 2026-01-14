package tdunu2025.msbiblioteca.service;

import tdunu2025.msbiblioteca.model.request.LibroCategoriaRequest;
import tdunu2025.msbiblioteca.model.response.LibroCategoriaResponse;

import java.util.List;

public interface LibroCategoriaService {

    List<LibroCategoriaResponse> listar();

    LibroCategoriaResponse obtener(Long id);
    
    LibroCategoriaResponse registrar(LibroCategoriaRequest request);
    
    LibroCategoriaResponse actualizar(Long id, LibroCategoriaRequest request);
    
    void eliminar(Long id);
}
