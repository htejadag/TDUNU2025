package com.example.MsCursos.service;

import java.util.List;

import com.example.MsCursos.model.response.CursoResponse;

public interface CursoService {
    
    List<CursoResponse> listar();

    CursoResponse obtenerPorId(Integer id);

    CursoResponse guardar(CursoResponse producto);

    void eliminar(Integer id);
}
