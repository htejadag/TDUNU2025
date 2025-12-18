package com.unu.ms.MsSecretariaAcademica.model.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @Column(name = "fecha_aperura")
    private LocalDate fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @CreationTimestamp  
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "expediente", cascade = CascadeType.ALL)
    private List<SolicitudModel> solicitudes;

    @OneToMany(mappedBy = "expediente")
    private List<ResolucionModel> resoluciones;

}
