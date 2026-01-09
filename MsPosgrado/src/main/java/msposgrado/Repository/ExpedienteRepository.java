package msposgrado.Repository;

import msposgrado.Model.Expediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad {@link Expediente}.
 * 
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "expediente"
 * en la base de datos mediante Spring Data JPA.
 * 
 * Incluye métodos personalizados para obtener únicamente los expedientes activos,
 * considerando que la entidad maneja un borrado lógico mediante el atributo {@code activo}.
 */
@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Integer> {

    /**
     * Obtiene todos los expedientes que están activos.
     * 
     * @return Lista de {@link Expediente} cuyo atributo {@code activo} es {@code true}.
     */
    List<Expediente> findByActivoTrue();
}