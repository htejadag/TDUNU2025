package com.example.Comedor.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.ComedorModel;
import com.example.Comedor.model.request.ComedorRequest;
import com.example.Comedor.model.response.ComedorResponse;
import com.example.Comedor.repository.ComedorRepository;
import com.example.Comedor.service.ComedorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ComedorServiceImp implements ComedorService{

    @Autowired
    ComedorRepository comedorRepository;
    

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ComedorResponse> listar() {
        return comedorRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, ComedorResponse.class))
            .toList();
    }

    @Override
    public ComedorResponse obtenerPorId (Integer id) {
        return comedorRepository.findById(id)
            .map(model -> modelMapper.map(model, ComedorResponse.class))
            .orElse(null);
    }

     @Override
    public ComedorResponse guardar(ComedorRequest request) {
        
        ComedorModel model = modelMapper.map(request, ComedorModel.class);

       
        ComedorModel saved = comedorRepository.save(model);

       
        ComedorResponse response = modelMapper.map(saved, ComedorResponse.class);

        return response;
    }

    @Override
    public ComedorResponse modificar(Integer id, ComedorRequest request) {

    ComedorModel model = comedorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un comedor con id: " + id));

    modelMapper.map(request, model);

    ComedorModel actualizado = comedorRepository.save(model);

    return modelMapper.map(actualizado, ComedorResponse.class);
}





    @Override
    public void eliminar(Integer id) {
        comedorRepository.deleteById(id);
    }





    




    
}
