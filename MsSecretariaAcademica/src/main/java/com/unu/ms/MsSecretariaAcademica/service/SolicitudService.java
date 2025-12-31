package com.unu.ms.MsSecretariaAcademica.service;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.SolicitudRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.SolicitudResponse;

public interface SolicitudService {

    public List<SolicitudResponse> listar();

    public SolicitudResponse obtenerPorId(Integer id);

    public SolicitudResponse guardar(SolicitudRequest solicitudRequest);

    public void eliminar(Integer id);

    public SolicitudResponse actualizar(Integer id, SolicitudRequest solicitudActualizado);

    public boolean existePorId(Integer id);

    public List<SolicitudResponse> obtenerPorPersona(Integer idPersona);

    public List<SolicitudResponse> obtenerPorEstado(Integer idEstado);

    public List<SolicitudResponse> obtenerPorTipo(Integer idTipoSolicitud);

    public List<SolicitudResponse> obtenerPorPersonaYEstado(Integer idPersona, Integer idEstado);

    public List<SolicitudResponse> obtenerPorTipoYEstado(Integer idTipoSolicitud, Integer idEstado);

    public List<SolicitudResponse> obtenerPorFechaRango(String fechaInicio, String fechaFin);

}