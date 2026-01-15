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
@Table(name = "nota_credito")
public class NotaCreditoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notacreditoid")
    private Integer notaCreditoId;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "observacion")
    private String observacion;

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

    @Column(name = "devolucionid")
    private Integer devolucionId;
}
