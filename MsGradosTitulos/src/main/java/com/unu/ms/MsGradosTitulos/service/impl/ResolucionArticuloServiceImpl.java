package com.unu.ms.MsGradosTitulos.service.impl;

import com.unu.ms.MsGradosTitulos.model.entity.ResolucionArticulo;
import com.unu.ms.MsGradosTitulos.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionArticuloResponse;
import com.unu.ms.MsGradosTitulos.repository.ResolucionArticuloRepository;
import com.unu.ms.MsGradosTitulos.service.ResolucionArticuloService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ResolucionArticuloServiceImpl implements ResolucionArticuloService {

    @Autowired
    private ResolucionArticuloRepository resolucionArticuloRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ResolucionArticuloResponse> listar() {
        return resolucionArticuloRepository.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, ResolucionArticuloResponse.class))
            .toList();
    }

    @Override
    public ResolucionArticuloResponse obtenerPorId(Integer id) {
        return resolucionArticuloRepository.findById(id)
            .map(entity -> modelMapper.map(entity, ResolucionArticuloResponse.class))
            .orElse(null);
    }

    @Override
    public ResolucionArticuloResponse guardar(ResolucionArticuloRequest request) {
        ResolucionArticulo entity = modelMapper.map(request, ResolucionArticulo.class);
        ResolucionArticulo saved = resolucionArticuloRepository.save(entity);
        return modelMapper.map(saved, ResolucionArticuloResponse.class);
    }

    @Override
    public ResolucionArticuloResponse actualizar(ResolucionArticuloRequest request) {
        if (request.getIdArticulo() == null) {
            throw new IllegalArgumentException("El ID del artículo es requerido para actualizar");
        }
        
        ResolucionArticulo entity = modelMapper.map(request, ResolucionArticulo.class);
        ResolucionArticulo updated = resolucionArticuloRepository.save(entity);
        return modelMapper.map(updated, ResolucionArticuloResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        resolucionArticuloRepository.deleteById(id);
    }

    @Override
    public List<ResolucionArticuloResponse> buscarPorResolucion(Integer idResolucion) {
        return resolucionArticuloRepository.findByIdResolucionOrderByNumeroArticuloAsc(idResolucion)
            .stream()
            .map(entity -> modelMapper.map(entity, ResolucionArticuloResponse.class))
            .toList();
    }
}
