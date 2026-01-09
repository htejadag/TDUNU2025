package msposgrado.Service.Impl;

import msposgrado.Model.Revision;
import msposgrado.Repository.RevisionRepository;
import msposgrado.Service.RevisionService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación del servicio {@link RevisionService} para la gestión de revisiones de tesis.
 *
 * Proporciona métodos para crear, listar, obtener y eliminar registros
 * de {@link Revision} en la base de datos.
 */
@Service
public class RevisionServiceImpl implements RevisionService {

    private final RevisionRepository repository;

    /**
     * Constructor que inyecta el repositorio {@link RevisionRepository}.
     *
     * @param repository Repositorio para acceso a los datos de {@link Revision}.
     */
    public RevisionServiceImpl(RevisionRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea un nuevo registro de {@link Revision}.
     *
     * @param revision Objeto {@link Revision} a crear.
     * @return El {@link Revision} creado con ID generado.
     */
    @Override
    public Revision crear(Revision revision) {
        return repository.save(revision);
    }

    /**
     * Lista todas las revisiones existentes.
     *
     * @return Lista de {@link Revision}.
     */
    @Override
    public List<Revision> listar() {
        return repository.findAll();
    }

    /**
     * Obtiene una revisión por su ID.
     *
     * @param id ID de la revisión a obtener.
     * @return El {@link Revision} correspondiente o {@code null} si no existe.
     */
    @Override
    public Revision obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Elimina una revisión por su ID.
     *
     * @param id ID de la revisión a eliminar.
     */
    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}