package com.unu.ms.MsGradosTitulos.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteResponse {
    private Integer idExpediente;
    private String codigoExpediente;
    private Integer idPersona;
    private Integer idEstado;
    private String descripcion;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private Integer idUsuarioCreo;
    private LocalDateTime fechaCreacion;
}
