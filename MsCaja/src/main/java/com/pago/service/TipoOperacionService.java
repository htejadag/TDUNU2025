package com.pago.service;

import com.pago.model.entity.TipoOperacion;
import java.util.List;

public interface TipoOperacionService {
    List<TipoOperacion> findAll();

    TipoOperacion findById(Integer id);
}

