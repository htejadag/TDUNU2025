package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionResponse {
    
    Integer idResolucion;
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

