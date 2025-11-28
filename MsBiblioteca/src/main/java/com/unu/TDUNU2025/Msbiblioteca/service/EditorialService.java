package com.unu.TDUNU2025.Msbiblioteca.service;

import java.util.List;

import com.unu.TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.EditorialResponse;

public interface EditorialService {

    List<EditorialResponse> listar();

    EditorialResponse obtenerPorId(Long id);

    EditorialResponse guardar(EditorialRequest editorial);

    void eliminar(Long id);

}
