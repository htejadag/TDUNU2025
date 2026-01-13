package com.example.Comedor.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.PlatoModel;
import com.example.Comedor.model.request.plato.PlatoRequest;
import com.example.Comedor.model.request.plato.PlatoUpdateRequest;
import com.example.Comedor.model.response.PlatoResponse;
import com.example.Comedor.repository.PlatoRepository;
import com.example.Comedor.service.PlatoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlatoServiceImp implements PlatoService {
    
    @Autowired
    PlatoRepository platoRepository;
    

    @Autowired
    private ModelMapper modelMapper;
    
    
    
    @Override
    public List<PlatoResponse> listar() {
        
        return platoRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, PlatoResponse.class))
            .toList();

        
    }

    @Override
    public PlatoResponse obtenerPorId(Integer id) {
        return platoRepository.findById(id)
            .map(model -> modelMapper.map(model, PlatoResponse.class))
            .orElse(null);
        
    }

    @Override
    public PlatoResponse guardar(PlatoRequest platoRequest) {

        PlatoModel model = modelMapper.map(platoRequest, PlatoModel.class);

        model.setNombre(platoRequest.getNombre());
        model.setDescripcion(platoRequest.getDescripcion());
        model.setImagenUrl(platoRequest.getImagenUrl());
        model.setCalorias(platoRequest.getCalorias());
        model.setTipo(platoRequest.getTipo());
        model.setActivo(platoRequest.isActivo());
        model.setUsuarioCreacion(platoRequest.getUsuarioCreacion());
        model.setFechaCreacion(LocalDate.now());


       
        PlatoModel saved = platoRepository.save(model);

       
        return modelMapper.map(saved, PlatoResponse.class);

        
        
    }

    @Override
    public PlatoResponse modificar(Integer id, PlatoUpdateRequest platoRequest) {
        PlatoModel model = platoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un plato con id: " + id));

        modelMapper.map(platoRequest, model);

        model.setNombre(platoRequest.getNombre());
        model.setDescripcion(platoRequest.getDescripcion());
        model.setImagenUrl(platoRequest.getImagenUrl());
        model.setCalorias(platoRequest.getCalorias());
        model.setTipo(platoRequest.getTipo());
        model.setActivo(platoRequest.isActivo());
        model.setUsuarioModificacion(platoRequest.getUsuarioModificacion());
        model.setFechaModificacion(LocalDate.now());

        PlatoModel actualizado = platoRepository.save(model);

        return modelMapper.map(actualizado, PlatoResponse.class);
    }

    @Override
    public PlatoResponse eliminar(Integer id) {
        PlatoModel model = platoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un plato con id: " + id));
    
    model.setActivo(false);

    PlatoModel actualizado = platoRepository.save(model);

    return modelMapper.map(actualizado, PlatoResponse.class);
        
    }


    
}
