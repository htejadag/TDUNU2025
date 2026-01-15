package com.pago.service;

import com.pago.model.entity.Catalogo;
import java.util.List;

public interface CatalogoService {
    List<Catalogo> findAll();

    Catalogo findById(Integer id);
}

