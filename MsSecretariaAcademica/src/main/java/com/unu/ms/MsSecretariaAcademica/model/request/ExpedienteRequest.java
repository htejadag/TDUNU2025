package com.unu.ms.MsSecretariaAcademica.model.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionModel;
import com.unu.ms.MsSecretariaAcademica.model.entity.SolicitudModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpedienteRequest {

    Integer idExpediente;
    String codigoExpediente;
    Integer idPersona;
    Integer idEstado;
    String descripcion;
    LocalDate fechaApertura;
    LocalDate fechaCierre;
    LocalDateTime fechaCreacion;
    List<SolicitudModel> solicitudes;
    List<ResolucionModel> resoluciones;

}
