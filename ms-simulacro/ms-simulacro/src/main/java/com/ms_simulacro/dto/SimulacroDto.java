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
public class SimulacroDto {
    private Long id;
    private String nombre;
    private Long cicloId;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String tipoSimulacro;
    private String estado;
    private String usuarioCreacion;
    private String usuarioModificacion;

}
