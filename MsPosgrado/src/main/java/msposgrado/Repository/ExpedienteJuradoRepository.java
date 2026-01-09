package msposgrado.Repository;

import msposgrado.Model.ExpedienteJurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link ExpedienteJurado}.
 * 
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "expediente_jurado"
 * en la base de datos mediante Spring Data JPA.
 * 
 * Permite gestionar la relación entre expedientes y jurados, incluyendo la creación,
 * actualización, eliminación y búsqueda de asignaciones de jurados a expedientes.
 */
@Repository
public interface ExpedienteJuradoRepository extends JpaRepository<ExpedienteJurado, Integer> {
    // Se pueden agregar métodos personalizados según sea necesario, por ejemplo:
    // List<ExpedienteJurado> findByExpedienteId(Integer idExpediente);
}