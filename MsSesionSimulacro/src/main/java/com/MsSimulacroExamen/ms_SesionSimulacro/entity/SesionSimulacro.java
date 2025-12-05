package com.MsSimulacroExamen.ms_SesionSimulacro.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sesion_simulacro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SesionSimulacro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idSesionSimulacro;

    @Column(name = "simulacro_id", nullable = false)
    private Long simulacroId;
    // Identificación
    @Column(name = "nombre_sesion", length = 100)
    private String nombreSesion;    // "Turno Mañana", "Sesión A"

    // Ubicación (pueden ser relaciones o solo IDs según tu modelo)
    @Column(name = "sede_id")
    private Long sedeId;

    @Column(name = "aula_id")
    private Long aulaId;

    // Horario
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    // Capacidad
    @Column(name = "capacidad_maxima")
    private Integer capacidadMaxima;

    @Column(name = "inscritos")
    private Integer inscritos;

    // Estado
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;  // PROGRAMADA / EN_CURSO / FINALIZADA / CANCELADA

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    // Auditoría
    @CreationTimestamp
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @UpdateTimestamp
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_registro", nullable = false, length = 100)
    private String usuarioRegistro;

    @Column(name = "usuario_modificacion", length = 100)
    private String usuarioModificacion;
}
