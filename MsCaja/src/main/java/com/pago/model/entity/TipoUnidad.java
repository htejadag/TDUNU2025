package com.pago.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "tipo_unidad")
@Data
public class TipoUnidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoUnidad")
    private Integer idTipoUnidad;

    @Column(name = "NombreUnidad", length = 50, nullable = false)
    private String nombreUnidad;
}

