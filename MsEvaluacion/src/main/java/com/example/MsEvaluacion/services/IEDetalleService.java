package com.example.MsEvaluacion.services;

import java.util.List;

import com.example.MsEvaluacion.model.entity.EvaluacionDetalleModel;
import com.example.MsEvaluacion.model.request.EDetalleResquest;
import com.example.MsEvaluacion.model.response.EDetalleResponse;

public interface IEDetalleService {
    
    List<EDetalleResponse> listar();
    
    List<EvaluacionDetalleModel> listarPorEvaluacion(String evaluacion);

    EDetalleResponse obtenerPorId(String id);

    EDetalleResponse guardar(EDetalleResquest eDetalleRequest);  
    
    void eliminar(String id);
}
