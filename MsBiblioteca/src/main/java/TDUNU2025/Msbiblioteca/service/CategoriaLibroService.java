package tdunu2025.msbiblioteca.service;

import java.util.List;

import tdunu2025.msbiblioteca.model.request.CategoriaLibroRequest;
import tdunu2025.msbiblioteca.model.response.CategoriaLibroResponse;

public interface CategoriaLibroService {

    CategoriaLibroResponse registrar (CategoriaLibroRequest request);

    CategoriaLibroResponse obtener (Long idCategoria);

    List<CategoriaLibroResponse> listar();

    CategoriaLibroResponse actualizar(Long idCategoria, CategoriaLibroRequest request);

    void eliminar(Long idCategoria);
}