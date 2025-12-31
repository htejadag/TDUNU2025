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

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.Solicitud.BASE)
@Tag(name = "Solicitud Controller")
public class SolicitudController {

    SolicitudService solicitudService;

    @GetMapping(value = ApiRoutes.Solicitud.LISTAR)
    public ResponseBase<List<SolicitudResponse>> listar() {

        log.info("Inicio request: listar solicitudes");

        List<SolicitudResponse> listaResponse = solicitudService.listar();

        log.info("Fin request: listar solicitudes. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Solicitud.GUARDAR)
    public ResponseBase<SolicitudResponse> guardar(@RequestBody SolicitudRequest request) {

        log.info("Inicio request: guardar solicitud");
        log.debug("Datos de entrada guardar solicitud: {}", request);

        SolicitudResponse response = solicitudService.guardar(request);

        log.info("Fin request: guardar solicitud. Id generado: {}", response.getIdSolicitud());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Solicitud.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar solicitud");
        log.debug("Id solicitud a eliminar: {}", id);

        solicitudService.eliminar(id);

        log.info("Fin request: eliminar solicitud. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Solicitud.ACTUALIZAR)
    public ResponseBase<SolicitudResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody SolicitudRequest request) {

        log.info("Inicio request: actualizar solicitud");
        log.debug("Id solicitud a actualizar: {}", id);
        log.debug("Datos de entrada actualizar solicitud: {}", request);

        SolicitudResponse response = solicitudService.actualizar(id, request);

        log.info("Fin request: actualizar solicitud. Id actualizado: {}", id);

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Solicitud.OBTENER_POR_ID)
    public ResponseBase<SolicitudResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener solicitud por id");
        log.debug("Id solicitud: {}", id);

        SolicitudResponse response = solicitudService.obtenerPorId(id);

        log.info("Fin request: obtener solicitud por id. Id: {}", id);

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Solicitud.OBTENER_POR_PERSONA)
    public ResponseBase<List<SolicitudResponse>> obtenerPorPersona(
            @RequestParam(value = "idPersona") Integer idPersona) {

        log.info("Inicio request: obtener solicitudes por persona");
        log.debug("Id persona: {}", idPersona);

        List<SolicitudResponse> listaResponse = solicitudService.obtenerPorPersona(idPersona);

        log.info("Fin request: obtener solicitudes por persona. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Solicitud.OBTENER_POR_ESTADO)
    public ResponseBase<List<SolicitudResponse>> obtenerPorEstado(
            @RequestParam(value = "idEstado") Integer idEstado) {

        log.info("Inicio request: obtener solicitudes por estado");
        log.debug("Id estado: {}", idEstado);

        List<SolicitudResponse> listaResponse = solicitudService.obtenerPorEstado(idEstado);

        log.info("Fin request: obtener solicitudes por estado. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Solicitud.OBTENER_POR_TIPO)
    public ResponseBase<List<SolicitudResponse>> obtenerPorTipo(
            @RequestParam(value = "idTipoSolicitud") Integer idTipoSolicitud) {

        log.info("Inicio request: obtener solicitudes por tipo");
        log.debug("Id tipo solicitud: {}", idTipoSolicitud);

        List<SolicitudResponse> listaResponse = solicitudService.obtenerPorTipo(idTipoSolicitud);

        log.info("Fin request: obtener solicitudes por tipo. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Solicitud.OBTENER_POR_PERSONA_Y_ESTADO)
    public ResponseBase<List<SolicitudResponse>> obtenerPorPersonaYEstado(
            @RequestParam(value = "idPersona") Integer idPersona,
            @RequestParam(value = "idEstado") Integer idEstado) {

        log.info("Inicio request: obtener solicitudes por persona y estado");
        log.debug("Id persona: {}, Id estado: {}", idPersona, idEstado);

        List<SolicitudResponse> listaResponse =
                solicitudService.obtenerPorPersonaYEstado(idPersona, idEstado);

        log.info("Fin request: obtener solicitudes por persona y estado. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Solicitud.OBTENER_POR_TIPO_Y_ESTADO)
    public ResponseBase<List<SolicitudResponse>> obtenerPorTipoYEstado(
            @RequestParam(value = "idTipoSolicitud") Integer idTipoSolicitud,
            @RequestParam(value = "idEstado") Integer idEstado) {

        log.info("Inicio request: obtener solicitudes por tipo y estado");
        log.debug("Id tipo solicitud: {}, Id estado: {}", idTipoSolicitud, idEstado);

        List<SolicitudResponse> listaResponse =
                solicitudService.obtenerPorTipoYEstado(idTipoSolicitud, idEstado);

        log.info("Fin request: obtener solicitudes por tipo y estado. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Solicitud.OBTENER_POR_FECHA_RANGO)
    public ResponseBase<List<SolicitudResponse>> obtenerPorFechaRango(
            @RequestParam(value = "fechaInicio") String fechaInicio,
            @RequestParam(value = "fechaFin") String fechaFin) {

        log.info("Inicio request: obtener solicitudes por rango de fechas");
        log.debug("Fecha inicio: {}, Fecha fin: {}", fechaInicio, fechaFin);

        List<SolicitudResponse> listaResponse =
                solicitudService.obtenerPorFechaRango(fechaInicio, fechaFin);

        log.info("Fin request: obtener solicitudes por rango de fechas. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, listaResponse);
    }
    
}

