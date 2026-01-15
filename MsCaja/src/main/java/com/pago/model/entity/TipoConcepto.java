package com.pago.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "tipo_concepto")
@Data
public class TipoConcepto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoConcenpto")
    private Integer idTipoConcepto;

    @Column(name = "NombreConcepto", length = 50, nullable = false)
    private String nombreConcepto;
}

