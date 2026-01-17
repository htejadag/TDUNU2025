package com.example.MsEvaluacion.services;

import java.util.List;

import com.example.MsEvaluacion.model.request.EvaluacionRequest;
import com.example.MsEvaluacion.model.response.EvaluacionResponse;

public interface IEvaluacionService {

    List<EvaluacionResponse> listar();

    EvaluacionResponse obtenerPorId(String id);

    EvaluacionResponse guardar(EvaluacionRequest evaluacionRequest);

    EvaluacionResponse actualizar(String id, EvaluacionRequest evaluacionRequest);

    void eliminar(String id);

    void calcularPromedioFinal(String evaluacionId);
}
