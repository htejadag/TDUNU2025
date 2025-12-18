package com.unu.ms.MsSecretariaAcademica.service;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.CatalogoRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.CatalogoResponse;

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