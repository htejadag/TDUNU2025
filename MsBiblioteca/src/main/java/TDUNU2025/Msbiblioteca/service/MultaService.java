<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.MultaRequest;
import tdunu2025.msbiblioteca.model.response.MultaResponse;

=======
package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import TDUNU2025.Msbiblioteca.model.response.MultaResponse;

import java.util.List;

>>>>>>> parent of 852a09b (Actualizacion de ruta)
public interface MultaService {

    List<MultaResponse> listar();

    MultaResponse obtener(Long id);

    MultaResponse registrar(MultaRequest request);

    MultaResponse actualizar(Long id, MultaRequest request);

    void eliminar(Long id);
}
