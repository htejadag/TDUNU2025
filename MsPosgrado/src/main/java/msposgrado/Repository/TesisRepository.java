package msposgrado.Repository;

import msposgrado.Model.Tesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad {@link Tesis}.
 * 
 * Proporciona métodos CRUD y operaciones sobre la tabla "tesis".
 */
@Repository
public interface TesisRepository extends JpaRepository<Tesis, Integer> {
    
    /**
     * Obtiene todas las tesis que están activas (activo = true).
     * 
     * @return lista de tesis activas
     */
    List<Tesis> findByActivoTrue();
}