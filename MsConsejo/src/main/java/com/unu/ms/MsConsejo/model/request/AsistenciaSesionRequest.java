package com.unu.ms.MsConsejo.model.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaSesionRequest {

    // private Integer idAsistencia;
    // private SesionConsejoModel idSesion;
    // private MiembroConsejoModel idMiembro;
    private Integer idEstadoAsistencia;
    private Integer usuarioRegistro;
    private LocalDateTime fechaRegistro;

}
