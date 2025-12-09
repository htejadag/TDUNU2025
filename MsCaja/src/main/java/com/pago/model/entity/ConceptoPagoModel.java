package com.pago.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "concepto_pago")
@Data
public class ConceptoPagoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concepto_pagoid")
    private Integer concepto_pagoid;

    @ManyToOne
    @JoinColumn(name = "clasificadorid", foreignKey = @ForeignKey(name = "FK_concepto_pago_clasificador_ingreso"))
    private ClasificadorIngresoModel clasificador_ingreso;

    @Column(name = "nombre_concepto")
    private String nombre_concepto;

    @Column(name = "precio_base")
    private float precio_base;

    @Column(name = "usuario_creacion")
    private Integer usuario_creacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fecha_creacion;

    @Column(name = "usuario_modificacion", nullable = true)
    private Integer usuario_modificacion;

    @Column(name = "fecha_modificacion", nullable = true)
    private LocalDateTime fecha_modificacion;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "es_eliminado")
    private Boolean es_eliminado;

    @Transient
    private String mensaje;

}
