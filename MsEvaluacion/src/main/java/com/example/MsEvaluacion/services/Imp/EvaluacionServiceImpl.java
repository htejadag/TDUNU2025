package com.example.MsEvaluacion.services.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsEvaluacion.model.entity.EvaluacionModel;
import com.example.MsEvaluacion.model.request.EvaluacionRequest;
import com.example.MsEvaluacion.model.response.EvaluacionResponse;
import com.example.MsEvaluacion.repository.IEvaluacionRepository;
import com.example.MsEvaluacion.services.IEvaluacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EvaluacionServiceImpl implements IEvaluacionService {

    @Autowired
    IEvaluacionRepository evaluacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void eliminar(String id) {
        

    }

    @Override
    public EvaluacionResponse guardar(EvaluacionRequest evaluacionRequest) {

        EvaluacionModel evaluacionModel = modelMapper.map(evaluacionRequest, EvaluacionModel.class);
        EvaluacionModel saved = evaluacionRepository.save(evaluacionModel);
        EvaluacionResponse response = modelMapper.map(saved, EvaluacionResponse.class);
        return response;

    }

    @Override
    public List<EvaluacionResponse> listar() {
        return evaluacionRepository.findAll()
                .stream()
                .map(evaluacion -> modelMapper.map(evaluacion, EvaluacionResponse.class))
                .toList();
    }

    @Override
    public EvaluacionResponse obtenerPorId(String id) {
        return evaluacionRepository.findById(id)
                .map(model -> modelMapper.map(model, EvaluacionResponse.class))
                .orElse(null);
    }

}
