package com.proyect.MsSustentacion.model.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "sustentacion")
public class Sustentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sustentacion")
    private Long id;

    @Column(name = "tramite_id", nullable = false)
    private Long tramiteId;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false, length = 160)
    private String lugar;

    @Column(name = "estado_resul_id", nullable = false)
    private Short estadoResulId;

    @Column(name = "acta_numero", length = 60, unique = true)
    private String actaNumero;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

}
