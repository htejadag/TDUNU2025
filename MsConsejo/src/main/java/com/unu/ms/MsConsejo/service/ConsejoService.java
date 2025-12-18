package com.unu.ms.MsConsejo.service;

import java.util.List;

import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoDetalleCompletoResponse;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;

public interface ConsejoService {

        public List<ConsejoResponse> listar();
        public ConsejoResponse obtenerPorId(Integer id);
        public ConsejoResponse guardar(ConsejoRequest consejoRequest);
        public void eliminar(Integer id);
        public ConsejoResponse actualizar(Integer id, ConsejoRequest consejoActualizado);
        public boolean existePorId(Integer id);
        
        public ConsejoResponse buscarPorNombre(String nombre);
        public List<ConsejoResponse> buscarPorEstado(Integer idEstado);
        
        // Nuevos métodos
        public List<ConsejoResponse> listarVigentes();
        public ConsejoDetalleCompletoResponse obtenerDetalleCompleto(Integer id);

}