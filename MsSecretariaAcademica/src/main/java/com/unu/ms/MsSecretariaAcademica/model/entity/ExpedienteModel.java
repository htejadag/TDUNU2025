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

@Entity
@Table(name = "expediente")
@Data
public class ExpedienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Integer idExpediente;

    @Column(name = "codigo_expediente", nullable = false, unique = true)
    private String codigoExpediente;

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    /** 
     * Referencia lógica a la solicitud que originó el expediente
     * (NO relación fuerte para evitar acoplamiento entre MS)
     */
    @Column(name = "id_solicitud_origen", nullable = false)
    private Integer idSolicitudOrigen;

    @OneToOne(mappedBy = "expediente", cascade = CascadeType.ALL)
    private ResolucionModel resolucion;
}

