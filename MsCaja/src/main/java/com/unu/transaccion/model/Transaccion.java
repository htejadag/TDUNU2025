package com.unu.transaccion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TRANSACCION")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Transaccion")
    private Integer idTransaccion;

    @Column(name = "Fecha_Hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "Correlativo", length = 50, nullable = false)
    private String correlativo;

    @Column(name = "MontoTotal", precision = 8, scale = 2, nullable = false)
    private BigDecimal montoTotal;

    @Column(name = "Descuento", precision = 8, scale = 2, nullable = false)
    private BigDecimal descuento;

    @Column(name = "Observaciones", length = 100)
    private String observaciones;

    @Column(name = "ID_Cajero", nullable = false)
    private Integer idCajero;

    @Column(name = "ID_CatalogoTO", nullable = false)
    private Integer idCatalogoTO;

    @Column(name = "ID_Cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "ID_CatalogoTP", nullable = false)
    private Integer idCatalogoTP;
}
