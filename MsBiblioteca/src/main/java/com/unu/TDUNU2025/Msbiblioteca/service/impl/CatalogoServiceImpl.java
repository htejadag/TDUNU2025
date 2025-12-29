package com.unu.TDUNU2025.Msbiblioteca.service.impl;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Catalogo;
import com.unu.TDUNU2025.Msbiblioteca.repository.CatalogoRepository;
import com.unu.TDUNU2025.Msbiblioteca.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Override
    public List<Catalogo> listarCatalogos() {
        return catalogoRepository.findAll();
    }

    @Override
    public Optional<Catalogo> obtenerCatalogoPorId(Integer id) {
        return catalogoRepository.findById(id);
    }

    @Override
    public Catalogo guardarCatalogo(Catalogo catalogo) {
        return catalogoRepository.save(catalogo);
    }

    @Override
    public void eliminarCatalogo(Integer id) {
        catalogoRepository.deleteById(id);
    }
}