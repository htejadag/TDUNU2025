package com.unu.ms.MsConsejo.service;

import com.unu.ms.MsConsejo.model.request.AsistenciaConsejeroRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaConsejeroResponse;

import java.util.List;

public interface AsistenciaConsejeroService {
    
    List<AsistenciaConsejeroResponse> listar();
    
    AsistenciaConsejeroResponse obtenerPorId(Integer id);
    
    AsistenciaConsejeroResponse guardar(AsistenciaConsejeroRequest request);
    
    AsistenciaConsejeroResponse actualizar(AsistenciaConsejeroRequest request);
    
    void eliminar(Integer id);
    
    List<AsistenciaConsejeroResponse> buscarPorSesion(Integer idSesion);
    
    List<AsistenciaConsejeroResponse> buscarPorMiembro(Integer idMiembro);
    
    List<AsistenciaConsejeroResponse> buscarPorEstadoAsistencia(Integer idEstadoAsistencia);
}
