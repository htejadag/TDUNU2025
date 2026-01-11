<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.CatalogoRequest;
import tdunu2025.msbiblioteca.model.response.CatalogoResponse;
=======
package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.CatalogoRequest;
import TDUNU2025.Msbiblioteca.model.response.CatalogoResponse;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

public interface CatalogoService {
    
    List<CatalogoResponse> listar();
   
    CatalogoResponse obtener (Long id);
    
    CatalogoResponse registrar (CatalogoRequest request);
    
    CatalogoResponse actualizar (Long id, CatalogoRequest request);

    void eliminar(Long id);
}