package com.example.MsPlanEstudios.service.Imp;

//import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsPlanEstudios.model.entity.PlanEstudiosModel;
import com.example.MsPlanEstudios.model.request.PlanEstudiosRequest;
import com.example.MsPlanEstudios.model.response.PlanEstudiosResponse;
import com.example.MsPlanEstudios.repository.PlanEstudiosRepository;
import com.example.MsPlanEstudios.service.PlanEstudiosService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanEstudiosServiceImp implements PlanEstudiosService {

    @Autowired
    PlanEstudiosRepository planestudiosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PlanEstudiosResponse> listar() {
        return planestudiosRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, PlanEstudiosResponse.class))
                .toList();
    }

    @Override
    public PlanEstudiosResponse obtenerPorId(Integer id) {
        return planestudiosRepository.findById(id)
                .map(model -> modelMapper.map(model, PlanEstudiosResponse.class))
                .orElse(null);
    }

    @Override
    public PlanEstudiosResponse guardar(PlanEstudiosRequest request) {
        // 1. Request -> Model
        PlanEstudiosModel model = modelMapper.map(request, PlanEstudiosModel.class);

        // 2. Guardar en BD
        PlanEstudiosModel saved = planestudiosRepository.save(model);

        // 3. Model -> Response
        PlanEstudiosResponse response = modelMapper.map(saved, PlanEstudiosResponse.class);

        return response;
    }

    @Override
    public void eliminar(Integer id) {
        planestudiosRepository.deleteById(id);
    }

    @Override
    public PlanEstudiosResponse modificar(Integer id, PlanEstudiosRequest request) {
        // 1. Request -> Model
        PlanEstudiosModel model = modelMapper.map(request, PlanEstudiosModel.class);

        // 2. Asignar el id que viene por parámetro
        model.setId(id);

        // 3. Guardar en BD (si el id existe, hace UPDATE; si no, INSERT)
        PlanEstudiosModel saved = planestudiosRepository.save(model);

        // 4. Model -> Response
        PlanEstudiosResponse response = modelMapper.map(saved, PlanEstudiosResponse.class);

        return response;
    }

}
