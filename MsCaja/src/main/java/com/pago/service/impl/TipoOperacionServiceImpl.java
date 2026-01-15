package com.pago.service.impl;

import com.pago.model.entity.TipoOperacion;
import com.pago.repository.TipoOperacionRepository;
import com.pago.service.TipoOperacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoOperacionServiceImpl implements TipoOperacionService {

    private final TipoOperacionRepository repository;

    @Override
    @Cacheable(value = "tipo_operacion_all")
    public List<TipoOperacion> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "tipo_operacion_id", key = "#id")
    public TipoOperacion findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
