package com.pago.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "apertura_cierre_caja")
@Data
public class AperturaCierreCajaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apertura_cierre_cajaid")
    private int apertura_cierre_cajaid;

    @Column(name = "fecha_apertura")
    private LocalDateTime fecha_apertura;

    @Column(name = "fecha_cierre")
    private LocalDateTime fecha_cierre;

    @Column(name = "usuario_creacion")
    private int usuario_creacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fecha_creacion;

    @Column(name = "usuario_modificacion")
    private int usuario_modificacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fecha_modificacion;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "es_eliminado")
    private boolean es_eliminado;
}
