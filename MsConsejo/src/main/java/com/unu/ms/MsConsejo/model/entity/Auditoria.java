package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
    @Column(name = "id_auditoria")
    private Integer idAuditoria;

    @Column(name = "nombre_entidad", nullable = false)
    private String nombreEntidad;

    @Column(name = "entidad_id", nullable = false)
    private Integer entidadId;

    @Column(name = "accion", nullable = false)
    private String accion; // CREATE, UPDATE, DELETE, SOFT_DELETE

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Lob
    @Column(name = "datos_antes", columnDefinition = "TEXT")
    private String datosAntes;

    @Lob
    @Column(name = "datos_despues", columnDefinition = "TEXT")
    private String datosDespues;

    @CreationTimestamp
    @Column(name = "fecha_accion")
    private LocalDateTime fechaAccion;

    @Column(name = "observacion")
    private String observacion;

}