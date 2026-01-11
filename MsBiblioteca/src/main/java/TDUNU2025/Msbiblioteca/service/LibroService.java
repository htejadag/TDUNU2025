<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;
=======
package TDUNU2025.Msbiblioteca.service;
>>>>>>> parent of 852a09b (Actualizacion de ruta)


import java.util.List;

<<<<<<< HEAD
import tdunu2025.msbiblioteca.model.request.LibroRequest;
import tdunu2025.msbiblioteca.model.response.LibroResponse;
=======
import TDUNU2025.Msbiblioteca.model.request.LibroRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroResponse;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

public interface LibroService {

    LibroResponse registrar(LibroRequest request);

    List<LibroResponse> listar();

    LibroResponse obtener(Long id);

    LibroResponse actualizar(Long id, LibroRequest request);

    void eliminar(Long id);
}
