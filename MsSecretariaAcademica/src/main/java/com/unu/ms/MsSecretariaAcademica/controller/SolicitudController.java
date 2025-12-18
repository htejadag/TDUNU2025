package com.unu.ms.MsSecretariaAcademica.controller;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.SolicitudRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.SolicitudResponse;
import com.unu.ms.MsSecretariaAcademica.service.SolicitudService;
import com.unu.ms.MsSecretariaAcademica.util.ApiRoutes;
import com.unu.ms.MsSecretariaAcademica.util.Mensajes;
import com.unu.ms.MsSecretariaAcademica.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.Solicitud.BASE)
@Tag(name = "Solicitud Controller")
public class SolicitudController {

    SolicitudService solicitudService;

    @GetMapping(value = ApiRoutes.Solicitud.LISTAR)
    public ResponseBase<List<SolicitudResponse>> listar() {
        List<SolicitudResponse> listaResponse = solicitudService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Solicitud.GUARDAR)
    public ResponseBase<SolicitudResponse> guardar(@RequestBody SolicitudRequest request) {
        SolicitudResponse response = solicitudService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Solicitud.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {
        solicitudService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Solicitud.ACTUALIZAR)
    public ResponseBase<SolicitudResponse> actualizar(@RequestParam(value = "id") Integer id, @RequestBody SolicitudRequest request) {
        SolicitudResponse response = solicitudService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Solicitud.OBTENER_POR_ID)
    public ResponseBase<SolicitudResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        SolicitudResponse response = solicitudService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }
    
}
