package com.unu.ms.MsGradosTitulos.model.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "codigo_expediente")
    private String codigoExpediente;

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "id_estado")
    private Integer idEstado;

    private String descripcion;

    @Column(name = "fecha_aperura")
    private LocalDate fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @Column(name = "id_usuario_creo")
    private Integer usuarioCreo;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @OneToMany(mappedBy = "expediente")
    private List<ResolucionModel> resoluciones;

}
