package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDate;

import com.unu.ms.MsSecretariaAcademica.model.entity.ExpedienteModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudRequest {

    Integer idSolicitud;
    String codigoSolicitud;
    Integer idPersona;
    Integer idTipoSolicitud;
    String asunto;
    String detalle;
    Integer idEstado;
    LocalDate fechaSolicitud;
    ExpedienteModel expediente;

}
