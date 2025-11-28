package com.unu.ms.MsConsejo.service;

import java.util.List;

import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;

public interface MiembroConsejoService {
    
        public List<MiembroConsejoResponse> listar();
        public MiembroConsejoResponse obtenerPorId(Integer id);
        public MiembroConsejoResponse crear(MiembroConsejoRequest miembroConsejoRequest);
        public void eliminar(Integer id);
        public MiembroConsejoResponse actualizar(Integer id, MiembroConsejoRequest  miembroConsejoActualizado);
        public boolean existePorId(Integer id);
        
}