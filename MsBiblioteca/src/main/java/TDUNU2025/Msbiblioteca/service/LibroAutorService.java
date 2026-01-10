package tdunu2025.Msbiblioteca.service;

import java.util.List;

import tdunu2025.Msbiblioteca.model.request.LibroAutorRequest;
import tdunu2025.Msbiblioteca.model.response.LibroAutorResponse;

public interface LibroAutorService {

    LibroAutorResponse registrar(LibroAutorRequest request);

    List<LibroAutorResponse> listar();

    LibroAutorResponse obtener(Long id);

    LibroAutorResponse actualizar(Long id, LibroAutorRequest request);

    void eliminar(Long id);
}
