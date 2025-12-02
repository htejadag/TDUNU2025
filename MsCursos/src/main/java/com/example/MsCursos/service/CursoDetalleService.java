package com.example.MsCursos.service;

import java.util.List;

import com.example.MsCursos.model.request.CursoDetalleRequest;
import com.example.MsCursos.model.response.CursoDetalleResponse;

public interface CursoDetalleService {

    List<CursoDetalleResponse> listar();

    CursoDetalleResponse obtenerPorId(Integer id);

    CursoDetalleResponse guardar(CursoDetalleRequest request);

    void eliminar(Integer id);

}
