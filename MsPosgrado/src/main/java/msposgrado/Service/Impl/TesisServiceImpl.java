package msposgrado.Service.Impl;

import msposgrado.Model.Tesis;
import msposgrado.Repository.TesisRepository;
import msposgrado.Service.TesisService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación del servicio {@link TesisService} para la gestión de tesis.
 *
 * Proporciona operaciones CRUD sobre la entidad {@link Tesis}.
 */
@Service
public class TesisServiceImpl implements TesisService {

    private final TesisRepository repository;

    /**
     * Constructor que inyecta el repositorio de {@link Tesis}.
     *
     * @param repository Repositorio para operaciones de persistencia sobre {@link Tesis}.
     */
    public TesisServiceImpl(TesisRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea y persiste una nueva tesis en la base de datos.
     *
     * @param tesis Objeto {@link Tesis} a crear.
     * @return La {@link Tesis} creada y guardada en la base de datos.
     */
    @Override
    public Tesis crear(Tesis tesis) {
        return repository.save(tesis);
    }

    /**
     * Obtiene todas las tesis registradas.
     *
     * @return Lista de {@link Tesis} presentes en la base de datos.
     */
    @Override
    public List<Tesis> listar() {
        return repository.findAll();
    }

    /**
     * Obtiene una tesis por su ID.
     *
     * @param id ID de la tesis a buscar.
     * @return La {@link Tesis} correspondiente al ID o {@code null} si no existe.
     */
    @Override
    public Tesis obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Elimina una tesis por su ID.
     *
     * @param id ID de la tesis a eliminar.
     */
    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}