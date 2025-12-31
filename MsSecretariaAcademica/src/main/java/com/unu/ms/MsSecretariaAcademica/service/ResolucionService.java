package com.unu.ms.MsSecretariaAcademica.service;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionResponse;

public interface ResolucionService {

    public List<ResolucionResponse> listar();

    public ResolucionResponse obtenerPorId(Integer id);

    public ResolucionResponse guardar(ResolucionRequest resolucionRequest);

    public void eliminar(Integer id);

    public ResolucionResponse actualizar(Integer id, ResolucionRequest resolucionActualizado);

    public boolean existePorId(Integer id);

    public List<ResolucionResponse> buscarPorExpediente(Integer idExpediente);

    public ResolucionResponse buscarPorNumero(String numeroResolucion);

    public List<ResolucionResponse> buscarPorTipo(Integer idTipoResolucion);

    public List<ResolucionResponse> buscarPorEstado(Integer idEstado);

    public List<ResolucionResponse> buscarPorTipoYEstado(Integer idTipoResolucion, Integer idEstado);

    public List<ResolucionResponse> buscarPorAprobadoEnSesion(Integer aprobadoEnSesion);

}