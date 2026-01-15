package com.pago.service;

import com.pago.model.entity.TipoConcepto;
import java.util.List;

public interface TipoConceptoService {
    List<TipoConcepto> findAll();

    TipoConcepto findById(Integer id);
}

