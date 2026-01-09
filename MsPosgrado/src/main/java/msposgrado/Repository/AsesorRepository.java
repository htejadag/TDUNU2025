package msposgrado.Repository;

import msposgrado.Model.Asesor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Asesor}.
 * 
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "asesor"
 * en la base de datos, utilizando Spring Data JPA.
 * 
 * También incluye métodos personalizados según la lógica del sistema,
 * por ejemplo, para filtrar solo los registros activos (borrado lógico).
 */
@Repository
public interface AsesorRepository extends JpaRepository<Asesor, Integer> {

    /**
     * Devuelve una lista de todos los asesores cuyo estado "activo" es verdadero.
     * 
     * Este método aprovecha la convención de nombres de Spring Data JPA para generar
     * automáticamente la consulta equivalente a:
     * <pre>
     * SELECT * FROM asesor WHERE activo = true
     * </pre>
     * 
     * Se utiliza para mostrar únicamente los asesores activos en el sistema.
     * 
     * @return lista de {@link Asesor} activos
     */
    List<Asesor> findByActivoTrue();
}