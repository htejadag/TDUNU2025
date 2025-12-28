package com.example.MsEvaluacion.services.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsEvaluacion.model.entity.EvaluacionDetalleModel;
import com.example.MsEvaluacion.model.request.EDetalleResquest;
import com.example.MsEvaluacion.model.response.EDetalleResponse;
import com.example.MsEvaluacion.repository.IEDetalleRepository;
import com.example.MsEvaluacion.services.IEDetalleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EDetalleServiceImpl implements IEDetalleService {

    @Autowired
    IEDetalleRepository eDetalleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void eliminar(String id) {

    }

    @Override
    public EDetalleResponse guardar(EDetalleResquest eDetalleRequest) {
        EvaluacionDetalleModel evaluacionDetalleModel = modelMapper.map(eDetalleRequest, EvaluacionDetalleModel.class);
        EvaluacionDetalleModel saved = eDetalleRepository.save(evaluacionDetalleModel);
        EDetalleResponse response = modelMapper.map(saved, EDetalleResponse.class);
        return response;
    }

    @Override
    public List<EvaluacionDetalleModel> listarPorEvaluacion(String idEvaluacion) {
        return eDetalleRepository.findByIdEvaluacion(idEvaluacion);
    }

    @Override
    public List<EDetalleResponse> listar() {
        return eDetalleRepository.findAll()
                .stream()
                .map(evaluaciond -> modelMapper.map(evaluaciond, EDetalleResponse.class))
                .toList();
    }

    @Override
    public EDetalleResponse obtenerPorId(String id) {
        return eDetalleRepository.findById(id)
                .map(model -> modelMapper.map(model, EDetalleResponse.class))
                .orElse(null);
    }

}
