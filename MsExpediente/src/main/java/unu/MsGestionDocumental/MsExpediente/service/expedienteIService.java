package unu.MsGestionDocumental.MsExpediente.service;

import unu.MsGestionDocumental.MsExpediente.config.ResourceNotFoundException;
import unu.MsGestionDocumental.MsExpediente.model.entity.ExpedienteEntity;
import unu.MsGestionDocumental.MsExpediente.model.request.requestExpediente;
import unu.MsGestionDocumental.MsExpediente.model.response.responseExpediente;
import unu.MsGestionDocumental.MsExpediente.repository.ExpedienteRepository;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j 
public class expedienteIService implements expedienteService{

    @Autowired
    ExpedienteRepository expedienteRepository;

    @Autowired
    private ModelMapper modelMapper;
    

    @Override
    public List<responseExpediente> listar() {
        return expedienteRepository.findAll()
            .stream()
            .map(model-> modelMapper.map(model, responseExpediente.class))
            .toList();

    }

    @Override
    public responseExpediente obtenerPorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public responseExpediente guardar(requestExpediente producto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}