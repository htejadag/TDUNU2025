package msposgrado.Service.Impl;

import msposgrado.Model.ExpedienteJurado;
import msposgrado.Repository.ExpedienteJuradoRepository;
import msposgrado.Service.ExpedienteJuradoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio {@link ExpedienteJuradoService} para la gestión de la relación
 * entre expedientes y jurados.
 *
 * Proporciona métodos para crear, listar, obtener y eliminar registros de
 * {@link ExpedienteJurado} en la base de datos.
 */
@Service
public class ExpedienteJuradoServiceImpl implements ExpedienteJuradoService {

    private final ExpedienteJuradoRepository repository;

    /**
     * Constructor que inyecta el repositorio {@link ExpedienteJuradoRepository}.
     *
     * @param repository Repositorio para acceso a los datos de {@link ExpedienteJurado}.
     */
    public ExpedienteJuradoServiceImpl(ExpedienteJuradoRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea un nuevo registro de {@link ExpedienteJurado}.
     *
     * @param ej Objeto {@link ExpedienteJurado} a crear.
     * @return El {@link ExpedienteJurado} creado con ID generado.
     */
    @Override
    public ExpedienteJurado crear(ExpedienteJurado ej) {
        return repository.save(ej);
    }

    /**
     * Lista todos los registros de {@link ExpedienteJurado}.
     *
     * @return Lista completa de {@link ExpedienteJurado}.
     */
    @Override
    public List<ExpedienteJurado> listar() {
        return repository.findAll();
    }

    /**
     * Obtiene un registro de {@link ExpedienteJurado} por su ID.
     *
     * @param id ID del registro a obtener.
     * @return El {@link ExpedienteJurado} correspondiente, o {@code null} si no existe.
     */
    @Override
    public ExpedienteJurado obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Elimina un registro de {@link ExpedienteJurado} por su ID.
     *
     * @param id ID del registro a eliminar.
     */
    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}