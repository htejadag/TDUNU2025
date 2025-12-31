package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionRequest {

    // Integer idResolucion;
    String numeroResolucion;
    Integer idEstado;
    Integer idTipoResolucion;
    LocalDate fechaEmision;
    String resumen;
    String fundamento;
    String articuladoGeneral;
    Integer aprobadoEnSesion;
    Integer expediente;
    // List<ResolucionArticuloModel> articulos;

}
