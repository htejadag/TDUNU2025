package com.unu.ms.MsGradosTitulos.service.impl;

import com.unu.ms.MsGradosTitulos.model.entity.Resolucion;
import com.unu.ms.MsGradosTitulos.model.request.ResolucionRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionResponse;
import com.unu.ms.MsGradosTitulos.repository.ResolucionRepository;
import com.unu.ms.MsGradosTitulos.service.ResolucionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ResolucionServiceImpl implements ResolucionService {

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ResolucionResponse> listar() {
        return resolucionRepository.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, ResolucionResponse.class))
            .toList();
    }

    @Override
    public ResolucionResponse obtenerPorId(Integer id) {
        return resolucionRepository.findById(id)
            .map(entity -> modelMapper.map(entity, ResolucionResponse.class))
            .orElse(null);
    }

    @Override
    public ResolucionResponse guardar(ResolucionRequest request) {
        Resolucion entity = modelMapper.map(request, Resolucion.class);
        Resolucion saved = resolucionRepository.save(entity);
        return modelMapper.map(saved, ResolucionResponse.class);
    }

    @Override
    public ResolucionResponse actualizar(ResolucionRequest request) {
        if (request.getIdResolucion() == null) {
            throw new IllegalArgumentException("El ID de la resolución es requerido para actualizar");
        }
        
        Resolucion entity = modelMapper.map(request, Resolucion.class);
        Resolucion updated = resolucionRepository.save(entity);
        return modelMapper.map(updated, ResolucionResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        resolucionRepository.deleteById(id);
    }

    @Override
    public List<ResolucionResponse> buscarPorExpediente(Integer idExpediente) {
        return resolucionRepository.findByIdExpediente(idExpediente)
            .stream()
            .map(entity -> modelMapper.map(entity, ResolucionResponse.class))
            .toList();
    }

    @Override
    public ResolucionResponse buscarPorNumero(String numeroResolucion) {
        return resolucionRepository.findByNumeroResolucion(numeroResolucion)
            .map(entity -> modelMapper.map(entity, ResolucionResponse.class))
            .orElse(null);
    }
}
