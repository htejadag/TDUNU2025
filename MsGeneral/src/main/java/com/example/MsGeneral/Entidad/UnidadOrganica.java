package com.example.MsGeneral.Entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UnidadOrganica")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadOrganica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Short idUnidad;

    @Column(name = "nombre", length = 120, nullable = false)
    private String nombre;

    @Column(name = "siglas", length = 20, nullable = false)
    private String siglas;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion", length = 50, nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion", length = 50)
    private String usuarioModificacion;
}
