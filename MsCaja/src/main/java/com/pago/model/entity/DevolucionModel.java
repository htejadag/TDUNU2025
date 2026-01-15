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
    private Integer devolucionid;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "montodevuelto")
    private Double montodevuelto;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "pagoid")
    private Integer pagoid;

    @Column(name = "usuariocreacion")
    private String usuariocreacion;

    @Column(name = "fechacreacion")
    private LocalDateTime fechacreacion;

    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechamodificacion;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "eseliminado")
    private Boolean eseliminado;
}
