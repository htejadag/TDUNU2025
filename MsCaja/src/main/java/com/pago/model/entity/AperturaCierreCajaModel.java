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
    @Column(name = "aperturacierrecajaid")
    private Integer aperturacierrecajaid;

    @Column(name = "fecha_apertura")
    private LocalDateTime fecha_apertura;

    @Column(name = "fecha_cierre", nullable = true)
    private LocalDateTime fecha_cierre;

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
