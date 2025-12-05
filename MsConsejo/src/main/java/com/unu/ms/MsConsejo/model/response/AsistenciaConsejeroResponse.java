package com.unu.ms.MsConsejo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaConsejeroResponse {
    private Integer idAsistencia;
    private Integer idSesion;
    private Integer idMiembro;
    private Integer idEstadoAsistencia;
    private Integer idUsuarioRegistro;
    private LocalDateTime fechaRegistro;
}
