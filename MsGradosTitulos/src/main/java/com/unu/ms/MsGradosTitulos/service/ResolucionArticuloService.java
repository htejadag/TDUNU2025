package com.unu.ms.MsGradosTitulos.service;

import java.util.List;

import com.unu.ms.MsGradosTitulos.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionArticuloResponse;

public interface ResolucionArticuloService {

    public List<ResolucionArticuloResponse> listar();
    public ResolucionArticuloResponse obtenerPorId(Integer id);
    public ResolucionArticuloResponse guardar(ResolucionArticuloRequest resolucionArticuloRequest);
    public void eliminar(Integer id);
    public ResolucionArticuloResponse actualizar(Integer id, ResolucionArticuloRequest resolucionArticuloActualizado);
    public boolean existePorId(Integer id);

    public List<ResolucionArticuloResponse> buscarPorResolucion(Integer idResolucion);

}