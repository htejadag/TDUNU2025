package com.example.MsCuenta.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.model.response.CatalogoResponse;
import com.example.MsCuenta.repository.CatalogoRepository;

@Service
public class CatalogoCacheService {

    @Autowired
    CatalogoRepository catalogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Cacheable("catalogos")
    public List<CatalogoResponse> listarCacheado() {
        return catalogoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, CatalogoResponse.class))
                .toList();
    }
    
}
