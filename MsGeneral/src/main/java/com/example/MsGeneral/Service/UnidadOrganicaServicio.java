package com.example.MsGeneral.Service;

import java.util.List;

import com.example.MsGeneral.Model.Request.UnidadOrganicaRequest;
import com.example.MsGeneral.Model.Response.UnidadOrganicaResponse;

public interface UnidadOrganicaServicio {
    List<UnidadOrganicaResponse> listar();

    UnidadOrganicaResponse obtenerPorId(Integer id);

    UnidadOrganicaResponse guardar(UnidadOrganicaRequest unidadorganica);

    void eliminar(Integer id);

}
