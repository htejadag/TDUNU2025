package com.example.MsGeneral.Service;

import java.util.List;

import com.example.MsGeneral.Model.Request.CatalogoRequest;
import com.example.MsGeneral.Model.Response.CatalogoResponse;


public interface CatalogoService {

    List<CatalogoResponse> listar();

    List<CatalogoResponse> listarPorCategoria(String categoria);

    CatalogoResponse obtenerPorId(String id);

    CatalogoResponse guardar(CatalogoRequest request);

    CatalogoResponse actualizar(String id, CatalogoRequest request);

    void cambiarEstado(String id, boolean activo);

    void eliminar(String id);  // opcional según tu negocio

}
