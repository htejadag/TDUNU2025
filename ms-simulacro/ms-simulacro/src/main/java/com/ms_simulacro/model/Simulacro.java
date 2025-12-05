package com.ms_simulacro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "simulacro")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Simulacro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "ciclo_id")
    private Long cicloId;

    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "tipo_simulacro")
    private String tipoSimulacro;

    private String estado;

    // auditoria
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @PrePersist()
    private void persistence() {
        this.fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate()
    private void update() {
        this.fechaModificacion = LocalDateTime.now();
    }

}
