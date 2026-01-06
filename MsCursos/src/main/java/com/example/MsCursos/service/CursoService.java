package com.example.mscursos.service;

import java.util.List;

import com.example.mscursos.model.request.CursoRequest;
import com.example.mscursos.model.response.CursoResponse;

public interface CursoService {

    List<CursoResponse> listar();

    CursoResponse obtenerPorId(Integer id);

    CursoResponse guardar(CursoRequest request);

    void eliminar(Integer id);

    List<CursoResponse> listarPorCarrera(Integer idCarrera);

    List<CursoResponse> listarPorEstado(Boolean estado);

    List<CursoResponse> listarPorCarreraYEstado(Integer idCarrera, Boolean estado);

    void upsertDesdeKafka(CursoRequest req);
}
