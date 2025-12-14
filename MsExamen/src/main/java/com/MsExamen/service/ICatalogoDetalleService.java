package com.MsExamen.service;

import com.MsExamen.dto.CatalogoDetalleDto;
import com.MsExamen.dto.request.CatalogoDetalleRequest;

import java.util.List;

public interface ICatalogoDetalleService {
    List<CatalogoDetalleDto> getAllCatalogoDetalles();

    CatalogoDetalleDto getCatalogoDetalleById(Integer id);

    CatalogoDetalleDto createCatalogoDetalle(CatalogoDetalleRequest catalogoDetalleRequest);

    CatalogoDetalleDto updateCatalogoDetalle(Integer id, CatalogoDetalleRequest catalogoDetalleRequest);

    void deleteCatalogoDetalle(Integer id);
}
