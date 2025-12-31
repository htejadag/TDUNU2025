package com.unu.ms.MsSecretariaAcademica.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteResponse {

    Integer idExpediente;
    String codigoExpediente;
    Integer idPersona;
    Integer idEstado;
    String descripcion;
    LocalDate fechaApertura;
    LocalDate fechaCierre;
    LocalDateTime fechaCreacion;
    Integer idSolicitudOrigen;
    // ResolucionModel resolucion;

}
