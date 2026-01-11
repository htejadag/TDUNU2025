<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.PrestamoRequest;
import tdunu2025.msbiblioteca.model.response.PrestamoResponse;
=======
package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.PrestamoRequest;
import TDUNU2025.Msbiblioteca.model.response.PrestamoResponse;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

public interface PrestamoService {
    
    List<PrestamoResponse> listar();
    
    PrestamoResponse obtener(Long id);
    
    PrestamoResponse registrar(PrestamoRequest request);

    PrestamoResponse actualizar (Long id, PrestamoRequest request);
    
    void eliminar(Long id);
}