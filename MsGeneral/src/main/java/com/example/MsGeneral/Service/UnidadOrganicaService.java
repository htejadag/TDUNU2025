package com.example.MsGeneral.Service;

import java.util.List;

import com.example.MsGeneral.Model.Request.UnidadOrganicaRequest;
import com.example.MsGeneral.Model.Response.UnidadOrganicaResponse;

public interface UnidadOrganicaService {
    
    List<UnidadOrganicaResponse> listar();

    UnidadOrganicaResponse obtenerPorId(String id);

    UnidadOrganicaResponse guardar(UnidadOrganicaRequest unidadorganica);

    UnidadOrganicaResponse actualizar(String id, UnidadOrganicaRequest unidadOrganica);

    void eliminar(String id);

}
