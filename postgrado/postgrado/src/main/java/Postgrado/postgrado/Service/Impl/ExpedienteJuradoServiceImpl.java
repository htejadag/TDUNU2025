package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.ExpedienteJurado;
import Postgrado.postgrado.Repository.ExpedienteJuradoRepository;
import Postgrado.postgrado.Service.ExpedienteJuradoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpedienteJuradoServiceImpl implements ExpedienteJuradoService {

    private final ExpedienteJuradoRepository repository;

    public ExpedienteJuradoServiceImpl(ExpedienteJuradoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ExpedienteJurado crear(ExpedienteJurado ej) {
        return repository.save(ej);
    }

    @Override
    public List<ExpedienteJurado> listar() {
        return repository.findByActivoTrue();
    }

    @Override
    public ExpedienteJurado obtenerPorId(Integer id) {
        return repository.findById(id).filter(ExpedienteJurado::getActivo).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        ExpedienteJurado ej = obtenerPorId(id);
        if (ej != null) {
            ej.setActivo(false);
            repository.save(ej);
        }
    }
}
