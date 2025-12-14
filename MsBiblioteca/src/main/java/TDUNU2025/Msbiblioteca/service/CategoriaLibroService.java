package com.unu.TDUNU2025.Msbiblioteca.service;

import com.unu.TDUNU2025.Msbiblioteca.model.request.CategoriaLibroRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.CategoriaLibroResponse;

import java.util.List;

public interface CategoriaLibroService {

    CategoriaLibroResponse save(CategoriaLibroRequest request);

    CategoriaLibroResponse findById(Long idCategoria);

    List<CategoriaLibroResponse> findAll();

    CategoriaLibroResponse update(Long idCategoria, CategoriaLibroRequest request);

    void delete(Long idCategoria);
}