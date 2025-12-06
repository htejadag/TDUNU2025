package com.unu.ms.MsGradosTitulos.service;

import com.unu.ms.MsGradosTitulos.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionArticuloResponse;

import java.util.List;

public interface ResolucionArticuloService {
    
    List<ResolucionArticuloResponse> listar();
    
    ResolucionArticuloResponse obtenerPorId(Integer id);
    
    ResolucionArticuloResponse guardar(ResolucionArticuloRequest request);
    
    ResolucionArticuloResponse actualizar(ResolucionArticuloRequest request);
    
    void eliminar(Integer id);
    
    List<ResolucionArticuloResponse> buscarPorResolucion(Integer idResolucion);
}
