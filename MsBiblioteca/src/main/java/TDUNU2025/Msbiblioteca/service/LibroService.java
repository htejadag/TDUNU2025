package com.unu.TDUNU2025.Msbiblioteca.service;

import com.unu.TDUNU2025.Msbiblioteca.model.request.LibroRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.LibroResponse;
import java.util.List;

public interface LibroService {

    LibroResponse registrar(LibroRequest request);

    List<LibroResponse> listar();

    LibroResponse obtener(Long id);

    LibroResponse actualizar(Long id, LibroRequest request);

    void eliminar(Long id);
}
