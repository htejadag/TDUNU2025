package com.pago.service;

import com.pago.model.entity.TipoDocumento;
import java.util.List;

public interface TipoDocumentoService {
    List<TipoDocumento> findAll();

    TipoDocumento findById(Integer id);
}

