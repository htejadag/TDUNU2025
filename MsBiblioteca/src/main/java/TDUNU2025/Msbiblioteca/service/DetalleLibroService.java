<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.DetalleLibroRequest;
import tdunu2025.msbiblioteca.model.response.DetalleLibroResponse;

=======
package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;

import java.util.List;

>>>>>>> parent of 852a09b (Actualizacion de ruta)
public interface DetalleLibroService {

    List<DetalleLibroResponse> listar();

    DetalleLibroResponse obtener(Long id);

    DetalleLibroResponse registrar(DetalleLibroRequest request);

    DetalleLibroResponse actualizar (Long id, DetalleLibroRequest request);

    void eliminar(Long id);
}
