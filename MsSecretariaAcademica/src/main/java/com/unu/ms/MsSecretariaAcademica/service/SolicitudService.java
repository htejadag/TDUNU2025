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

}