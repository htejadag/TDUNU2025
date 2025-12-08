package com.unu.ms.MsGradosTitulos.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoResponse {
    private Integer idSeguimiento;
    private Integer idEntidadCatalogo;
    private Integer entidadId;
    private Integer idEstado;
    private String comentario;
    private Integer idUsuario;
    private LocalDateTime fechaRegistro;
}
