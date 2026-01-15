package com.unu.ms.MsConsejo.model.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionConsejoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

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
