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

    @Column(name = "Correlativo", nullable = false, length = 50)
    private String correlativo;

    @Column(name = "MontoTotal", nullable = false, precision = 8, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "Descuento", nullable = false, precision = 8, scale = 2)
    private BigDecimal descuento;

    @Column(name = "Observaciones", length = 100)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "ID_Cajero", nullable = false)
    private Cajero cajero;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_CatalogoTO", nullable = false)
    private TipoOperacion tipoOperacion;

    @ManyToOne
    @JoinColumn(name = "ID_CatalogoTP", nullable = false)
    private TipoPago tipoPago;
}
