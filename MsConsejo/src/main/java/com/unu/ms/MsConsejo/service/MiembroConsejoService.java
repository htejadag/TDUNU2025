package com.unu.ms.MsConsejo.service;

import java.util.List;

import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;

public interface MiembroConsejoService {
    
        public List<MiembroConsejoResponse> listar();
        public MiembroConsejoResponse obtenerPorId(Integer id);
        public MiembroConsejoResponse guardar(MiembroConsejoRequest miembroConsejoRequest);
        public void eliminar(Integer id);
        public MiembroConsejoResponse actualizar(Integer id, MiembroConsejoRequest  miembroConsejoActualizado);
        public boolean existePorId(Integer id);

        public List<MiembroConsejoResponse> buscarPorConsejo(Integer idConsejo);
        public List<MiembroConsejoResponse> buscarPorPersona(Integer idPersona);
        public List<MiembroConsejoResponse> buscarPorCargo(Integer idCargo);
        
        // Nuevo método - miembros activos por cargo
        public List<MiembroConsejoResponse> listarActivosPorCargo(Integer idCargo);
        
}