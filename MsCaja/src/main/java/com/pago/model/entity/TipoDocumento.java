package com.pago.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "tipo_documento")
@Data
public class TipoDocumento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoDocumento")
    private Integer idTipoDocumento;

    @Column(name = "NombreDocumento", length = 50, nullable = false)
    private String nombreDocumento;
}

