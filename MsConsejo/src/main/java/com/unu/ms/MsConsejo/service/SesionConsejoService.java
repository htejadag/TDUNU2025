package com.unu.ms.MsConsejo.service;

import java.time.LocalDate;
import java.util.List;

import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.model.response.SesionReportePeriodoResponse;

public interface SesionConsejoService {
    
        public List<SesionConsejoResponse> listar();
        public SesionConsejoResponse obtenerPorId(Integer id);
        public SesionConsejoResponse guardar(SesionConsejoRequest sesionConsejoRequest);
        public void eliminar(Integer id);
        public SesionConsejoResponse actualizar(Integer id, SesionConsejoRequest sesionConsejoActualizado);
        public boolean existePorId(Integer id);
        
        public List<SesionConsejoResponse> buscarPorConsejo(Integer idConsejo);
        public SesionConsejoResponse buscarPorNumeroSesion(String numeroSesion);
        public List<SesionConsejoResponse> buscarPorFecha(LocalDate fechaSesion);
        public List<SesionConsejoResponse> buscarPorTipoSesion(Integer idTipoSesion);
        
        // Nuevos métodos
        public List<SesionConsejoResponse> buscarPorConsejoYTipo(Integer idConsejo, Integer idTipoSesion);
        public List<SesionConsejoResponse> buscarPendientesPorConsejo(Integer idConsejo);
        public SesionConsejoResponse finalizarSesion(Integer idSesion);
        public SesionReportePeriodoResponse obtenerReportePeriodo(LocalDate fechaInicio, LocalDate fechaFin);
        
}