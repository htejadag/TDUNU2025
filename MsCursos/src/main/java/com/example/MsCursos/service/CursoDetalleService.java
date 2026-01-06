package com.example.mscursos.service;

import java.util.List;

import com.example.mscursos.model.request.CursoDetalleRequest;
import com.example.mscursos.model.response.CursoDetalleResponse;

public interface CursoDetalleService {

    List<CursoDetalleResponse> listar();

    CursoDetalleResponse obtenerPorId(Integer id);

    CursoDetalleResponse guardar(CursoDetalleRequest request);

    void eliminar(Integer id);

}
