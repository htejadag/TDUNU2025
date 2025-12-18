package com.unu.ms.MsConsejo.service;

import java.util.List;

import com.unu.ms.MsConsejo.model.request.CatalogoRequest;
import com.unu.ms.MsConsejo.model.response.CatalogoResponse;

public interface CatalogoService {

    public List<CatalogoResponse> listar();
    public CatalogoResponse obtenerPorId(Integer id);
    public CatalogoResponse guardar(CatalogoRequest catalogoRequest);
    public void eliminar(Integer id);
    public CatalogoResponse actualizar(Integer id, CatalogoRequest catalogoActualizado);
    public boolean existePorId(Integer id);
    
    public List<CatalogoResponse> buscarPorDescripcion(String descripcion);
    public List<CatalogoResponse> buscarPorAbreviatura(String abreviatura);
    public List<CatalogoResponse> buscarPorEstado(String estado);
    public List<CatalogoResponse> buscarHijosPorPadre(Integer idPadre);
    public List<CatalogoResponse> buscarCatalogosRaiz();
    public List<CatalogoResponse> buscarPorPadreYEstado(Integer idPadre, String estado);

}
