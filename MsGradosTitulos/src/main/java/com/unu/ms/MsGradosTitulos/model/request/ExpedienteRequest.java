package com.unu.ms.MsGradosTitulos.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteRequest {
    private Integer idExpediente;
    private String codigoExpediente;
    private Integer idPersona;
    private Integer idEstado;
    private String descripcion;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private Integer idUsuarioCreo;
}
