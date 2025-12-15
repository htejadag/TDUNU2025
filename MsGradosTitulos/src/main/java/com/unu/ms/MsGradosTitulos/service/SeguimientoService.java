package com.unu.ms.MsGradosTitulos.service;

import java.util.List;

import com.unu.ms.MsGradosTitulos.model.request.SeguimientoRequest;
import com.unu.ms.MsGradosTitulos.model.response.SeguimientoResponse;

public interface SeguimientoService {

    public List<SeguimientoResponse> listar();
    public SeguimientoResponse obtenerPorId(Integer id);
    public SeguimientoResponse guardar(SeguimientoRequest seguimientoRequest);
    public void eliminar(Integer id);
    public SeguimientoResponse actualizar(Integer id, SeguimientoRequest seguimientoActualizado);
    public boolean existePorId(Integer id);

    public List<SeguimientoResponse> buscarPorEntidad(Integer idEntidadCatalogo, Integer entidadId);

}