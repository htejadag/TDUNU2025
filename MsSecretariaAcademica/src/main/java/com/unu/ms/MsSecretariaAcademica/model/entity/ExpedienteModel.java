package com.unu.ms.MsSecretariaAcademica.model.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa un expediente dentro del sistema.
 *
 * Un expediente agrupa y gestiona la información relacionada
 * a un proceso o trámite específico del microservicio.
 * Contiene datos de identificación, estado, fechas relevantes
 * y su relación con solicitudes y resoluciones.
 */
@Entity
@Table(name = "expediente")
@Data
public class ExpedienteModel {

    /**
     * Identificador único del expediente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Integer idExpediente;

    /**
     * Código único del expediente.
     */
    @Column(name = "codigo_expediente", nullable = false, unique = true)
    private String codigoExpediente;

    /**
     * Identificador de la persona asociada al expediente.
     */
    @Column(name = "id_persona")
    private Integer idPersona;

    /**
     * Identificador del estado actual del expediente.
     */
    @Column(name = "id_estado")
    private Integer idEstado;

    /**
     * Descripción del expediente.
     */
    @Column(name = "descripcion")
    private String descripcion;

    /**
     * Fecha de apertura del expediente.
     */
    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    /**
     * Fecha de cierre del expediente.
     */
    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    /**
     * Fecha y hora de creación del registro del expediente.
     *
     * Se genera automáticamente al momento de persistir el registro.
     */
    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    /**
     * Identificador de la solicitud que dio origen al expediente.
     */
    @Column(name = "id_solicitud_origen", nullable = false)
    private Integer idSolicitudOrigen;

    /**
     * Resolución asociada al expediente.
     *
     * Relación uno a uno con la entidad de resolución.
     */
    @OneToOne(mappedBy = "expediente", cascade = CascadeType.ALL)
    private ResolucionModel resolucion;

}