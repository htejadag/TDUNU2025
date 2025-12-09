package com.example.MsGeneral.Service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsGeneral.Model.Entidad.UnidadOrganica;
import com.example.MsGeneral.Model.Request.UnidadOrganicaRequest;
import com.example.MsGeneral.Model.Response.UnidadOrganicaResponse;
import com.example.MsGeneral.Repository.UnidadOrganicaRepositorio;
import com.example.MsGeneral.Service.UnidadOrganicaServicio;


@Service
public class UnidadOrganicaServicioImp implements UnidadOrganicaServicio {

    @Autowired
    private UnidadOrganicaRepositorio unidadOrganicaRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UnidadOrganicaResponse> listar() {
        return unidadOrganicaRepositorio.findAll()
        .stream()
        .map(model->modelMapper.map(model,UnidadOrganicaResponse.class))
        .toList();
    }

    @Override
    public UnidadOrganicaResponse obtenerPorId(String id) {
        return unidadOrganicaRepositorio.findById(id)
        .map(model-> modelMapper.map(model,UnidadOrganicaResponse.class))
        .orElse(null);
    }

    @Override
    public UnidadOrganicaResponse guardar(UnidadOrganicaRequest request) {
        UnidadOrganica model=modelMapper.map(request,UnidadOrganica.class);

        UnidadOrganica saved = unidadOrganicaRepositorio.save(model);

        UnidadOrganicaResponse response= modelMapper.map(saved, UnidadOrganicaResponse.class);

        return response;
    }

    @Override
    public UnidadOrganicaResponse actualizar(String id, UnidadOrganicaRequest request){
        UnidadOrganica modelExistente = unidadOrganicaRepositorio.findById(id).orElse(null);

        modelExistente.setNombre(request.getNombre());
        modelExistente.setSiglas(request.getSiglas());

        UnidadOrganica saved = unidadOrganicaRepositorio.save(modelExistente);

        UnidadOrganicaResponse response = modelMapper.map(saved,UnidadOrganicaResponse.class);
        
        return response;
    }

    @Override
    public void eliminar(String id) {
        unidadOrganicaRepositorio.deleteById(id);
    }
    
}
