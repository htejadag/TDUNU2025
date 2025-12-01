package com.example.MsPlanEstudios.service.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsPlanEstudios.model.PlanEstudiosModel;
import com.example.MsPlanEstudios.repository.PlanEstudiosRepository;
import com.example.MsPlanEstudios.service.PlanEstudiosService;

@Service
public class PlanEstudiosServiceImp implements PlanEstudiosService {

    @Autowired
    PlanEstudiosRepository planestudiosRepository;

    @Override
    public List<PlanEstudiosModel> listar() {
        return planestudiosRepository.findAll();
    }

    @Override
    public PlanEstudiosModel obtenerPorId(Integer id) {
        return planestudiosRepository.findById(id).orElse(null);
    }

    @Override
    public PlanEstudiosModel guardar(PlanEstudiosModel planestudios) {
        return planestudiosRepository.save(planestudios);
    }

    @Override
    public void eliminar(Integer id) {
        planestudiosRepository.deleteById(id);
    }


}
