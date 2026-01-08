package msposgrado.Service.Impl;

import msposgrado.Model.ExpedienteJurado;
import msposgrado.Repository.ExpedienteJuradoRepository;
import msposgrado.Service.ExpedienteJuradoService;
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
        return repository.findAll();
    }

    @Override
    public ExpedienteJurado obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
