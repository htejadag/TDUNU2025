package com.unu.ms.MsGradosTitulos.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionResponse {
    
    private Integer id;
    private String numeroResolucion;
    // private ExpedienteModel expediente;
    private Integer idSolicitud;
    private Integer idEstado;
    private Integer idTipoResolucion;
    private LocalDate fechaEmision;
    private String resumen;
    private String fundamento;
    private String articuladoGeneral;
    private Boolean aprobadoEnSesion;
    private Integer usuarioCreo;
    // private Timestamp fechaCreacion;
    // private List<ResolucionArticuloModel> articulos;
}

