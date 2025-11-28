package com.unu.ms.MsConsejo.service;

import java.util.List;

import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;

public interface SesionConsejoService {
    
        public List<SesionConsejoResponse> listar();
        public SesionConsejoResponse obtenerPorId(Integer id);
        public SesionConsejoResponse crear(SesionConsejoRequest sesionConsejoRequest);
        public void eliminar(Integer id);
        public SesionConsejoResponse actualizar(Integer id, SesionConsejoRequest sesionConsejoActualizado);
        public boolean existePorId(Integer id);
        
}