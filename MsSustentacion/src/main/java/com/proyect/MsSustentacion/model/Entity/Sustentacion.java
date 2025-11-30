package com.proyect.MsSustentacion.model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Long id;

    @NotNull(message = "El ID del trámite es obligatorio.")
    @Column(name = "tramite_id", nullable = false)
    private Long tramiteId;

    @NotNull(message = "La fecha es obligatoria.")
    @FutureOrPresent(message = "La fecha no puede ser pasada.")

    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria.")
    @Column(nullable = false)
    private LocalTime hora;

    @NotBlank(message = "El lugar es obligatorio.")
    @Column(nullable = false, length = 160)
    private String lugar;

    @NotNull(message = "El estado es obligatorio.")
    @Column(name = "estado_resul_id", nullable = false)
    private Short estadoResulId;

    @NotNull(message = "El número de acta es obligatorio.")
    @Column(name = "acta_numero", length = 60, unique = true)
    private String actaNumero;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

}
