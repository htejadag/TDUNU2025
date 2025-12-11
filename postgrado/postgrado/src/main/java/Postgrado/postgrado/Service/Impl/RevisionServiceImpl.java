package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Revision;
import Postgrado.postgrado.Repository.RevisionRepository;
import Postgrado.postgrado.Service.RevisionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevisionServiceImpl implements RevisionService {

    private final RevisionRepository repository;

    public RevisionServiceImpl(RevisionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Revision crear(Revision revision) {
        return repository.save(revision);
    }

    @Override
    public List<Revision> listar() {
        return repository.findAll();
    }

    @Override
    public Revision obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
