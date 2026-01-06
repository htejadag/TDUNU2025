package com.example.Comedor.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.MenuDiaModel;
import com.example.Comedor.model.entity.MenuSemanaModel;
import com.example.Comedor.model.request.menuDia.MenuDiaRequest;
import com.example.Comedor.model.request.menuDia.MenuDiaUpdateRequest;
import com.example.Comedor.model.response.MenuDiaResponse;
import com.example.Comedor.repository.MenuDiaRepository;
import com.example.Comedor.repository.MenuSemanaRepository;
import com.example.Comedor.service.MenuDiaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuDiaServiceImp implements MenuDiaService {

    @Autowired
    MenuDiaRepository menuDiaRepository;

    @Autowired
    private MenuSemanaRepository menuSemanaRepository;

    
    @Autowired
    private ModelMapper modelMapper;
    
    
    
    @Override
    public List<MenuDiaResponse> listar() {
        
        return menuDiaRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, MenuDiaResponse.class))
            .toList();

        
    }

    @Override
    public MenuDiaResponse obtenerPorId(Integer id) {
        return menuDiaRepository.findById(id)
            .map(model -> modelMapper.map(model, MenuDiaResponse.class))
            .orElse(null);
        
    }

    @Override
    public MenuDiaResponse guardar(MenuDiaRequest req) {

        MenuSemanaModel semana = menuSemanaRepository.findById(req.getIdMenuSemana())
            .orElseThrow(() -> new RuntimeException("No existe menu semana con id: " + req.getIdMenuSemana()));

        MenuDiaModel model = new MenuDiaModel();

        
        model.setMenuSemana(semana);
        model.setDia(req.getDia());
        model.setActivo(req.isActivo());
        model.setUsuarioCreacion(req.getUsuarioCreacion());
        model.setFechaCreacion(LocalDate.now());
    
        MenuDiaModel saved = menuDiaRepository.save(model);

        MenuDiaResponse response = modelMapper.map(saved, MenuDiaResponse.class);

        return response;
    }


    @Override
    public MenuDiaResponse modificar(Integer id, MenuDiaUpdateRequest menuDiaRequest) {
       
       
        MenuDiaModel model = menuDiaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un menu dia con id: " + id));

        MenuSemanaModel semana = menuSemanaRepository.findById(menuDiaRequest.getIdMenuSemana())
            .orElseThrow(() -> new RuntimeException("No existe un menu semana con id:"+menuDiaRequest.getIdMenuSemana()));

        modelMapper.map(menuDiaRequest, model);

        model.setMenuSemana(semana);
        model.setDia(menuDiaRequest.getDia());
        model.setActivo(menuDiaRequest.isActivo());
        model.setUsuarioCreacion(menuDiaRequest.getUsuarioModificacion());
        model.setFechaModificacion(LocalDate.now());

        MenuDiaModel actualizado = menuDiaRepository.save(model);

        return modelMapper.map(actualizado, MenuDiaResponse.class);
    }

    @Override
    public MenuDiaResponse eliminar(Integer id) {

         MenuDiaModel model = menuDiaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un menu dia con id: " + id));

    model.setActivo(false);

    MenuDiaModel actualizado = menuDiaRepository.save(model);

    return modelMapper.map(actualizado, MenuDiaResponse.class);
        
        
    
    }

}