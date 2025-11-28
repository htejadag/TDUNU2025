package com.unu.ms.MsGradosTitulos.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoRequest {
    private Integer idSeguimiento;
    private Integer idEntidadCatalogo;
    private Integer entidadId;
    private Integer idEstado;
    private String comentario;
    private Integer idUsuario;
}
