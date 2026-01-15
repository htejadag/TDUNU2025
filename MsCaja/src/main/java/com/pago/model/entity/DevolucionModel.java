package com.pago.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "devolucion")
public class DevolucionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "devolucionid")
    private Integer devolucionId;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "montodevuelto")
    private Double montoDevuelto;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "pagoid")
    private Integer pagoId;

    @Column(name = "usuariocreacion")
    private String usuarioCreacion;

    @Column(name = "fechacreacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "usuariomodificacion")
    private String usuarioModificacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "eseliminado")
    private Boolean esEliminado;
}
