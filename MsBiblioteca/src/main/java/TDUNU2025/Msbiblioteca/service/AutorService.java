<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.AutorRequest;
import tdunu2025.msbiblioteca.model.response.AutorResponse;
=======
package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.AutorRequest;
import TDUNU2025.Msbiblioteca.model.response.AutorResponse;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

public interface AutorService {
    
    List<AutorResponse> listar();
    
    AutorResponse obtener(Long id);
    
    AutorResponse registrar(AutorRequest request);
    
    AutorResponse actualizar (Long id, AutorRequest request);

    void eliminar(Long id);
}