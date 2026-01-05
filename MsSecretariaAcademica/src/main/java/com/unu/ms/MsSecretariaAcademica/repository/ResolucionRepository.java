package com.unu.ms.MsSecretariaAcademica.repository;
 
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionModel;

/**
 * Repositorio JPA para la gestión de resoluciones.
 *
 * Proporciona operaciones de acceso a datos para la entidad
 * {@link ResolucionModel}, permitiendo búsquedas por distintos
 * criterios del dominio resolutivo.
 */
@Repository
public interface ResolucionRepository extends JpaRepository<ResolucionModel, Integer> {

    /**
     * Busca una resolución por su número único.
     *
     * @param numeroResolucion número de la resolución
     * @return un {@link Optional} con la resolución encontrada,
     *         o vacío si no existe
     */
    public Optional<ResolucionModel> findByNumeroResolucion(String numeroResolucion);

    /**
     * Obtiene la lista de resoluciones asociadas a un expediente.
     *
     * @param idExpediente identificador del expediente
     * @return lista de resoluciones del expediente
     */
    public List<ResolucionModel> findByExpediente_IdExpediente(Integer idExpediente);

    /**
     * Obtiene la lista de resoluciones filtradas por estado.
     *
     * @param idEstado identificador del estado
     * @return lista de resoluciones en el estado indicado
     */
    public List<ResolucionModel> findByIdEstado(Integer idEstado);

    /**
     * Obtiene la lista de resoluciones filtradas por tipo de resolución.
     *
     * @param idTipoResolucion identificador del tipo de resolución
     * @return lista de resoluciones del tipo indicado
     */
    public List<ResolucionModel> findByIdTipoResolucion(Integer idTipoResolucion);

    /**
     * Obtiene la lista de resoluciones filtradas por indicador
     * de aprobación en sesión.
     *
     * @param aprobadoEnSesion indicador de aprobación en sesión
     * @return lista de resoluciones que cumplen el criterio
     */
    public List<ResolucionModel> findByAprobadoEnSesion(Integer aprobadoEnSesion);

    /**
     * Obtiene la lista de resoluciones filtradas por tipo y estado.
     *
     * @param idTipoResolucion identificador del tipo de resolución
     * @param idEstado identificador del estado
     * @return lista de resoluciones que cumplen ambos criterios
     */
    public List<ResolucionModel> findByIdTipoResolucionAndIdEstado(
            Integer idTipoResolucion,
            Integer idEstado
    );

}