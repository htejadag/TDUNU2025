package msposgrado.Service.Impl;

import msposgrado.Model.Expediente;
import msposgrado.Repository.ExpedienteRepository;
import msposgrado.Service.ExpedienteService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación del servicio {@link ExpedienteService} para la gestión de expedientes.
 *
 * Proporciona métodos para crear, listar, obtener, actualizar y eliminar registros
 * de {@link Expediente} en la base de datos.
 *
 * Maneja borrado lógico mediante el atributo {@code activo} de la entidad.
 */
@Service
public class ExpedienteServiceImpl implements ExpedienteService {

    private final ExpedienteRepository repository;

    /**
     * Constructor que inyecta el repositorio {@link ExpedienteRepository}.
     *
     * @param repository Repositorio para acceso a los datos de {@link Expediente}.
     */
    public ExpedienteServiceImpl(ExpedienteRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea un nuevo registro de {@link Expediente}.
     * Asigna el estado por defecto "INICIADO" si no se proporciona.
     *
     * @param expediente Objeto {@link Expediente} a crear.
     * @return El {@link Expediente} creado con ID generado.
     */
    @Override
    public Expediente crear(Expediente expediente) {
        if (expediente.getEstadoExpediente() == null) {
            expediente.setEstadoExpediente("INICIADO");
        }
        return repository.save(expediente);
    }

    /**
     * Lista todos los expedientes activos.
     *
     * @return Lista de {@link Expediente} cuyo atributo {@code activo} es {@code true}.
     */
    @Override
    public List<Expediente> listar() {
        return repository.findByActivoTrue();
    }

    /**
     * Obtiene un expediente por su ID.
     *
     * @param id ID del expediente a obtener.
     * @return El {@link Expediente} correspondiente.
     * @throws RuntimeException Si no existe o está inactivo.
     */
    @Override
    public Expediente obtenerPorId(Integer id) {
        Expediente expediente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expediente no encontrado"));

        if (!expediente.getActivo()) {
            throw new RuntimeException("Expediente no encontrado");
        }
        return expediente;
    }

    /**
     * Actualiza un expediente existente.
     * Solo actualiza los campos que no sean nulos o vacíos en el objeto proporcionado.
     *
     * @param id   ID del expediente a actualizar.
     * @param data Objeto {@link Expediente} con los datos a actualizar.
     * @return El {@link Expediente} actualizado.
     */
    @Override
    public Expediente actualizar(Integer id, Expediente data) {
        Expediente existente = obtenerPorId(id);

        if (data.getIdEstudiante() != null) {
            existente.setIdEstudiante(data.getIdEstudiante());
        }
        if (data.getEstadoExpediente() != null && !data.getEstadoExpediente().isEmpty()) {
            existente.setEstadoExpediente(data.getEstadoExpediente());
        }
        if (data.getFechaCierre() != null) {
            existente.setFechaCierre(data.getFechaCierre());
        }
        if (data.getObservaciones() != null && !data.getObservaciones().isEmpty()) {
            existente.setObservaciones(data.getObservaciones());
        }

        return repository.save(existente);
    }

    /**
     * Realiza un borrado lógico de un expediente.
     * Marca el atributo {@code activo} como {@code false} para mantener la auditoría.
     *
     * @param id ID del expediente a eliminar.
     */
    @Override
    public void eliminar(Integer id) {
        Expediente existente = obtenerPorId(id);
        if (existente != null) {
            existente.setActivo(false);
            repository.save(existente);
        }
    }
}