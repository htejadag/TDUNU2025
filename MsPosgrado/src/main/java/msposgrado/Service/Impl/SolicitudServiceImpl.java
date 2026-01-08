package msposgrado.Service.Impl;

import msposgrado.Model.Solicitud;
import msposgrado.Repository.SolicitudRepository;
import msposgrado.Service.SolicitudService;
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
        return repository.findByActivoTrue();
    }

    @Override
    public Solicitud obtenerPorId(Integer id) {
        return repository.findById(id).filter(Solicitud::getActivo).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        Solicitud solicitud = obtenerPorId(id);
        if (solicitud != null) {
            solicitud.setActivo(false);
            repository.save(solicitud);
        }
    }
}
