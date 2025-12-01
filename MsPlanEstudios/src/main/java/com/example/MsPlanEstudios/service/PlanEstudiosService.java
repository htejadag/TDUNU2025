package com.example.MsPlanEstudios.service;

import java.util.List;

import com.example.MsPlanEstudios.model.PlanEstudiosModel;

public interface PlanEstudiosService {
    List<PlanEstudiosModel> listar();
    PlanEstudiosModel obtenerPorId(Integer id);
    PlanEstudiosModel guardar(PlanEstudiosModel producto);
    void eliminar(Integer id);
}
