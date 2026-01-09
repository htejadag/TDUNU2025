package msposgrado.Repository;

import msposgrado.Model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Revision}.
 * 
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "revision"
 * en la base de datos mediante Spring Data JPA.
 * 
 * Actualmente no incluye métodos personalizados, pero hereda todos los
 * métodos de {@link JpaRepository}, como {@code save}, {@code findById}, {@code findAll},
 * {@code deleteById}, etc.
 */
@Repository
public interface RevisionRepository extends JpaRepository<Revision, Integer> {
}