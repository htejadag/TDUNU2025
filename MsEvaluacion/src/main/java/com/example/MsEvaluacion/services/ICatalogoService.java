package com.example.MsEvaluacion.services;

import java.util.List;
import com.example.MsEvaluacion.model.entity.CatalogoModel;
import com.example.MsEvaluacion.model.response.CatalogoResponse;

public interface ICatalogoService {
    CatalogoResponse obtenerCatalogoPorCodigo(String codigo);
    CatalogoResponse guardarCatalogo(CatalogoModel catalogo);
    List<CatalogoResponse> listarCatalogos();
}