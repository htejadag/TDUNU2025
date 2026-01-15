package com.pago.service.impl;

import com.pago.model.entity.TipoConcepto;
import com.pago.repository.TipoConceptoRepository;
import com.pago.service.TipoConceptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoConceptoServiceImpl implements TipoConceptoService {

    private final TipoConceptoRepository repository;

    @Override
    @Cacheable(value = "tipo_concepto_all")
    public List<TipoConcepto> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "tipo_concepto_id", key = "#id")
    public TipoConcepto findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
