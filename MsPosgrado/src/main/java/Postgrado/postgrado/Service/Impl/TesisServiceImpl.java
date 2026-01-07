package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Tesis;
import Postgrado.postgrado.Repository.TesisRepository;
import Postgrado.postgrado.Service.TesisService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesisServiceImpl implements TesisService {

    private final TesisRepository repository;

    public TesisServiceImpl(TesisRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tesis crear(Tesis tesis) {
        return repository.save(tesis);
    }

    @Override
    public List<Tesis> listar() {
        return repository.findAll();
    }

    @Override
    public Tesis obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
