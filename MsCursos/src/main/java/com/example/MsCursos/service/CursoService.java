package com.example.MsCursos.service;

import java.util.List;

import com.example.MsCursos.model.request.CursoRequest;
import com.example.MsCursos.model.response.CursoResponse;

public interface CursoService {
    
    List<CursoResponse> listar();

    CursoResponse obtenerPorId(Integer id);

    CursoResponse guardar(CursoRequest request);

    void eliminar(Integer id);
}
