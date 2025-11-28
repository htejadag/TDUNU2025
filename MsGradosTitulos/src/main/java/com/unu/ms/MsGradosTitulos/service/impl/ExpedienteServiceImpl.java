package com.unu.ms.MsGradosTitulos.service.impl;

import com.unu.ms.MsGradosTitulos.model.entity.Expediente;
import com.unu.ms.MsGradosTitulos.model.request.ExpedienteRequest;
import com.unu.ms.MsGradosTitulos.model.response.ExpedienteResponse;
import com.unu.ms.MsGradosTitulos.repository.ExpedienteRepository;
import com.unu.ms.MsGradosTitulos.service.ExpedienteService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExpedienteServiceImpl implements ExpedienteService {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ExpedienteResponse> listar() {
        return expedienteRepository.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, ExpedienteResponse.class))
            .toList();
    }

    @Override
    public ExpedienteResponse obtenerPorId(Integer id) {
        return expedienteRepository.findById(id)
            .map(entity -> modelMapper.map(entity, ExpedienteResponse.class))
            .orElse(null);
    }

    @Override
    public ExpedienteResponse guardar(ExpedienteRequest request) {
        Expediente entity = modelMapper.map(request, Expediente.class);
        Expediente saved = expedienteRepository.save(entity);
        return modelMapper.map(saved, ExpedienteResponse.class);
    }

    @Override
    public ExpedienteResponse actualizar(ExpedienteRequest request) {
        if (request.getIdExpediente() == null) {
            throw new IllegalArgumentException("El ID del expediente es requerido para actualizar");
        }
        
        Expediente entity = modelMapper.map(request, Expediente.class);
        Expediente updated = expedienteRepository.save(entity);
        return modelMapper.map(updated, ExpedienteResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        expedienteRepository.deleteById(id);
    }

    @Override
    public ExpedienteResponse buscarPorCodigo(String codigo) {
        return expedienteRepository.findByCodigoExpediente(codigo)
            .map(entity -> modelMapper.map(entity, ExpedienteResponse.class))
            .orElse(null);
    }

    @Override
    public List<ExpedienteResponse> buscarPorPersona(Integer idPersona) {
        return expedienteRepository.findByIdPersona(idPersona)
            .stream()
            .map(entity -> modelMapper.map(entity, ExpedienteResponse.class))
            .toList();
    }
}
