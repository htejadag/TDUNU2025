package com.unu.ms.MsConsejo.service;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;

public interface ConsejoService {
    
        public Iterable<ConsejoModel> listar();
        public ConsejoResponse obtenerPorId(Integer id);
        public ConsejoResponse crear(ConsejoRequest consejo);
        public void eliminar(Integer id);
        public ConsejoResponse actualizar(Integer id, ConsejoRequest consejoActualizado);
        public boolean existePorId(Integer id);
}