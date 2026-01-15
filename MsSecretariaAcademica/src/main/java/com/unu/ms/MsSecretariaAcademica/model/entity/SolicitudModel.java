package com.unu.ms.MsSecretariaAcademica.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad que representa una solicitud dentro del sistema.
 *
 * Una solicitud corresponde a un requerimiento o trámite
 * ingresado al microservicio, el cual puede dar origen a
 * un expediente y posteriormente a una resolución.
 */
@Entity
@Table(name = "solicitud")
@Data
public class SolicitudModel {

    /**
     * Identificador único de la solicitud.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    /**
     * Código único de la solicitud.
     */
    @Column(name = "codigo_solicitud", nullable = false, unique = true)
    private String codigoSolicitud;

    /**
     * Identificador de la persona asociada a la solicitud.
     */
    @Column(name = "id_persona")
    private Integer idPersona;

    /**
     * Identificador del tipo de solicitud.
     */
    @Column(name = "id_tipo_solicitud")
    private Integer idTipoSolicitud;

    /**
     * Identificador del estado actual de la solicitud.
     */
    @Column(name = "id_estado")
    private Integer idEstado;

    /**
     * Asunto principal de la solicitud.
     */
    @Column(name = "asunto")
    private String asunto;

    /**
     * Detalle o descripción de la solicitud.
     */
    @Column(name = "detalle")
    private String detalle;

    /**
     * Fecha en la que se registró la solicitud.
     */
    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;

}