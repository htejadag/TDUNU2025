package com.example.MsAuditoria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsAuditoria.Model.Response.AuditoriaResponse;
import com.example.MsAuditoria.Service.AuditoriaService;
import com.example.MsAuditoria.Util.ApiRoutes;
import com.example.MsAuditoria.Util.Mensajes;
import com.example.MsAuditoria.Util.ResponseBase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(ApiRoutes.BASEGENERAL+ApiRoutes.Auditoria.BASE)
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping(value = ApiRoutes.Auditoria.LISTAR)
    public ResponseBase <List<AuditoriaResponse>> listar() {
        List<AuditoriaResponse> lista = auditoriaService.listar();
        return ResponseBase.ok(Mensajes.AUDITORIA_LISTA,lista);
    }

    @GetMapping(value = ApiRoutes.Auditoria.LISTAR_POR_ENTIDAD)
    public ResponseBase<List<AuditoriaResponse>> listarPorEntidad(
            @PathVariable String entidad,
            @PathVariable String idEntidad) {

        List<AuditoriaResponse> lista = auditoriaService.listarPorEntidad(entidad, idEntidad);
        return ResponseBase.ok(Mensajes.AUDITORIA_POR_ENTIDAD,lista);
    }

    @GetMapping(value = ApiRoutes.Auditoria.LISTAR_POR_MICROSERVICIO)
    public ResponseBase<List<AuditoriaResponse>> listarPorMicroservicio(
            @PathVariable String microservicio) {

        List<AuditoriaResponse> lista = auditoriaService.listarPorMicroservicio(microservicio);
        return ResponseBase.ok(Mensajes.AUDITORIA_POR_MICROSERVICIO,lista);
    }

}
