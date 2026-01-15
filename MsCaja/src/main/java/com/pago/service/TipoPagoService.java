package com.pago.service;

import com.pago.model.entity.TipoPago;
import java.util.List;

public interface TipoPagoService {
    List<TipoPago> findAll();

    TipoPago findById(Integer id);
}

