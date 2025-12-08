package com.example.MsPlanEstudios.service;

import java.util.List;

import com.example.MsPlanEstudios.model.request.PlanEstudiosDetalleRequest;
import com.example.MsPlanEstudios.model.response.PlanEstudiosDetalleResponse;

public interface PlanEstudiosDetalleService {
    List<PlanEstudiosDetalleResponse> listar();
    PlanEstudiosDetalleResponse obtenerPorId(Integer id);
    PlanEstudiosDetalleResponse guardar(PlanEstudiosDetalleRequest plan);
    PlanEstudiosDetalleResponse modificar(Integer id, PlanEstudiosDetalleRequest plan);
    void eliminar(Integer id);
}
