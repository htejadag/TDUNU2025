package com.example.Comedor.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.MenuDiaModel;
import com.example.Comedor.model.entity.MenuSemanaModel;
import com.example.Comedor.model.request.menuSemana.MenuSemanaRequest;
import com.example.Comedor.model.request.menuSemana.MenuSemanaUpdateRequest;
import com.example.Comedor.model.response.MenuSemanaResponse;
import com.example.Comedor.repository.MenuDiaRepository;
import com.example.Comedor.repository.MenuSemanaRepository;
import com.example.Comedor.service.MenuSemanaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuSemanaServiceImp implements MenuSemanaService {

    private final MenuSemanaRepository menuSemanaRepository;

    private final ModelMapper modelMapper;

    private final MenuDiaRepository menuDiaRepository;

    public MenuSemanaServiceImp(MenuSemanaRepository menuSemanaRepository, ModelMapper modelMapper,
            MenuDiaRepository menuDiaRepository) {

        this.menuSemanaRepository = menuSemanaRepository;
        this.modelMapper = modelMapper;
        this.menuDiaRepository = menuDiaRepository;
    }

    @Override
    public List<MenuSemanaResponse> listar() {
        return menuSemanaRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, MenuSemanaResponse.class))
                .toList();
    }

    @Override
    public MenuSemanaResponse obtenerPorId(Integer id) {
        return menuSemanaRepository.findById(id)
                .map(model -> modelMapper.map(model, MenuSemanaResponse.class))
                .orElse(null);
    }

    @Override
    public MenuSemanaResponse guardar(MenuSemanaRequest request) {

        MenuSemanaModel model = modelMapper.map(request, MenuSemanaModel.class);

        model.setFechaInicio(LocalDate.parse(request.getFechaInicio()));
        model.setFechaFin(LocalDate.parse(request.getFechaFin()));
        model.setActivo(request.isActivo());
        model.setUsuarioCreacion(request.getUsuarioCreacion());
        model.setFechaCreacion(LocalDate.now());

        MenuSemanaModel saved = menuSemanaRepository.save(model);

        for (int dia = 1; dia <= 5; dia++) {
            MenuDiaModel menuDia = new MenuDiaModel();
            menuDia.setMenuSemana(saved);
            menuDia.setDia(dia);
            menuDia.setActivo(true);
            menuDia.setUsuarioCreacion(request.getUsuarioCreacion());
            menuDia.setFechaCreacion(LocalDate.now());
            menuDiaRepository.save(menuDia);
        }

        return modelMapper.map(saved, MenuSemanaResponse.class);

    }

    @Override
    public MenuSemanaResponse modificar(Integer id, MenuSemanaUpdateRequest request) {

        MenuSemanaModel model = menuSemanaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un menu semana con id: " + id));

        modelMapper.map(request, model);

        model.setFechaInicio(LocalDate.parse(request.getFechaInicio()));
        model.setFechaFin(LocalDate.parse(request.getFechaFin()));
        model.setActivo(request.isActivo());
        model.setUsuarioModificacion(request.getUsuarioModificacion());
        model.setFechaModificacion(LocalDate.now());

        MenuSemanaModel actualizado = menuSemanaRepository.save(model);

        return modelMapper.map(actualizado, MenuSemanaResponse.class);
    }

    @Override
    public MenuSemanaResponse eliminar(Integer id) {

        MenuSemanaModel model = menuSemanaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un menu semana con id: " + id));

        model.setActivo(false);

        MenuSemanaModel actualizado = menuSemanaRepository.save(model);

        return modelMapper.map(actualizado, MenuSemanaResponse.class);

    }

}
