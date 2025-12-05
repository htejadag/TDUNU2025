package com.unu.ms.MsConsejo.service;

import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;

import java.util.List;

public interface MiembroConsejoService {
    
    List<MiembroConsejoResponse> listar();
    
    MiembroConsejoResponse obtenerPorId(Integer id);
    
    MiembroConsejoResponse guardar(MiembroConsejoRequest request);
    
    MiembroConsejoResponse actualizar(MiembroConsejoRequest request);
    
    void eliminar(Integer id);
    
    List<MiembroConsejoResponse> buscarPorConsejo(Integer idConsejo);
    
    List<MiembroConsejoResponse> buscarPorPersona(Integer idPersona);
    
    List<MiembroConsejoResponse> buscarPorCargo(Integer idCargo);
}
