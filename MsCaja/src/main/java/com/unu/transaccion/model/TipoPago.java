package com.unu.transaccion.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TIPO_PAGO")
public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipPago") // Ojo: en tu DB script dice 'ID_TipPago'
    private Integer idTipoPago;

    private String nombrePago;
}
