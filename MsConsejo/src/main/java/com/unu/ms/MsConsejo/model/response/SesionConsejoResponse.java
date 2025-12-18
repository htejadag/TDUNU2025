package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionConsejoResponse {

    // Integer idSesion;
    // ConsejoModel consejo;
    String numeroSesion;
    String nombreSesion;
    LocalDate fechaSesion;
    Integer idTipoSesion;
    String descripcion;
    // Integer idEstado;
    // Integer usuarioRegistro;
    // LocalDateTime fechaCreacion;
    // List<AsistenciaSesionModel> asistencias;

}
