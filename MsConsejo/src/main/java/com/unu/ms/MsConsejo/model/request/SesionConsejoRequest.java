package com.unu.ms.MsConsejo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionConsejoRequest {
    private Integer idSesion;
    private Integer idConsejo;
    private String numeroSesion;
    private String nombreSesion;
    private String descripcion;
    private LocalDate fechaSesion;
    private Integer idTipoSesion;
    private Integer idUsuarioRegistro;
    private LocalDateTime fechaRegistro;
}
