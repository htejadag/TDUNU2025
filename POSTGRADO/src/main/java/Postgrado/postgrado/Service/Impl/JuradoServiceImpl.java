package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Jurado;
import Postgrado.postgrado.Repository.JuradoRepository;
import Postgrado.postgrado.Service.JuradoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuradoServiceImpl implements JuradoService {

    private final JuradoRepository repository;

    public JuradoServiceImpl(JuradoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Jurado crear(Jurado jurado) {
        return repository.save(jurado);
    }

    @Override
    public List<Jurado> listar() {
        return repository.findAll();
    }

    @Override
    public Jurado obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
