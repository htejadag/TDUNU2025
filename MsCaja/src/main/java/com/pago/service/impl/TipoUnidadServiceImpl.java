package com.pago.service.impl;

import com.pago.model.entity.TipoUnidad;
import com.pago.repository.TipoUnidadRepository;
import com.pago.service.TipoUnidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoUnidadServiceImpl implements TipoUnidadService {

    private final TipoUnidadRepository repository;

    @Override
    @Cacheable(value = "tipo_unidad_all")
    public List<TipoUnidad> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "tipo_unidad_id", key = "#id")
    public TipoUnidad findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
