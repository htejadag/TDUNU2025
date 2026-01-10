<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.LibroCategoriaRequest;
import tdunu2025.msbiblioteca.model.response.LibroCategoriaResponse;

=======
package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.LibroCategoriaRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroCategoriaResponse;

import java.util.List;

>>>>>>> parent of 852a09b (Actualizacion de ruta)
public interface LibroCategoriaService {

    List<LibroCategoriaResponse> listar();

    LibroCategoriaResponse obtener(Long id);
    
    LibroCategoriaResponse registrar(LibroCategoriaRequest request);
    
    LibroCategoriaResponse actualizar(Long id, LibroCategoriaRequest request);
    
    void eliminar(Long id);
}
