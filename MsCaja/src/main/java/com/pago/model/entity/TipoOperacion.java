package com.pago.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "tipo_operacion")
@Data
public class TipoOperacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoOperacion")
    private Integer idTipoOperacion;

    @Column(name = "NombreOpercion", length = 50, nullable = false)
    private String nombreOperacion;
}
