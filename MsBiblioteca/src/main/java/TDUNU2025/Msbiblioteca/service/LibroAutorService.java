<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;
=======
package TDUNU2025.Msbiblioteca.service;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import TDUNU2025.Msbiblioteca.model.request.LibroAutorRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroAutorResponse;
import java.util.List;

<<<<<<< HEAD
import tdunu2025.msbiblioteca.model.request.LibroAutorRequest;
import tdunu2025.msbiblioteca.model.response.LibroAutorResponse;

=======
>>>>>>> parent of 852a09b (Actualizacion de ruta)
public interface LibroAutorService {

    LibroAutorResponse registrar(LibroAutorRequest request);

    List<LibroAutorResponse> listar();

    LibroAutorResponse obtener(Long id);

    LibroAutorResponse actualizar(Long id, LibroAutorRequest request);

    void eliminar(Long id);
}
