package com.unu.ms.MsSecretariaAcademica.service;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.ExpedienteRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ExpedienteResponse;

public interface ExpedienteService {

    public List<ExpedienteResponse> listar();

    public ExpedienteResponse obtenerPorId(Integer id);

    public ExpedienteResponse guardar(ExpedienteRequest expedienteRequest);

    public void eliminar(Integer id);

    public ExpedienteResponse actualizar(Integer id, ExpedienteRequest expedienteActualizado);

    public boolean existePorId(Integer id);

    public ExpedienteResponse buscarPorCodigo(String codigo);

    public List<ExpedienteResponse> buscarPorPersona(Integer idPersona);

    public List<ExpedienteResponse> buscarPorEstado(Integer idEstado);

    public List<ExpedienteResponse> buscarPorPersonaYEstado(Integer idPersona, Integer idEstado);

}