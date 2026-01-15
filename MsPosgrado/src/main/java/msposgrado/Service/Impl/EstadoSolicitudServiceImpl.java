package msposgrado.Service.Impl;

import msposgrado.Model.EstadoSolicitud;
import msposgrado.Repository.EstadoSolicitudRepository;
import msposgrado.Service.EstadoSolicitudService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EstadoSolicitudServiceImpl implements EstadoSolicitudService {

    private final EstadoSolicitudRepository repository;

    public EstadoSolicitudServiceImpl(EstadoSolicitudRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "estadosSolicitud")
    public List<EstadoSolicitud> listar() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "estadosSolicitud", key = "#id")
    public EstadoSolicitud obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    @org.springframework.cache.annotation.CacheEvict(value = "estadosSolicitud", allEntries = true)
    public EstadoSolicitud guardar(EstadoSolicitud estadoSolicitud) {
        return repository.save(estadoSolicitud);
    }

    @Override
    @Transactional
    @org.springframework.cache.annotation.CacheEvict(value = "estadosSolicitud", allEntries = true)
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
