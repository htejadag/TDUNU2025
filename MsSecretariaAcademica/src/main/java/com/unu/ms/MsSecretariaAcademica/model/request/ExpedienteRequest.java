package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteRequest {

    // Integer idExpediente;
    String codigoExpediente;
    Integer idPersona;
    Integer idEstado;
    String descripcion;
    LocalDate fechaApertura;
    LocalDate fechaCierre;
    // LocalDateTime fechaCreacion;
    Integer idSolicitudOrigen;
    // ResolucionModel resolucion;

}
