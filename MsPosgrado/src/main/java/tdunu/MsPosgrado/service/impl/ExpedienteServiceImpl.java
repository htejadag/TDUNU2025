package tdunu.MsPosgrado.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdunu.MsPosgrado.model.ExpedienteModel;
import tdunu.MsPosgrado.repository.ExpedienteRepository;
import tdunu.MsPosgrado.service.ExpedienteService;


@Service
public class ExpedienteServiceImpl implements ExpedienteService{

    @Autowired
    ExpedienteRepository expedienteRepository;

    @Override
    public List<ExpedienteModel> listar() {
        return expedienteRepository.findAll();
    }

    @Override
    public ExpedienteModel obtenerPorId(Integer id) {
        return expedienteRepository.findById(id).orElse(null);
    }

    @Override
    public ExpedienteModel guardar(ExpedienteModel expediente) {
        return expedienteRepository.save(expediente);
    }

    @Override
    public void eliminar(Integer id) {
        expedienteRepository.deleteById(id);
    }

    
}