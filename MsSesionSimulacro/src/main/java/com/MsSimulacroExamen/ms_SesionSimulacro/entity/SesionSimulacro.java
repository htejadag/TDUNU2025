package com.MsSimulacroExamen.ms_SesionSimulacro.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private Long idSesionSimulacro;

    @Column(nullable = false)
    private Long idSimulacro;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false, length = 50)
    private String aula;

    @Column(length = 50)
    private String sede;

    @Column(nullable = false, length = 20)
    private String estado;  // PROGRAMADO / EN_CURSO / FINALIZADO / CANCELADO
}
