package msposgrado.Repository;

import msposgrado.Model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad {@link Solicitud}.
 * 
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "solicitudes".
 * 
 * Hereda todos los métodos de {@link JpaRepository} y además define métodos
 * personalizados para obtener solo las solicitudes activas.
 */
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    
    /**
     * Obtiene todas las solicitudes que están activas (activo = true).
     * 
     * @return lista de solicitudes activas
     */
    List<Solicitud> findByActivoTrue();
}