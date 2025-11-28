package com.example.Comedor.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.example.Comedor.model.entity.ComedorModel;
import com.example.Comedor.model.entity.MenuSemanaModel;
// import com.example.Comedor.model.request.ComedorRequest;
import com.example.Comedor.model.request.MenuSemanaRequest;
// import com.example.Comedor.model.response.ComedorResponse;
import com.example.Comedor.model.response.MenuSemanaResponse;
// import com.example.Comedor.repository.ComedorRepository;
import com.example.Comedor.repository.MenuSemanaRepository;
import com.example.Comedor.service.MenuSemanaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuSemanaServiceImp implements MenuSemanaService{

    @Autowired
    MenuSemanaRepository menuSemanaRepository;
    

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MenuSemanaResponse> listar() {
        return menuSemanaRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, MenuSemanaResponse.class))
            .toList();
    }

    @Override
    public MenuSemanaResponse obtenerPorId (Integer id) {
        return menuSemanaRepository.findById(id)
            .map(model -> modelMapper.map(model, MenuSemanaResponse.class))
            .orElse(null);
    }

     @Override
    public MenuSemanaResponse guardar(MenuSemanaRequest request) {
        
        MenuSemanaModel model = modelMapper.map(request, MenuSemanaModel.class);

       
        MenuSemanaModel saved = menuSemanaRepository.save(model);

       
        MenuSemanaResponse response = modelMapper.map(saved, MenuSemanaResponse.class);

        return response;
    }

    @Override
    public MenuSemanaResponse modificar(Integer id, MenuSemanaRequest request) {

    MenuSemanaModel model = menuSemanaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un comedor con id: " + id));

    modelMapper.map(request, model);

    MenuSemanaModel actualizado = menuSemanaRepository.save(model);

    return modelMapper.map(actualizado, MenuSemanaResponse.class);
    }


    @Override
    public void eliminar(Integer id) {
        menuSemanaRepository.deleteById(id);
    }

    
}
