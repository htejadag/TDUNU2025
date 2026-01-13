package com.unu.ms.MsConsejo.service;

import java.util.List;

import com.unu.ms.MsConsejo.model.request.AsistenciaSesionRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaSesionResponse;

public interface AsistenciaSesionService {

        public List<AsistenciaSesionResponse> listar();
        public AsistenciaSesionResponse obtenerPorId(Integer id);
        public AsistenciaSesionResponse guardar(AsistenciaSesionRequest asistenciaSesionRequest);
        public void eliminar(Integer id);
        public AsistenciaSesionResponse actualizar(Integer id, AsistenciaSesionRequest asistenciaSesionActualizado);
        public boolean existePorId(Integer id);
        
        public List<AsistenciaSesionResponse> buscarPorSesion(Integer idSesion);
        public List<AsistenciaSesionResponse> buscarPorMiembro(Integer idMiembro);
        public List<AsistenciaSesionResponse> buscarPorEstadoAsistencia(Integer idEstadoAsistencia);

}