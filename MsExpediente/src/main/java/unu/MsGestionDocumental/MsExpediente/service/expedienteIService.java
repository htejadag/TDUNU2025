package unu.MsGestionDocumental.MsExpediente.service;

import unu.MsGestionDocumental.MsExpediente.config.ResourceNotFoundException;
import unu.MsGestionDocumental.MsExpediente.model.entity.ExpedienteEntity;
import unu.MsGestionDocumental.MsExpediente.model.request.requestExpediente;
import unu.MsGestionDocumental.MsExpediente.model.response.responseExpediente;
import unu.MsGestionDocumental.MsExpediente.repository.ExpedienteRepository;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
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
        return expedienteRepository.findById(id)            
            .map(model -> modelMapper.map(model, responseExpediente.class))
            .orElseThrow(()-> new ResourceNotFoundException("Expediente", "id", id));
    }

    @Override
    public responseExpediente guardar(requestExpediente producto) {
        ExpedienteEntity model = modelMapper.map(producto, ExpedienteEntity.class);
        ExpedienteEntity guadado = expedienteRepository.save(model);
        responseExpediente vista = modelMapper.map(guadado, responseExpediente.class);
        return vista;
    }

    @Override
    public void eliminar(Integer id) {
        ExpedienteEntity repo = expedienteRepository.findAllByIDEliminado(id)
         .orElseThrow(()-> new ResourceNotFoundException("Expediente", "id", id));

         repo.setActivo(false);
         expedienteRepository.save(repo);
    }

}