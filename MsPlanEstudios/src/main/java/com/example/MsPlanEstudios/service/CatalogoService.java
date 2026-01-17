package com.example.MsPlanEstudios.service;

import java.util.List;

import com.example.MsPlanEstudios.model.entity.CatalogoModel;
import com.example.MsPlanEstudios.model.request.CatalogoRequest;
import com.example.MsPlanEstudios.model.response.CatalogoResponse;

public interface CatalogoService {
    CatalogoResponse guardar(CatalogoRequest request);
    List<CatalogoResponse> listarPorCategoria(Integer categoria);
    CatalogoModel obtenerActivo(Integer id);
}
