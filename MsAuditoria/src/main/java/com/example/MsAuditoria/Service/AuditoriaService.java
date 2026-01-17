package com.example.MsAuditoria.Service;

import java.util.List;

import com.example.MsAuditoria.Model.Request.AuditoriaRequest;
import com.example.MsAuditoria.Model.Response.AuditoriaResponse;

public interface AuditoriaService {

     AuditoriaResponse guardar(AuditoriaRequest request);

    List<AuditoriaResponse> listar();

    List<AuditoriaResponse> listarPorEntidad(String entidad, String idEntidad);

    List<AuditoriaResponse> listarPorMicroservicio(String microservicio);

}
