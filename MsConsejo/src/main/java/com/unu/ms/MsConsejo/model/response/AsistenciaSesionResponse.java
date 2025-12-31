package com.unu.ms.MsConsejo.model.response;

import java.time.LocalDateTime;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaSesionResponse {

    Integer idAsistencia;
    SesionConsejoModel idSesion;
    MiembroConsejoModel idMiembro;
    Integer idEstadoAsistencia;
    Integer usuarioRegistro;
    LocalDateTime fechaRegistro;

}
