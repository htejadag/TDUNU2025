package com.example.Comedor.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.PlatoModel;
import com.example.Comedor.model.request.PlatoRequest;
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

       
        PlatoModel saved = platoRepository.save(model);

       
        PlatoResponse response = modelMapper.map(saved, PlatoResponse.class);

        return response;
        
    }

    @Override
    public PlatoResponse modificar(Integer id, PlatoRequest platoRequest) {
        PlatoModel model = platoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un comedor con id: " + id));

        modelMapper.map(platoRequest, model);

        PlatoModel actualizado = platoRepository.save(model);

        return modelMapper.map(actualizado, PlatoResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        platoRepository.deleteById(id);
        
    }


    
}
