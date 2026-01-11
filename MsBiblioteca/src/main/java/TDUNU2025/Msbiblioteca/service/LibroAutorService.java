package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.LibroAutorRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroAutorResponse;
import java.util.List;

public interface LibroAutorService {

    LibroAutorResponse registrar(LibroAutorRequest request);

    List<LibroAutorResponse> listar();

    LibroAutorResponse obtener(Long id);

    LibroAutorResponse actualizar(Long id, LibroAutorRequest request);

    void eliminar(Long id);
}
