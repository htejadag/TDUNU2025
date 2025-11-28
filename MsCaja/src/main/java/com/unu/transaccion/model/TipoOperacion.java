package com.unu.transaccion.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TIPO_OPERACION")
public class TipoOperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoOperacion")
    private Integer idTipoOperacion;

    @Column(name = "NombreOpercion")
    private String nombreOperacion;
}
