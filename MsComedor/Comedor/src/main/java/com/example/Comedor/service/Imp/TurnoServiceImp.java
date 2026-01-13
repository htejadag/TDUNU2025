package com.example.Comedor.service.Imp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Comedor.model.entity.TurnoModel;
import com.example.Comedor.model.request.turno.TurnoRequest;
import com.example.Comedor.model.request.turno.TurnoUpdateRequest;
import com.example.Comedor.model.response.TurnoResponse;
import com.example.Comedor.repository.TurnoRepository;
import com.example.Comedor.service.TurnoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TurnoServiceImp implements TurnoService {

    @Autowired
    TurnoRepository turnoRepository;
    

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TurnoResponse> listar() {
        return turnoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, TurnoResponse.class))
                .toList();
    }

    @Override
    public TurnoResponse obtenerPorId(Integer id) {
        return turnoRepository.findById(id)
                .map(model -> modelMapper.map(model, TurnoResponse.class))
                .orElse(null);
    }

    @Override
    public TurnoResponse guardar(TurnoRequest turnoRequest) {
        

        TurnoModel model = modelMapper.map(turnoRequest, TurnoModel.class);

        model.setDescripcion(turnoRequest.getDescripcion());
        model.setHoraApertura(LocalTime.parse(turnoRequest.getHoraApertura()));
        model.setHoraCierre(LocalTime.parse(turnoRequest.getHoraCierre()));
        model.setRacionesTotales(turnoRequest.getRacionesTotales());
        model.setRacionesRestantes(turnoRequest.getRacionesTotales());
        model.setActivo(turnoRequest.isActivo());
        model.setUsuarioCreacion(turnoRequest.getUsuarioCreacion());
        model.setFechaCreacion(LocalDate.now());


       
        TurnoModel saved = turnoRepository.save(model);

        return modelMapper.map(saved, TurnoResponse.class);

        
        
        
    }

    @Override
    public TurnoResponse modificar(Integer id, TurnoUpdateRequest turnoRequest) {

    TurnoModel model = turnoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un turno con id: " + id));

    model.setDescripcion(turnoRequest.getDescripcion());
    model.setHoraApertura(LocalTime.parse(turnoRequest.getHoraApertura()));
    model.setHoraCierre(LocalTime.parse(turnoRequest.getHoraCierre()));
    model.setRacionesTotales(turnoRequest.getRacionesTotales());
    model.setRacionesRestantes(turnoRequest.getRacionesTotales());
    model.setActivo(turnoRequest.isActivo());
    model.setUsuarioModificacion(turnoRequest.getUsuarioModificacion());
    model.setFechaModificacion(LocalDate.now());


    TurnoModel actualizado = turnoRepository.save(model);

    return modelMapper.map(actualizado, TurnoResponse.class);
}

    @Override
    public TurnoResponse eliminar(Integer id) {

         TurnoModel model = turnoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un turno con id: " + id));

    model.setActivo(false);

    TurnoModel actualizado = turnoRepository.save(model);

    return modelMapper.map(actualizado, TurnoResponse.class);


        
        
    }

    @Override
    public void descontarRacion(Integer id) {


         TurnoModel model = turnoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un Racion en ese turno y con ese id: " + id));

        model.setRacionesRestantes(model.getRacionesRestantes()-1);

        turnoRepository.save(model);
    }
    
}
