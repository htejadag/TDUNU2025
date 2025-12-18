package com.unu.ms.MsConsejo.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionConsejoRequest {

    // Integer idSesion;
    // ConsejoModel idConsejo;
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
