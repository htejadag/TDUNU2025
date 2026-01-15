package com.pago.service.impl;

import com.pago.model.entity.Catalogo;
import com.pago.repository.CatalogoRepository;
import com.pago.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;

    @Override
    @Cacheable(value = "catalogo_all")
    public List<Catalogo> findAll() {
        return catalogoRepository.findAll();
    }

    @Override
    @Cacheable(value = "catalogo_id", key = "#id")
    public Catalogo findById(Integer id) {
        return catalogoRepository.findById(id).orElse(null);
    }
}
