package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "auditoria")
@Data
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuditoria;

    @Column(nullable = false)
    private String nombreEntidad;      // Solicitud, Expediente, Resolucion

    @Column(nullable = false)
    private Long entidadId;

    @Column(nullable = false)
    private String accion;             // CREATE, UPDATE, DELETE, SOFT_DELETE

    private Long usuarioId;

    @Column(columnDefinition = "TEXT")
    private String datosAntes;

    @Column(columnDefinition = "TEXT")
    private String datosDespues;

    @CreationTimestamp
    private LocalDateTime fechaAccion;

    private String observacion;
}
