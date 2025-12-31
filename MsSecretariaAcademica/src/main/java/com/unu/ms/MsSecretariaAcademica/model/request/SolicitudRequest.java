package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudRequest {

    // Integer idSolicitud;
    String codigoSolicitud;
    Integer idPersona;
    Integer idTipoSolicitud;
    Integer idEstado;
    String asunto;
    String detalle;
    LocalDate fechaSolicitud;

}
