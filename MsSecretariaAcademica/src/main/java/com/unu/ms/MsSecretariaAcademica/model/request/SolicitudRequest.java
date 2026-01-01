package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para la creación y actualización de solicitudes.
 *
 * Representa la información necesaria para registrar una solicitud,
 * incluyendo su identificación, tipo, estado y contenido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudRequest {

    /**
     * Código único de la solicitud.
     */
    String codigoSolicitud;

    /**
     * Identificador de la persona que realiza la solicitud.
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
     * Detalle o descripción completa de la solicitud.
     */
    String detalle;

    /**
     * Fecha en la que se registra la solicitud.
     */
    LocalDate fechaSolicitud;

    /**
     * Otros atributos de la entidad
     * 
     * Integer idSolicitud;
     */

}
