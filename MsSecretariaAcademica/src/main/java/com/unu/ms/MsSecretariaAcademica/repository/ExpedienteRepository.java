package com.unu.ms.MsSecretariaAcademica.repository;
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unu.ms.MsSecretariaAcademica.model.entity.ExpedienteModel;

/**
 * Repositorio JPA para la gestión de expedientes.
 *
 * Proporciona operaciones de acceso a datos para la entidad
 * {@link ExpedienteModel}, incluyendo validaciones de existencia
 * y búsquedas por distintos criterios del dominio.
 */
@Repository
public interface ExpedienteRepository extends JpaRepository<ExpedienteModel, Integer> {

    /**
     * Busca un expediente por su código único.
     *
     * @param codigoExpediente código del expediente
     * @return un {@link Optional} con el expediente encontrado,
     *         o vacío si no existe
     */
    public Optional<ExpedienteModel> findByCodigoExpediente(String codigoExpediente);

    /**
     * Obtiene la lista de expedientes asociados a una persona.
     *
     * @param idPersona identificador de la persona
     * @return lista de expedientes de la persona
     */
    public List<ExpedienteModel> findByIdPersona(Integer idPersona);

    /**
     * Obtiene la lista de expedientes filtrados por estado.
     *
     * @param idEstado identificador del estado
     * @return lista de expedientes en el estado indicado
     */
    public List<ExpedienteModel> findByIdEstado(Integer idEstado);

    /**
     * Obtiene la lista de expedientes asociados a una persona y estado específicos.
     *
     * @param idPersona identificador de la persona
     * @param idEstado identificador del estado
     * @return lista de expedientes que cumplen ambos criterios
     */
    public List<ExpedienteModel> findByIdPersonaAndIdEstado(Integer idPersona, Integer idEstado);

    /**
     * Verifica la existencia de un expediente por su código.
     *
     * @param codigoExpediente código del expediente
     * @return {@code true} si existe un expediente con el código indicado,
     *         {@code false} en caso contrario
     */
    public boolean existsByCodigoExpediente(String codigoExpediente);

}
