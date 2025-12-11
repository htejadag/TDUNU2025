package com.unu.ms.MsSecretariaAcademica.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "numero_resolucion", length = 100)
    private String numeroResolucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expediente", insertable = false, updatable = false)
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
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "resolucion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResolucionArticuloModel> articulos;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

}
