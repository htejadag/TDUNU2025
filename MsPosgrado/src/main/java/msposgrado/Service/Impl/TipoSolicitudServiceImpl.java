package msposgrado.Service.Impl;

import msposgrado.Model.TipoSolicitud;
import msposgrado.Repository.TipoSolicitudRepository;
import msposgrado.Service.TipoSolicitudService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TipoSolicitudServiceImpl implements TipoSolicitudService {

    private final TipoSolicitudRepository repository;

    public TipoSolicitudServiceImpl(TipoSolicitudRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "tiposSolicitud")
    public List<TipoSolicitud> listar() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "tiposSolicitud", key = "#id")
    public TipoSolicitud obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
