package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta para solicitudes.
 *
 * Representa la información de una solicitud registrada en el sistema,
 * utilizada para la consulta y visualización de sus datos principales
 * sin exponer la entidad de persistencia.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudResponse {

    /**
     * Identificador único de la solicitud.
     */
    Integer idSolicitud;

    /**
     * Código único que identifica la solicitud.
     */
    String codigoSolicitud;

    /**
     * Identificador de la persona asociada a la solicitud.
     */
    Integer idPersona;

    /**
     * Identificador del tipo de solicitud.
     */
    Integer idTipoSolicitud;

    /**
     * Identificador del estado actual de la solicitud.
     */
    Integer idEstado;

    /**
     * Asunto principal de la solicitud.
     */
    String asunto;

    /**
     * Descripción detallada de la solicitud.
     */
    String detalle;

    /**
     * Fecha en la que se registró la solicitud.
     */
    LocalDate fechaSolicitud;

}