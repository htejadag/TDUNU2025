package com.unu.transaccion.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CAJERO")
public class Cajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cajero")
    private Integer idCajero;

    private String nombre;
}
