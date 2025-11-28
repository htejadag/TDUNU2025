package com.unu.transaccion.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cliente")
    private Integer idCliente;

    // Puedes agregar más campos si los necesitas mostrar luego
    private String nombre;
}
