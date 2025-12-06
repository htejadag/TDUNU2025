package com.example.MsPlanEstudios.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsPlanEstudios.model.entity.PlanEstudiosDetalleModel;
import com.example.MsPlanEstudios.model.request.PlanEstudiosDetalleRequest;
import com.example.MsPlanEstudios.model.response.PlanEstudiosDetalleResponse;
import com.example.MsPlanEstudios.repository.PlanEstudiosDetalleRepository;
import com.example.MsPlanEstudios.service.PlanEstudiosDetalleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanEstudiosDetalleServiceImp implements PlanEstudiosDetalleService{
    @Autowired
    PlanEstudiosDetalleRepository planestudiosdetalleRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PlanEstudiosDetalleResponse> listar() {
        return planestudiosdetalleRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, PlanEstudiosDetalleResponse.class))
                .toList();
    }

    @Override
    public PlanEstudiosDetalleResponse obtenerPorId(Integer id) {
        return planestudiosdetalleRepository.findById(id)
                .map(model -> modelMapper.map(model, PlanEstudiosDetalleResponse.class))
                .orElse(null);
    }

    @Override
    public PlanEstudiosDetalleResponse guardar(PlanEstudiosDetalleRequest request) {
        // 1. Request -> Model
        PlanEstudiosDetalleModel model = modelMapper.map(request, PlanEstudiosDetalleModel.class);

        // 2. Guardar en BD
        PlanEstudiosDetalleModel saved = planestudiosdetalleRepository.save(model);

        // 3. Model -> Response
        PlanEstudiosDetalleResponse response = modelMapper.map(saved, PlanEstudiosDetalleResponse.class);

        return response;
    }

    @Override
    public PlanEstudiosDetalleResponse modificar(Integer id, PlanEstudiosDetalleRequest request) {
        // 1. Request -> Model
        PlanEstudiosDetalleModel model = modelMapper.map(request, PlanEstudiosDetalleModel.class);

        // 2. Asignar el id que viene por parámetro
        model.setId(id);

        // 3. Guardar en BD (si el id existe, hace UPDATE; si no, INSERT)
        PlanEstudiosDetalleModel saved = planestudiosdetalleRepository.save(model);

        // 4. Model -> Response
        PlanEstudiosDetalleResponse response = modelMapper.map(saved, PlanEstudiosDetalleResponse.class);

        return response;
    }

    @Override
    public void eliminar(Integer id) {
        planestudiosdetalleRepository.deleteById(id);
    }
    
}
