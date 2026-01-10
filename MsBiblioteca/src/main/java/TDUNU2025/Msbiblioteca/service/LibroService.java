package tdunu2025.msbiblioteca.service;


import java.util.List;

import tdunu2025.msbiblioteca.model.request.LibroRequest;
import tdunu2025.msbiblioteca.model.response.LibroResponse;

public interface LibroService {

    LibroResponse registrar(LibroRequest request);

    List<LibroResponse> listar();

    LibroResponse obtener(Long id);

    LibroResponse actualizar(Long id, LibroRequest request);

    void eliminar(Long id);
}
