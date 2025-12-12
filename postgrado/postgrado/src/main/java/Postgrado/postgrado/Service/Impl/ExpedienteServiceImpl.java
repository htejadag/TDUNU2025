package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Expediente;
import Postgrado.postgrado.Repository.ExpedienteRepository;
import Postgrado.postgrado.Service.ExpedienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpedienteServiceImpl implements ExpedienteService {

    private final ExpedienteRepository repository;

    public ExpedienteServiceImpl(ExpedienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Expediente crear(Expediente expediente) {
        return repository.save(expediente);
    }

    @Override
    public List<Expediente> listar() {
        return repository.findAll();
    }

    @Override
    public Expediente obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
