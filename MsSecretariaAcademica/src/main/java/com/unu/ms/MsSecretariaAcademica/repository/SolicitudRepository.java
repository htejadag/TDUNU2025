package com.unu.ms.MsSecretariaAcademica.repository;


import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsSecretariaAcademica.model.entity.SolicitudModel;

/**
 * Repositorio JPA para la gestión de solicitudes.
 *
 * Proporciona operaciones de acceso a datos para la entidad
 * {@link SolicitudModel}, permitiendo consultas por persona,
 * estado, tipo y rangos de fecha.
 */
@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudModel, Integer> {

    /**
     * Obtiene la lista de solicitudes asociadas a una persona.
     *
     * @param idPersona identificador de la persona
     * @return lista de solicitudes de la persona
     */
    public List<SolicitudModel> findByIdPersona(Integer idPersona);

    /**
     * Obtiene la lista de solicitudes filtradas por estado.
     *
     * @param idEstado identificador del estado
     * @return lista de solicitudes en el estado indicado
     */
    public List<SolicitudModel> findByIdEstado(Integer idEstado);

    /**
     * Obtiene la lista de solicitudes asociadas a una persona y estado específicos.
     *
     * @param idPersona identificador de la persona
     * @param idEstado identificador del estado
     * @return lista de solicitudes que cumplen ambos criterios
     */
    public List<SolicitudModel> findByIdPersonaAndIdEstado(Integer idPersona, Integer idEstado);

    /**
     * Obtiene la lista de solicitudes filtradas por tipo de solicitud.
     *
     * @param idTipoSolicitud identificador del tipo de solicitud
     * @return lista de solicitudes del tipo indicado
     */
    public List<SolicitudModel> findByIdTipoSolicitud(Integer idTipoSolicitud);

    /**
     * Obtiene la lista de solicitudes filtradas por tipo y estado.
     *
     * @param idTipoSolicitud identificador del tipo de solicitud
     * @param idEstado identificador del estado
     * @return lista de solicitudes que cumplen ambos criterios
     */
    public List<SolicitudModel> findByIdTipoSolicitudAndIdEstado(
            Integer idTipoSolicitud,
            Integer idEstado
    );

    /**
     * Obtiene la lista de solicitudes registradas dentro de un rango de fechas.
     *
     * @param fechaInicio fecha de inicio del rango
     * @param fechaFin fecha de fin del rango
     * @return lista de solicitudes dentro del rango de fechas
     */
    public List<SolicitudModel> findByFechaSolicitudBetween(String fechaInicio, String fechaFin);

    /**
     * Verifica la existencia de una solicitud por su código.
     *
     * @param codigoSolicitud código de la solicitud
     * @return {@code true} si existe una solicitud con el código indicado,
     *         {@code false} en caso contrario
     */
    public boolean existsByCodigoSolicitud(String codigoSolicitud);

}