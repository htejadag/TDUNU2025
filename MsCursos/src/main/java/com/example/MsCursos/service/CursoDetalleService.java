package com.example.MsCursos.service;

import java.util.List;

import com.example.MsCursos.model.response.CursoDetalleResponse;

public interface CursoDetalleService {

    List<CursoDetalleResponse> listar();

    CursoDetalleResponse obtenerPorId(Integer id);

    CursoDetalleResponse guardar(CursoDetalleResponse producto);

    void eliminar(Integer id);

}
