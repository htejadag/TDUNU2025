package com.unu.ms.MsConsejo.service;

import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;

import java.time.LocalDate;
import java.util.List;

public interface SesionConsejoService {
    
    List<SesionConsejoResponse> listar();
    
    SesionConsejoResponse obtenerPorId(Integer id);
    
    SesionConsejoResponse guardar(SesionConsejoRequest request);
    
    SesionConsejoResponse actualizar(SesionConsejoRequest request);
    
    void eliminar(Integer id);
    
    List<SesionConsejoResponse> buscarPorConsejo(Integer idConsejo);
    
    SesionConsejoResponse buscarPorNumeroSesion(String numeroSesion);
    
    List<SesionConsejoResponse> buscarPorFecha(LocalDate fechaSesion);
    
    List<SesionConsejoResponse> buscarPorTipoSesion(Integer idTipoSesion);
}
