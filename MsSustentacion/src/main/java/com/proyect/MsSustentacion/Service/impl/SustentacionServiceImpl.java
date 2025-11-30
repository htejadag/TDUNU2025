package com.proyect.MsSustentacion.Service.impl;

import com.proyect.MsSustentacion.Repository.SustentacionRepository;
import com.proyect.MsSustentacion.Service.SustentacionService;
import com.proyect.MsSustentacion.model.Entity.Sustentacion;
import com.proyect.MsSustentacion.model.Error.ResourceNotFoundException;
import com.proyect.MsSustentacion.model.request.SustentacionRequest;
import com.proyect.MsSustentacion.model.response.SustentacionResponse;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SustentacionServiceImpl implements SustentacionService {

    @Autowired
    private SustentacionRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Short ID_ELIMINADO = 0;
    private static final Short ID_PENDIENTE = 1;

    @Override
    @Transactional(readOnly = true)
    public List<SustentacionResponse> findAll() {
        return repository.findByEstadoResulIdNot(ID_ELIMINADO)
                .stream()
                .map(entity -> modelMapper.map(entity, SustentacionResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SustentacionResponse findById(Long id) {
        return repository.findByIdAndEstadoResulIdNot(id, ID_ELIMINADO)
                .map(entity -> modelMapper.map(entity, SustentacionResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException("Sustentación no encontrada (ID: " + id + ")"));
    }

    @Override
    @Transactional
    public SustentacionResponse save(SustentacionRequest request) {

        Sustentacion sustentacion = modelMapper.map(request, Sustentacion.class);

        if (sustentacion.getId() == null && sustentacion.getEstadoResulId() == null) {
            sustentacion.setEstadoResulId(ID_PENDIENTE);
        }

        Sustentacion saved = repository.save(sustentacion);
        return modelMapper.map(saved, SustentacionResponse.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Sustentacion sustentacion = repository.findByIdAndEstadoResulIdNot(id, ID_ELIMINADO)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. ID no encontrado: " + id));

        sustentacion.setEstadoResulId(ID_ELIMINADO);

        repository.save(sustentacion);

        log.info("Sustentación ID {} eliminada lógicamente (Estado cambiado a {}).", id, ID_ELIMINADO);
    }
}