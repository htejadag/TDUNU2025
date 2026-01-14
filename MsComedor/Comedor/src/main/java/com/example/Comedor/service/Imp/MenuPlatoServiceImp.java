package com.example.Comedor.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.MenuDiaModel;
import com.example.Comedor.model.entity.MenuPlatoModel;
import com.example.Comedor.model.entity.PlatoModel;
import com.example.Comedor.model.entity.TurnoModel;
import com.example.Comedor.model.request.menuPlato.MenuPlatoRequest;
import com.example.Comedor.model.request.menuPlato.MenuPlatoUpdateRequest;
import com.example.Comedor.model.response.MenuPlatoResponse;
import com.example.Comedor.repository.MenuDiaRepository;
import com.example.Comedor.repository.MenuPlatoRepository;
import com.example.Comedor.repository.PlatoRepository;
import com.example.Comedor.repository.TurnoRepository;
import com.example.Comedor.service.MenuPlatoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuPlatoServiceImp implements MenuPlatoService {

        private final MenuPlatoRepository menuPlatoRepository;

        private final MenuDiaRepository menuDiaRepository;

        private final PlatoRepository platoRepository;

        private final TurnoRepository turnoRepository;

        private final ModelMapper modelMapper;

        public MenuPlatoServiceImp(MenuPlatoRepository menuPlatoRepository,
                        MenuDiaRepository menuDiaRepository,
                        PlatoRepository platoRepository,
                        TurnoRepository turnoRepository,
                        ModelMapper modelMapper) {

                this.menuPlatoRepository = menuPlatoRepository;
                this.menuDiaRepository = menuDiaRepository;
                this.platoRepository = platoRepository;
                this.turnoRepository = turnoRepository;
                this.modelMapper = modelMapper;

        }

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

                MenuDiaModel menuDia = menuDiaRepository.findById(req.getIdMenuDia())
                                .orElseThrow(() -> new RuntimeException(
                                                "No existe menuDia con id: " + req.getIdMenuDia()));

                PlatoModel plato = platoRepository.findById(req.getIdPlato())
                                .orElseThrow(() -> new RuntimeException("No existe plato con id: " + req.getIdPlato()));

                TurnoModel turno = turnoRepository.findById(req.getIdTurno())
                                .orElseThrow(() -> new RuntimeException("No existe turno con id: " + req.getIdTurno()));

                MenuPlatoModel model = new MenuPlatoModel();

                model.setIdMenuDia(menuDia);
                model.setIdPlato(plato);
                model.setIdTurno(turno);
                model.setActivo(req.isActivo());
                model.setUsuarioCreacion(req.getUsuarioCreacion());
                model.setFechaCreacion(LocalDate.now());

                MenuPlatoModel saved = menuPlatoRepository.save(model);

                return modelMapper.map(saved, MenuPlatoResponse.class);

        }

        @Override
        public MenuPlatoResponse modificar(Integer id, MenuPlatoUpdateRequest req) {

                MenuPlatoModel model = menuPlatoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("No existe menu_plato con id: " + id));

                MenuDiaModel menuDia = menuDiaRepository.findById(req.getIdMenuDia())
                                .orElseThrow(() -> new RuntimeException(
                                                "No existe menuDia con id: " + req.getIdMenuDia()));

                PlatoModel plato = platoRepository.findById(req.getIdPlato())
                                .orElseThrow(() -> new RuntimeException("No existe plato con id: " + req.getIdPlato()));

                TurnoModel turno = turnoRepository.findById(req.getIdTurno())
                                .orElseThrow(() -> new RuntimeException("No existe turno con id: " + req.getIdTurno()));

                modelMapper.map(req, model);

                model.setIdMenuDia(menuDia);
                model.setIdPlato(plato);
                model.setIdTurno(turno);
                model.setActivo(req.isActivo());
                model.setUsuarioModificacion(req.getUsuarioModificacion());
                model.setFechaModificacion(LocalDate.now());

                MenuPlatoModel actualizado = menuPlatoRepository.save(model);

                return modelMapper.map(actualizado, MenuPlatoResponse.class);
        }

        @Override
        public MenuPlatoResponse eliminar(Integer id) {

                MenuPlatoModel model = menuPlatoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("No existe menu_plato con id: " + id));

                model.setActivo(false);

                MenuPlatoModel actualizado = menuPlatoRepository.save(model);

                return modelMapper.map(actualizado, MenuPlatoResponse.class);

        }

        @Override
        public List<MenuPlatoResponse> listarPorMenuSemana(Integer idMenuSemana) {

                return menuPlatoRepository
                                .findByIdMenuDia_MenuSemana_Id(idMenuSemana)
                                .stream()
                                .map(model -> modelMapper.map(model, MenuPlatoResponse.class))
                                .toList();

        }

}
