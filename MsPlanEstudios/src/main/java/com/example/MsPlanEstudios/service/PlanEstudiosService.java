package com.example.MsPlanEstudios.service;

import java.util.List;

import com.example.MsPlanEstudios.model.request.PlanEstudiosRequest;
import com.example.MsPlanEstudios.model.response.PlanEstudiosResponse;

public interface PlanEstudiosService {
    List<PlanEstudiosResponse> listar();
    PlanEstudiosResponse obtenerPorId(Integer id);
    PlanEstudiosResponse guardar(PlanEstudiosRequest plan);
    PlanEstudiosResponse modificar(Integer id, PlanEstudiosRequest plan);
    void eliminar(Integer id);
}
