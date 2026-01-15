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
    private Integer aperturaCierreCajaId;

    @Column(name = "fecha_apertura")
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre", nullable = true)
    private LocalDateTime fechaCierre;

    @Column(name = "usuario_creacion")
    private Integer usuarioCreacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_modificacion", nullable = true)
    private Integer usuarioModificacion;

    @Column(name = "fecha_modificacion", nullable = true)
    private LocalDateTime fechaModificacion;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "es_eliminado")
    private Boolean esEliminado;

    @Transient
    private String mensaje;

}
