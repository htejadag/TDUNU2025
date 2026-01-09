package msposgrado.Repository;

import msposgrado.Model.TipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio para la entidad {@link TipoSolicitud}.
 * 
 * Permite realizar operaciones CRUD sobre la tabla "tipo_solicitud".
 */
@Repository
public interface TipoSolicitudRepository extends JpaRepository<TipoSolicitud, Integer> {
    
    /**
     * Busca un tipo de solicitud por su nombre.
     * 
     * @param nombre Nombre del tipo de solicitud
     * @return {@link Optional} con el tipo de solicitud si existe
     */
    Optional<TipoSolicitud> findByNombre(String nombre);
}