package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Solicitud;
import Postgrado.postgrado.Repository.SolicitudRepository;
import Postgrado.postgrado.Service.SolicitudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository repository;

    public SolicitudServiceImpl(SolicitudRepository repository) {
        this.repository = repository;
    }

    @Override
    public Solicitud crear(Solicitud solicitud) {
        return repository.save(solicitud);
    }

    @Override
    public List<Solicitud> listar() {
        return repository.findAll();
    }

    @Override
    public Solicitud obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
