package msposgrado.Repository;

import msposgrado.Model.Jurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad {@link Jurado}.
 * 
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "jurado"
 * en la base de datos mediante Spring Data JPA.
 * 
 * Incluye métodos personalizados para obtener únicamente los jurados activos,
 * considerando que la entidad maneja un borrado lógico mediante el atributo {@code activo}.
 */
@Repository
public interface JuradoRepository extends JpaRepository<Jurado, Integer> {

    /**
     * Obtiene todos los jurados que están activos.
     * 
     * @return Lista de {@link Jurado} cuyo atributo {@code activo} es {@code true}.
     */
    List<Jurado> findByActivoTrue();
}