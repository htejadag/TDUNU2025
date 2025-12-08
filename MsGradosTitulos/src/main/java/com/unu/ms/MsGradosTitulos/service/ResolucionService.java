package com.unu.ms.MsGradosTitulos.service;

import com.unu.ms.MsGradosTitulos.model.request.ResolucionRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionResponse;

import java.util.List;

public interface ResolucionService {
    
    List<ResolucionResponse> listar();
    
    ResolucionResponse obtenerPorId(Integer id);
    
    ResolucionResponse guardar(ResolucionRequest request);
    
    ResolucionResponse actualizar(ResolucionRequest request);
    
    void eliminar(Integer id);
    
    List<ResolucionResponse> buscarPorExpediente(Integer idExpediente);
    
    ResolucionResponse buscarPorNumero(String numeroResolucion);
}
