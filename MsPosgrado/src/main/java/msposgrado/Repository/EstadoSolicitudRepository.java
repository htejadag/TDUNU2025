package msposgrado.Repository;

import msposgrado.Model.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad {@link EstadoSolicitud}.
 * 
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "estado_solicitud"
 * en la base de datos mediante Spring Data JPA.
 * 
 * Incluye métodos personalizados para consultas específicas, como buscar por nombre.
 */
@Repository
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitud, Integer> {

    /**
     * Busca un {@link EstadoSolicitud} por su nombre.
     * 
     * Este método aprovecha la convención de nombres de Spring Data JPA para generar
     * automáticamente la consulta equivalente a:
     * <pre>
     * SELECT * FROM estado_solicitud WHERE nombre = ?;
     * </pre>
     * 
     * Se utiliza, por ejemplo, para inicializar datos o validar estados específicos
     * dentro del sistema.
     * 
     * @param nombre nombre del estado de solicitud a buscar
     * @return un {@link Optional} que contiene el {@link EstadoSolicitud} si existe,
     *         o vacío si no se encuentra
     */
    Optional<EstadoSolicitud> findByNombre(String nombre);
}