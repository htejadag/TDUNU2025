package com.ms_simulacro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SesionSimulacroDto {
    private Long id;
    private Long simulacroId;
    private String aula;
    private String sede;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
