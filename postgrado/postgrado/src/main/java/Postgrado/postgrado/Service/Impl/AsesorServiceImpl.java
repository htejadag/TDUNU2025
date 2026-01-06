package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Asesor;
import Postgrado.postgrado.Repository.AsesorRepository;
import Postgrado.postgrado.Service.AsesorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsesorServiceImpl implements AsesorService {

    private static final Logger log = LoggerFactory.getLogger(AsesorServiceImpl.class);

    private final AsesorRepository repository;

    public AsesorServiceImpl(AsesorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Asesor crear(Asesor asesor) {
        log.info("Guardando asesor {}", asesor.getNombres());
        return repository.save(asesor);
    }

    @Override
    public List<Asesor> listar() {
        log.info("Recuperando todos los asesores");
        return repository.findAll();
    }

    @Override
    public Asesor obtenerPorId(Integer id) {
        log.info("Buscando asesor con ID {}", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        log.warn("Eliminando asesor {}", id);
        repository.deleteById(id);
    }
}
