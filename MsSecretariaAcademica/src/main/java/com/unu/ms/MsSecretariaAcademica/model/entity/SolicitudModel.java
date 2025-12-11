package com.unu.ms.MsSecretariaAcademica.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "solicitud")
@Data
public class SolicitudModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @Column(name = "codigo_solicitud", length = 50)
    private String codigoSolicitud;

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "id_tipo_solicitud")
    private Integer idTipoSolicitud;

    @Column(name = "id_expediente")
    private Integer idExpediente;

    @Column(name = "asunto", length = 255)
    private String asunto;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;

    @Column(name = "id_usuario_creo")
    private Integer idUsuarioCreo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

}
