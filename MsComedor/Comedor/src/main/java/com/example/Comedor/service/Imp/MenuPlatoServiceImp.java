package com.example.Comedor.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.MenuDiaModel;
import com.example.Comedor.model.entity.MenuPlatoModel;
import com.example.Comedor.model.entity.PlatoModel;
import com.example.Comedor.model.request.MenuPlatoRequest;
import com.example.Comedor.model.response.MenuPlatoResponse;
import com.example.Comedor.repository.MenuDiaRepository;
import com.example.Comedor.repository.MenuPlatoRepository;
import com.example.Comedor.repository.PlatoRepository;
import com.example.Comedor.service.MenuPlatoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuPlatoServiceImp implements MenuPlatoService {


    @Autowired
    private MenuPlatoRepository menuPlatoRepository;

    @Autowired
    private MenuDiaRepository menuDiaRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private ModelMapper modelMapper;

   
    @Override
    public List<MenuPlatoResponse> listar() {
        return menuPlatoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, MenuPlatoResponse.class))
                .toList();
    }


    @Override
    public MenuPlatoResponse obtenerPorId(Integer id) {
        return menuPlatoRepository.findById(id)
                .map(model -> modelMapper.map(model, MenuPlatoResponse.class))
                .orElse(null);
    }

    
    @Override
    public MenuPlatoResponse guardar(MenuPlatoRequest req) {

        MenuPlatoModel model = new MenuPlatoModel();

      
        MenuDiaModel menuDia = menuDiaRepository.findById(req.getIdMenuDia())
                .orElseThrow(() -> new RuntimeException("No existe menuDia con id: " + req.getIdMenuDia()));

        PlatoModel plato = platoRepository.findById(req.getIdPlato())
                .orElseThrow(() -> new RuntimeException("No existe plato con id: " + req.getIdPlato()));

 
        model.setMenuDia(menuDia);
        model.setPlato(plato);

  
        MenuPlatoModel saved = menuPlatoRepository.save(model);

        return modelMapper.map(saved, MenuPlatoResponse.class);
    }


    @Override
    public MenuPlatoResponse modificar(Integer id, MenuPlatoRequest req) {

        MenuPlatoModel model = menuPlatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe menu_plato con id: " + id));

        MenuDiaModel menuDia = menuDiaRepository.findById(req.getIdMenuDia())
                .orElseThrow(() -> new RuntimeException("No existe menuDia con id: " + req.getIdMenuDia()));

        PlatoModel plato = platoRepository.findById(req.getIdPlato())
                .orElseThrow(() -> new RuntimeException("No existe plato con id: " + req.getIdPlato()));

        model.setMenuDia(menuDia);
        model.setPlato(plato);

        MenuPlatoModel actualizado = menuPlatoRepository.save(model);

        return modelMapper.map(actualizado, MenuPlatoResponse.class);
    }

   
    @Override
    public void eliminar(Integer id) {
        menuPlatoRepository.deleteById(id);
    }
    
}
