package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.model.request.LibroRequest;
import TDUNU2025.model.LibroResponse;
import java.util.List;

public interface LibroService {

    LibroResponse registrar(LibroRequest request);

    List<LibroResponse> listar();

    LibroResponse obtener(Long id);

    LibroResponse actualizar(Long id, LibroRequest request);

    void eliminar(Long id);
}
