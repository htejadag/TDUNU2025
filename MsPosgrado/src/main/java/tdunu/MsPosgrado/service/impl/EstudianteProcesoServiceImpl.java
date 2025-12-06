package tdunu.MsPosgrado.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdunu.MsPosgrado.model.EstudianteProceso;
import tdunu.MsPosgrado.repository.EstudianteProcesoRepository;
import tdunu.MsPosgrado.service.EstudianteProcesoService;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteProcesoServiceImpl
 implements EstudianteProcesoService {

    @Autowired
    private EstudianteProcesoRepository repository;

    @Override
    public List<EstudianteProceso> listarTodos() {
        return repository.findAll();
    }

    @Override
    public EstudianteProceso guardar(EstudianteProceso proceso) {
        return repository.save(proceso);
    }

    @Override
    public Optional<EstudianteProceso> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}