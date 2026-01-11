package TDUNU2025.Msbiblioteca.service;


import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.LibroRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroResponse;

public interface LibroService {

    LibroResponse registrar(LibroRequest request);

    List<LibroResponse> listar();

    LibroResponse obtener(Long id);

    LibroResponse actualizar(Long id, LibroRequest request);

    void eliminar(Long id);
}
