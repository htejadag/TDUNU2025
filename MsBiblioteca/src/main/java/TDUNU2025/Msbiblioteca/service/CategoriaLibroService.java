package TDUNU2025.Msbiblioteca.service;

import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.CategoriaLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.CategoriaLibroResponse;

public interface CategoriaLibroService {

    CategoriaLibroResponse save(CategoriaLibroRequest request);

    CategoriaLibroResponse findById(Long idCategoria);

    List<CategoriaLibroResponse> findAll();

    CategoriaLibroResponse update(Long idCategoria, CategoriaLibroRequest request);

    void delete(Long idCategoria);
}