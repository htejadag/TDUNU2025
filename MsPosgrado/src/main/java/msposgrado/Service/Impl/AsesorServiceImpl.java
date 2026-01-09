package msposgrado.Service.Impl;

import msposgrado.Model.Asesor;
import msposgrado.Repository.AsesorRepository;
import msposgrado.Service.AsesorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación del servicio {@link AsesorService} para la gestión de asesores.
 *
 * Proporciona la lógica de negocio para crear, listar, obtener, actualizar
 * y eliminar asesores, aplicando borrado lógico mediante el atributo {@code activo}.
 *
 * Utiliza el repositorio {@link AsesorRepository} para interactuar con la base de datos.
 */
@Service
public class AsesorServiceImpl implements AsesorService {

    private static final Logger log = LoggerFactory.getLogger(AsesorServiceImpl.class);

    private final AsesorRepository repository;

    /**
     * Constructor que inyecta el repositorio de asesores.
     *
     * @param repository Repositorio {@link AsesorRepository} para acceso a datos.
     */
    public AsesorServiceImpl(AsesorRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea un nuevo asesor en el sistema.
     * Si el estado no se proporciona, se asigna "ACTIVO" por defecto.
     *
     * @param asesor Objeto {@link Asesor} a crear.
     * @return El {@link Asesor} creado con ID generado.
     */
    @Override
    public Asesor crear(Asesor asesor) {
        if (asesor.getEstadoAsesor() == null) {
            asesor.setEstadoAsesor("ACTIVO"); // Estado por defecto
        }
        log.info("Creando asesor: {}", asesor.getNombres());
        return repository.save(asesor);
    }

    /**
     * Lista todos los asesores activos.
     *
     * @return Lista de {@link Asesor} cuyo atributo {@code activo} es {@code true}.
     */
    @Override
    public List<Asesor> listar() {
        return repository.findByActivoTrue();
    }

    /**
     * Obtiene un asesor por su ID.
     * Verifica que el asesor exista y esté activo.
     *
     * @param id ID del asesor a obtener.
     * @return El {@link Asesor} correspondiente al ID.
     * @throws RuntimeException Si el asesor no existe o está inactivo.
     */
    @Override
    public Asesor obtenerPorId(Integer id) {
        Asesor asesor = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Asesor con ID {} no encontrado", id);
                    return new RuntimeException("Asesor no encontrado");
                });

        if (!asesor.getActivo()) {
            log.warn("Intento de acceso a Asesor inactivo (ID: {})", id);
            throw new RuntimeException("Asesor no encontrado");
        }
        return asesor;
    }

    /**
     * Actualiza los datos de un asesor existente.
     * Solo actualiza los campos proporcionados; los nulos se conservan.
     *
     * @param id ID del asesor a actualizar.
     * @param data Datos de {@link Asesor} a modificar.
     * @return El {@link Asesor} actualizado.
     */
    @Override
    public Asesor actualizar(Integer id, Asesor data) {
        Asesor existente = obtenerPorId(id);

        if (data.getNombres() != null && !data.getNombres().isEmpty()) {
            existente.setNombres(data.getNombres());
        }
        if (data.getApellidos() != null && !data.getApellidos().isEmpty()) {
            existente.setApellidos(data.getApellidos());
        }
        if (data.getGradoMaximo() != null && !data.getGradoMaximo().isEmpty()) {
            existente.setGradoMaximo(data.getGradoMaximo());
        }
        if (data.getCvRuta() != null && !data.getCvRuta().isEmpty()) {
            existente.setCvRuta(data.getCvRuta());
        }
        if (data.getDeclaracionRuta() != null && !data.getDeclaracionRuta().isEmpty()) {
            existente.setDeclaracionRuta(data.getDeclaracionRuta());
        }
        if (data.getEstadoAsesor() != null) {
            existente.setEstadoAsesor(data.getEstadoAsesor());
        }

        log.info("Actualizando asesor con ID {}", id);
        return repository.save(existente);
    }

    /**
     * Elimina un asesor mediante borrado lógico.
     * Marca el registro como inactivo sin eliminarlo físicamente.
     *
     * @param id ID del asesor a eliminar.
     * @throws RuntimeException Si el asesor no existe.
     */
    @Override
    public void eliminar(Integer id) {
        Asesor existente = obtenerPorId(id);

        log.warn("Realizando borrado LÓGICO (Auditoria) del asesor ID {}", id);
        existente.setActivo(false);
        repository.save(existente);
    }
}