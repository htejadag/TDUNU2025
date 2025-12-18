package com.unu.ms.MsConsejo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaSesionResponse {

    Integer idAsistencia;
    // SesionConsejoModel idSesion;
    // MiembroConsejoModel idMiembro;
    Integer idEstadoAsistencia;
    Integer usuarioRegistro;
    // LocalDateTime fechaRegistro;

}
