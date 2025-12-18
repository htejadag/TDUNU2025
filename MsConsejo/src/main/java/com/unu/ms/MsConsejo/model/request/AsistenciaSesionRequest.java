package com.unu.ms.MsConsejo.model.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaSesionRequest {

    // Integer idAsistencia;
    // SesionConsejoModel idSesion;
    // MiembroConsejoModel idMiembro;
    Integer idEstadoAsistencia;
    Integer usuarioRegistro;
    LocalDateTime fechaRegistro;

}
