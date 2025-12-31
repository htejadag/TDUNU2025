package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionConsejoResponse {

    Integer idSesion;
    Integer consejo;
    String numeroSesion;
    String nombreSesion;
    LocalDate fechaSesion;
    Integer idTipoSesion;
    String descripcion;
    Integer idEstado;
    Integer usuarioRegistro;
    LocalDateTime fechaCreacion;
    // List<AsistenciaSesionModel> asistencias;

}
