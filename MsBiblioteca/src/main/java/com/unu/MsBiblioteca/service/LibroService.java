package com.unu.MsBiblioteca.service;

import com.unu.MsBiblioteca.model.request.LibroRequest;
import com.unu.MsBiblioteca.model.LibroResponse;
import java.util.List;

public interface LibroService {

    LibroResponse registrar(LibroRequest request);

    List<LibroResponse> listar();

    LibroResponse obtener(Long id);

    LibroResponse actualizar(Long id, LibroRequest request);

    void eliminar(Long id);
}
