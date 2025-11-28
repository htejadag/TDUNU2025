package com.unu.ms.MsGradosTitulos.model.entity;

import lombok.*;
import jakarta.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "seguimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private Integer id;

    @Column(name = "id_entidad_catalogo")
    private Integer idEntidadCatalogo;

    @Column(name = "entidad_id")
    private Integer entidadId;

    @Column(name = "id_estado")
    private Integer idEstado;

    private String comentario;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
}
