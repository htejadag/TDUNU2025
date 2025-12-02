package com.example.MsGeneral.Model.Entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Catalogos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_catalogo")
    private Integer idCatalogo;

    @Column(name = "nombre_tabla", length = 100, nullable = false)
    private String nombreTabla;

    @Column(name = "codigo", length = 32, nullable = false)
    private String codigo;

    @Column(name = "descripcion", length = 255, nullable = false)
    private String descripcion;

    @Column(name = "abreviatura", length = 20)
    private String abreviatura;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion", length = 50, nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion", length = 50)
    private String usuarioModificacion;
}
