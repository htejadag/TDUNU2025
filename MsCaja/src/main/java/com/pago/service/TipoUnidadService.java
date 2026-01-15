package com.pago.service;

import com.pago.model.entity.TipoUnidad;
import java.util.List;

public interface TipoUnidadService {
    List<TipoUnidad> findAll();

    TipoUnidad findById(Integer id);
}

