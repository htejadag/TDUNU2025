package com.pago.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "catalogo")
@Data
public class Catalogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Catalogo")
    private Integer idCatalogo;

    @Column(name = "TipoCatalogo", length = 50, nullable = false)
    private String tipoCatalogo;

    @Column(name = "Nombre", length = 50, nullable = false)
    private String nombre;
}

