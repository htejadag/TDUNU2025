package com.pago.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "tipo_pago")
@Data
public class TipoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipPago")
    private Integer idTipoPago;

    @Column(name = "NombrePago", length = 50, nullable = false)
    private String nombrePago;
}
