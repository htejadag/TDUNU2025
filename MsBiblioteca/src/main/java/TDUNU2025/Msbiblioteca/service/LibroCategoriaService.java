package tdunu2025.Msbiblioteca.service;

import java.util.List;

import tdunu2025.Msbiblioteca.model.request.LibroCategoriaRequest;
import tdunu2025.Msbiblioteca.model.response.LibroCategoriaResponse;

public interface LibroCategoriaService {

    List<LibroCategoriaResponse> listar();

    LibroCategoriaResponse obtener(Long id);
    
    LibroCategoriaResponse registrar(LibroCategoriaRequest request);
    
    LibroCategoriaResponse actualizar(Long id, LibroCategoriaRequest request);
    
    void eliminar(Long id);
}
