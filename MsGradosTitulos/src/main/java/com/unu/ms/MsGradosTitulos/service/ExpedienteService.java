package com.unu.ms.MsGradosTitulos.service;

import com.unu.ms.MsGradosTitulos.model.request.ExpedienteRequest;
import com.unu.ms.MsGradosTitulos.model.response.ExpedienteResponse;

import java.util.List;

public interface ExpedienteService {
    
    List<ExpedienteResponse> listar();
    
    ExpedienteResponse obtenerPorId(Integer id);
    
    ExpedienteResponse guardar(ExpedienteRequest request);
    
    ExpedienteResponse actualizar(ExpedienteRequest request);
    
    void eliminar(Integer id);
    
    ExpedienteResponse buscarPorCodigo(String codigo);
    
    List<ExpedienteResponse> buscarPorPersona(Integer idPersona);
}
