package com.proyect.MsSustentacion.Service.impl;

import com.proyect.MsSustentacion.model.Entity.Sustentacion;
import com.proyect.MsSustentacion.Repository.SustentacionRepository;
import com.proyect.MsSustentacion.Service.SustentacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SustentacionServiceImpl implements SustentacionService {

    @Autowired
    private SustentacionRepository repository;

    @Override
    public List<Sustentacion> findAll() {
        return repository.findAll();
    }

    @Override
    public Sustentacion findById(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Sustentacion save(Sustentacion sustentacion) {
        return repository.save(sustentacion);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
