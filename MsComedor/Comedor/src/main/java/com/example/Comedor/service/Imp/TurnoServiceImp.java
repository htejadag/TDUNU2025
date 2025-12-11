package com.example.Comedor.service.Imp;

import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Comedor.model.entity.TurnoModel;
import com.example.Comedor.model.request.TurnoRequest;
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
                .map(model -> {
                    TurnoResponse resp = modelMapper.map(model, TurnoResponse.class);
                    resp.setHoraApertura(model.getHoraApertura().toString());
                    resp.setHoraCierre(model.getHoraCierre().toString());
                    return resp;
                })
                .toList();
    }

    @Override
    public TurnoResponse obtenerPorId(Integer id) {
        return turnoRepository.findById(id)
                .map(model -> {
                    TurnoResponse resp = modelMapper.map(model, TurnoResponse.class);
                    resp.setHoraApertura(model.getHoraApertura().toString());
                    resp.setHoraCierre(model.getHoraCierre().toString());
                    return resp;
                })
                .orElse(null);
    }

    @Override
    public TurnoResponse guardar(TurnoRequest turnoRequest) {
        

        TurnoModel model = modelMapper.map(turnoRequest, TurnoModel.class);

        // String -> LocalTime
        model.setHoraApertura(LocalTime.parse(turnoRequest.getHoraApertura()));
        model.setHoraCierre(LocalTime.parse(turnoRequest.getHoraCierre()));

       
        TurnoModel saved = turnoRepository.save(model);

       
        TurnoResponse response = modelMapper.map(saved, TurnoResponse.class);

        // Convertimos al response
        response.setHoraApertura(saved.getHoraApertura().toString());
        response.setHoraCierre(saved.getHoraCierre().toString());

        return response;
        
        
    }

    @Override
    public TurnoResponse modificar(Integer id, TurnoRequest turnoRequest) {

    TurnoModel model = turnoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un turno con id: " + id));


    
    model.setDescripcion(turnoRequest.getDescripcion());
    model.setHoraApertura(LocalTime.parse(turnoRequest.getHoraApertura()));
    model.setHoraCierre(LocalTime.parse(turnoRequest.getHoraCierre()));

 
    TurnoModel actualizado = turnoRepository.save(model);

    return modelMapper.map(actualizado, TurnoResponse.class);
}

    @Override
    public void eliminar(Integer id) {

        turnoRepository.deleteById(id);
        
    }
    
}
