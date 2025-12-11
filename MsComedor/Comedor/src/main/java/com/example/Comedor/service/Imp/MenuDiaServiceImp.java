package com.example.Comedor.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.MenuDiaModel;
import com.example.Comedor.model.entity.MenuSemanaModel;
import com.example.Comedor.model.entity.TurnoModel;
import com.example.Comedor.model.request.MenuDiaRequest;
import com.example.Comedor.model.response.MenuDiaResponse;
import com.example.Comedor.repository.MenuDiaRepository;
import com.example.Comedor.repository.MenuSemanaRepository;
import com.example.Comedor.repository.TurnoRepository;
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
    private TurnoRepository turnoRepository;

    
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

        MenuDiaModel model = new MenuDiaModel();

        //Cargar las entidades por ID
        MenuSemanaModel semana = menuSemanaRepository.findById(req.getIdMenuSemana())
            .orElseThrow(() -> new RuntimeException("No existe menu semana con id: " + req.getIdMenuSemana()));

        TurnoModel turno = turnoRepository.findById(req.getIdTurno())
            .orElseThrow(() -> new RuntimeException("No existe turno con id: " + req.getIdTurno()));

        // Asignar relaciones correctamente
        model.setMenuSemana(semana);
        model.setTurno(turno);

        model.setRacionesTotales(req.getRacionesTotales());
        model.setRacionesRestantes(req.getRacionesRestantes());
        model.setActivo(req.isActivo());

        //AGREGAR ESTO
        model.setUsuarioCreacion(req.getUsuarioCreacion());
        model.setFechaCreacion(req.getFechaCreacion().toString());
        model.setUsuarioModificacion(req.getUsuarioModificacion());
        model.setFechaModificacion(req.getFechaModificacion().toString());

       

        // Guardar
        MenuDiaModel saved = menuDiaRepository.save(model);

        return modelMapper.map(saved, MenuDiaResponse.class);
    }


    @Override
    public MenuDiaResponse modificar(Integer id, MenuDiaRequest menuDiaRequest) {
        MenuDiaModel model = menuDiaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un comedor con id: " + id));


     
        if (menuDiaRequest.getFechaModificacion() != null) {
            model.setFechaModificacion(menuDiaRequest.getFechaModificacion().toString());
        } else {
            model.setFechaModificacion(null);
        }

    

        modelMapper.map(menuDiaRequest, model);

        MenuDiaModel actualizado = menuDiaRepository.save(model);

        return modelMapper.map(actualizado, MenuDiaResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        menuDiaRepository.deleteById(id);
        
    
    }

}