package com.pago.service.impl;

import com.pago.model.entity.TipoDocumento;
import com.pago.repository.TipoDocumentoRepository;
import com.pago.service.TipoDocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipoDocumentoRepository repository;

    @Override
    @Cacheable(value = "tipo_documento_all")
    public List<TipoDocumento> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "tipo_documento_id", key = "#id")
    public TipoDocumento findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
