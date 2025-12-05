package com.unu.ms.MsConsejo.service.impl;

import com.unu.ms.MsConsejo.model.entity.Consejo;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.service.ConsejoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConsejoServiceImpl implements ConsejoService {

    @Autowired
    private ConsejoRepository consejoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ConsejoResponse> listar() {
        return consejoRepository.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, ConsejoResponse.class))
            .toList();
    }

    @Override
    public ConsejoResponse obtenerPorId(Integer id) {
        return consejoRepository.findById(id)
            .map(entity -> modelMapper.map(entity, ConsejoResponse.class))
            .orElse(null);
    }

    @Override
    public ConsejoResponse guardar(ConsejoRequest request) {
        Consejo entity = modelMapper.map(request, Consejo.class);
        Consejo saved = consejoRepository.save(entity);
        return modelMapper.map(saved, ConsejoResponse.class);
    }

    @Override
    public ConsejoResponse actualizar(ConsejoRequest request) {
        if (request.getIdConsejo() == null) {
            throw new IllegalArgumentException("El ID del consejo es requerido para actualizar");
        }
        
        Consejo entity = modelMapper.map(request, Consejo.class);
        Consejo updated = consejoRepository.save(entity);
        return modelMapper.map(updated, ConsejoResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        consejoRepository.deleteById(id);
    }

    @Override
    public ConsejoResponse buscarPorNombre(String nombre) {
        return consejoRepository.findByNombre(nombre)
            .map(entity -> modelMapper.map(entity, ConsejoResponse.class))
            .orElse(null);
    }

    @Override
    public List<ConsejoResponse> buscarPorEstado(Integer idEstado) {
        return consejoRepository.findByIdEstado(idEstado)
            .stream()
            .map(entity -> modelMapper.map(entity, ConsejoResponse.class))
            .toList();
    }
}
