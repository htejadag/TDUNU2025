package com.example.MsCursos.service;

import java.util.List;

import com.example.MsCursos.model.request.CatalogoRequest;
import com.example.MsCursos.model.response.CatalogoResponse;

public interface CatalogoService {
    List<CatalogoResponse> listar();

    CatalogoResponse obtenerPorId(Integer id);

    List<CatalogoResponse> listarPorCategoria(String categoria);

    List<CatalogoResponse> listarPorEstado(Boolean estado);

    List<CatalogoResponse> listarPorCategoriaYEstado(String categoria, Boolean estado);

    List<CatalogoResponse> listarHijos(String idPadre);

    CatalogoResponse guardar(CatalogoRequest request);

    CatalogoResponse actualizar(Integer id, CatalogoRequest request);

    void cambiarEstado(Integer id, Boolean estado);

    void eliminar(Integer id);
}
