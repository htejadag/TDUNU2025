package com.unu.ms.MsGradosTitulos.service;

import java.util.List;

import com.unu.ms.MsGradosTitulos.model.request.ResolucionRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionResponse;

public interface ResolucionService {

    public List<ResolucionResponse> listar();
    public ResolucionResponse obtenerPorId(Integer id);
    public ResolucionResponse crear(ResolucionRequest resolucionRequest);
    public void eliminar(Integer id);
    public ResolucionResponse actualizar(Integer id, ResolucionRequest resolucionActualizado);
    public boolean existePorId(Integer id);

}