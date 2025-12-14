package com.MsExamen.service;

import com.MsExamen.dto.CatalogoDto;
import com.MsExamen.dto.request.CatalogoRequest;

import java.util.List;

public interface ICatalogoService {
    List<CatalogoDto> getAllCatalogos();

    CatalogoDto getCatalogoById(Integer id);

    CatalogoDto createCatalogo(CatalogoRequest catalogoRequest);

    CatalogoDto updateCatalogo(Integer id, CatalogoRequest catalogoRequest);

    void deleteCatalogo(Integer id);
}
