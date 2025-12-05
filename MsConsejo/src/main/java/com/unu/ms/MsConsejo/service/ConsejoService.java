package com.unu.ms.MsConsejo.service;

import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;

import java.util.List;

public interface ConsejoService {
    
    List<ConsejoResponse> listar();
    
    ConsejoResponse obtenerPorId(Integer id);
    
    ConsejoResponse guardar(ConsejoRequest request);
    
    ConsejoResponse actualizar(ConsejoRequest request);
    
    void eliminar(Integer id);
    
    ConsejoResponse buscarPorNombre(String nombre);
    
    List<ConsejoResponse> buscarPorEstado(Integer idEstado);
}
