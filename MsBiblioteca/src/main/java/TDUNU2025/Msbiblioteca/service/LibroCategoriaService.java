package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.LibroCategoriaRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroCategoriaResponse;

import java.util.List;

public interface LibroCategoriaService {

    List<LibroCategoriaResponse> listar();
    LibroCategoriaResponse obtener(Long id);
    LibroCategoriaResponse registrar(LibroCategoriaRequest request);
    LibroCategoriaResponse actualizar(Long id, LibroCategoriaRequest request);
    void eliminar(Long id);
}
