package msposgrado.Service.Impl;

import msposgrado.Model.Jurado;
import msposgrado.Repository.JuradoRepository;
import msposgrado.Service.JuradoService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación del servicio {@link JuradoService} para la gestión de jurados.
 *
 * Proporciona métodos para crear, listar, obtener, actualizar y eliminar registros
 * de {@link Jurado} en la base de datos.
 *
 * Maneja borrado lógico mediante el atributo {@code activo} de la entidad.
 */
@Service
public class JuradoServiceImpl implements JuradoService {

    private final JuradoRepository repository;

    /**
     * Constructor que inyecta el repositorio {@link JuradoRepository}.
     *
     * @param repository Repositorio para acceso a los datos de {@link Jurado}.
     */
    public JuradoServiceImpl(JuradoRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea un nuevo registro de {@link Jurado}.
     * Asigna el estado por defecto "ACTIVO" si no se proporciona.
     *
     * @param jurado Objeto {@link Jurado} a crear.
     * @return El {@link Jurado} creado con ID generado.
     */
    @Override
    public Jurado crear(Jurado jurado) {
        if (jurado.getEstadoJurado() == null) {
            jurado.setEstadoJurado("ACTIVO");
        }
        return repository.save(jurado);
    }

    /**
     * Lista todos los jurados activos.
     *
     * @return Lista de {@link Jurado} cuyo atributo {@code activo} es {@code true}.
     */
    @Override
    public List<Jurado> listar() {
        return repository.findByActivoTrue();
    }

    /**
     * Obtiene un jurado por su ID.
     *
     * @param id ID del jurado a obtener.
     * @return El {@link Jurado} correspondiente.
     * @throws RuntimeException Si no existe o está inactivo.
     */
    @Override
    public Jurado obtenerPorId(Integer id) {
        Jurado jurado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jurado no encontrado"));

        if (!jurado.getActivo()) {
            throw new RuntimeException("Jurado no encontrado");
        }
        return jurado;
    }

    /**
     * Actualiza un jurado existente.
     * Solo actualiza los campos que no sean nulos o vacíos en el objeto proporcionado.
     *
     * @param id   ID del jurado a actualizar.
     * @param data Objeto {@link Jurado} con los datos a actualizar.
     * @return El {@link Jurado} actualizado.
     */
    @Override
    public Jurado actualizar(Integer id, Jurado data) {
        Jurado existente = obtenerPorId(id);

        if (data.getNombres() != null && !data.getNombres().isEmpty()) {
            existente.setNombres(data.getNombres());
        }
        if (data.getApellidos() != null && !data.getApellidos().isEmpty()) {
            existente.setApellidos(data.getApellidos());
        }
        if (data.getEspecialidad() != null && !data.getEspecialidad().isEmpty()) {
            existente.setEspecialidad(data.getEspecialidad());
        }
        if (data.getEstadoJurado() != null) {
            existente.setEstadoJurado(data.getEstadoJurado());
        }

        return repository.save(existente);
    }

    /**
     * Realiza un borrado lógico de un jurado.
     * Marca el atributo {@code activo} como {@code false} para mantener la auditoría.
     *
     * @param id ID del jurado a eliminar.
     */
    @Override
    public void eliminar(Integer id) {
        Jurado existente = obtenerPorId(id);
        if (existente != null) {
            existente.setActivo(false);
            repository.save(existente);
        }
    }
}