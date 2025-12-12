package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Asesor;
import Postgrado.postgrado.Repository.AsesorRepository;
import Postgrado.postgrado.Service.AsesorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsesorServiceImpl implements AsesorService {

    private final AsesorRepository repository;

    public AsesorServiceImpl(AsesorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Asesor crear(Asesor asesor) {
        return repository.save(asesor);
    }

    @Override
    public List<Asesor> listar() {
        return repository.findAll();
    }

    @Override
    public Asesor obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
