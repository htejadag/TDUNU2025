package com.unu.ms.MsGradosTitulos.service;

import com.unu.ms.MsGradosTitulos.model.request.SeguimientoRequest;
import com.unu.ms.MsGradosTitulos.model.response.SeguimientoResponse;

import java.util.List;

public interface SeguimientoService {
    
    List<SeguimientoResponse> listar();
    
    SeguimientoResponse obtenerPorId(Integer id);
    
    SeguimientoResponse guardar(SeguimientoRequest request);
    
    SeguimientoResponse actualizar(SeguimientoRequest request);
    
    void eliminar(Integer id);
    
    List<SeguimientoResponse> buscarPorEntidad(Integer idEntidadCatalogo, Integer entidadId);
}
