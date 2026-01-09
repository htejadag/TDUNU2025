package msposgrado.Repository;

import msposgrado.Model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad {@link Documento}.
 * 
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "documentos"
 * en la base de datos mediante Spring Data JPA.
 * 
 * Incluye métodos personalizados según la lógica del sistema,
 * como filtrar documentos activos (borrado lógico).
 */
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

    /**
     * Devuelve una lista de todos los documentos cuyo estado "activo" es verdadero.
     * 
     * Este método aprovecha la convención de nombres de Spring Data JPA para generar
     * automáticamente la consulta equivalente a:
     * <pre>
     * SELECT * FROM documentos WHERE activo = true
     * </pre>
     * 
     * Se utiliza para mostrar únicamente los documentos activos en el sistema.
     * 
     * @return lista de {@link Documento} activos
     */
    List<Documento> findByActivoTrue();
}