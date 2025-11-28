package com.unu.ms.MsGradosTitulos.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionRequest {
    private Integer idResolucion;
    private String numeroResolucion;
    private Integer idExpediente;
    private Integer idSolicitud;
    private Integer idEstado;
    private Integer idTipoResolucion;
    private LocalDate fechaEmision;
    private String resumen;
    private String fundamento;
    private String articuladoGeneral;
    private Integer aprobadoEnSesion;
    private Integer idUsuarioCreo;
}
