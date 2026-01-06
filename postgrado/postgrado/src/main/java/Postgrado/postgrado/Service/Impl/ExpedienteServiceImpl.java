package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Expediente;
import Postgrado.postgrado.Repository.ExpedienteRepository;
import Postgrado.postgrado.Service.ExpedienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpedienteServiceImpl implements ExpedienteService {

    private static final Logger log = LoggerFactory.getLogger(ExpedienteServiceImpl.class);
    private final ExpedienteRepository repository;

    public ExpedienteServiceImpl(ExpedienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Expediente crear(Expediente expediente) {
        
        log.info("Guardando expediente de estudiante {}", expediente.getIdEstudiante());
        return repository.save(expediente);
    }

    @Override
    public List<Expediente> listar() {
        
        log.info("Recuperando expedientes");
        return repository.findAll();
    }

    @Override
    public Expediente obtenerPorId(Integer id) {
         log.info("Buscando expediente {}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        log.warn("Eliminando expediente {}", id);
        repository.deleteById(id);
    }
}
