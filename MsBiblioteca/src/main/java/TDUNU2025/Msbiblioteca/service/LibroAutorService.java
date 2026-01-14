package tdunu2025.msbiblioteca.service;

import tdunu2025.msbiblioteca.model.request.LibroAutorRequest;
import tdunu2025.msbiblioteca.model.response.LibroAutorResponse;
import java.util.List;

public interface LibroAutorService {

    LibroAutorResponse registrar(LibroAutorRequest request);

    List<LibroAutorResponse> listar();

    LibroAutorResponse obtener(Long id);

    LibroAutorResponse actualizar(Long id, LibroAutorRequest request);

    void eliminar(Long id);
}
