package com.ms_simulacro.dto.simulacro;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulacroCreateDto {
    private String nombre;
    private Long cicloId;
    private LocalDate fecha;
    private String horaInicio;
    private String horaFin;
    private String tipoSimulacro;
    private String estado;
    private String usuarioCreacion;
    private String usuarioModificacion;

}
