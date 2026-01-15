package com.pago.service.impl;

import com.pago.model.entity.TipoPago;
import com.pago.repository.TipoPagoRepository;
import com.pago.service.TipoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoPagoServiceImpl implements TipoPagoService {

    private final TipoPagoRepository repository;

    @Override
    @Cacheable(value = "tipo_pago_all")
    public List<TipoPago> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "tipo_pago_id", key = "#id")
    public TipoPago findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
