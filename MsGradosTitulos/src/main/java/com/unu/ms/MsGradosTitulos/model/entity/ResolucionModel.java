package com.unu.ms.MsGradosTitulos.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Entity
@Table(name = "resolucion")
@Data
public class ResolucionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion")
    private Integer idResolucion;

    @Column(name = "numero_resolucion")
    private String numeroResolucion;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    private ExpedienteModel expediente;

    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "id_tipo_resolucion")
    private Integer idTipoResolucion;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "resumen")
    private String resumen;

    @Column(name = "fundamento")
    private String fundamento;

    @Column(name = "articulado_general")
    private String articuladoGeneral;

    @Column(name = "aprobado_en_sesion")
    private Boolean aprobadoEnSesion;

    @Column(name = "id_usuario_creo")
    private Integer usuarioCreo;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @OneToMany(mappedBy = "resolucion")
    private List<ResolucionArticuloModel> articulos;
    
}
