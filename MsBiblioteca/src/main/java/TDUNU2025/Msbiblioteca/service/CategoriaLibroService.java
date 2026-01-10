package tdunu2025.Msbiblioteca.service;

import java.util.List;

import tdunu2025.Msbiblioteca.model.request.CategoriaLibroRequest;
import tdunu2025.Msbiblioteca.model.response.CategoriaLibroResponse;

public interface CategoriaLibroService {

    CategoriaLibroResponse registrar (CategoriaLibroRequest request);

    CategoriaLibroResponse obtener (Long idCategoria);

    List<CategoriaLibroResponse> listar();

    CategoriaLibroResponse actualizar(Long idCategoria, CategoriaLibroRequest request);

    void eliminar(Long idCategoria);
}