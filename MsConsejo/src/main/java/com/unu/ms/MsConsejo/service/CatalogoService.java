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

    public CatalogoResponse buscarPorCategoriaYValor(String categoria, String valor);
    public List<CatalogoResponse> buscarPorCategoria(String categoria);

}