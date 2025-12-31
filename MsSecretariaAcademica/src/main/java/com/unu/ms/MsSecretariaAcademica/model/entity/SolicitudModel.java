package com.unu.ms.MsSecretariaAcademica.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "codigo_solicitud", nullable = false, unique = true)
    private String codigoSolicitud;

    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "id_tipo_solicitud")
    private Integer idTipoSolicitud;

    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "asunto")
    private String asunto;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;
    
}

