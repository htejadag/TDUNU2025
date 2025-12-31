package com.unu.ms.MsSecretariaAcademica.controller;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.ExpedienteRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ExpedienteResponse;
import com.unu.ms.MsSecretariaAcademica.service.ExpedienteService;
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
@RequestMapping(ApiRoutes.Expediente.BASE)
@Tag(name = "Expediente Controller")
public class ExpedienteController {

    ExpedienteService expedienteService;

    @GetMapping(value = ApiRoutes.Expediente.LISTAR)
    public ResponseBase<List<ExpedienteResponse>> listar() {

        log.info("Inicio request: listar expedientes");

        List<ExpedienteResponse> listaResponse = expedienteService.listar();

        log.info("Fin request: listar expedientes. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Expediente.GUARDAR)
    public ResponseBase<ExpedienteResponse> guardar(@RequestBody ExpedienteRequest request) {

        log.info("Inicio request: guardar expediente");
        log.debug("Datos de entrada guardar expediente: {}", request);

        ExpedienteResponse response = expedienteService.guardar(request);

        log.info("Fin request: guardar expediente. Id generado: {}", response.getIdExpediente());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Expediente.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar expediente");
        log.debug("Id expediente a eliminar: {}", id);

        expedienteService.eliminar(id);

        log.info("Fin request: eliminar expediente. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Expediente.ACTUALIZAR)
    public ResponseBase<ExpedienteResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ExpedienteRequest request) {

        log.info("Inicio request: actualizar expediente");
        log.debug("Id expediente a actualizar: {}", id);
        log.debug("Datos de entrada actualizar expediente: {}", request);

        ExpedienteResponse response = expedienteService.actualizar(id, request);

        log.info("Fin request: actualizar expediente. Id actualizado: {}", id);

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Expediente.OBTENER_POR_ID)
    public ResponseBase<ExpedienteResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener expediente por id");
        log.debug("Id expediente: {}", id);

        ExpedienteResponse response = expedienteService.obtenerPorId(id);

        log.info("Fin request: obtener expediente por id. Id: {}", id);

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Expediente.BUSCAR_POR_CODIGO)
    public ResponseBase<ExpedienteResponse> buscarPorCodigo(@RequestParam String codigo) {

        log.info("Inicio request: buscar expediente por codigo");
        log.debug("Codigo expediente: {}", codigo);

        ExpedienteResponse response = expedienteService.buscarPorCodigo(codigo);

        log.info("Fin request: buscar expediente por codigo");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Expediente.BUSCAR_POR_PERSONA)
    public ResponseBase<List<ExpedienteResponse>> buscarPorPersona(@RequestParam Integer idPersona) {

        log.info("Inicio request: buscar expedientes por persona");
        log.debug("Id persona: {}", idPersona);

        List<ExpedienteResponse> listaResponse = expedienteService.buscarPorPersona(idPersona);

        log.info("Fin request: buscar expedientes por persona. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Expediente.BUSCAR_POR_TIPO_Y_ESTADO)
    public ResponseBase<List<ExpedienteResponse>> buscarPorPersonaYEstado(
            @RequestParam Integer idPersona,
            @RequestParam Integer idEstado) {

        log.info("Inicio request: buscar expedientes por persona y estado");
        log.debug("Id persona: {}, Id estado: {}", idPersona, idEstado);

        List<ExpedienteResponse> listaResponse = expedienteService.buscarPorPersonaYEstado(idPersona, idEstado);

        log.info("Fin request: buscar expedientes por persona y estado. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }  
    
    @GetMapping(value = ApiRoutes.Expediente.BUSCAR_POR_ESTADO)
    public ResponseBase<List<ExpedienteResponse>> buscarPorEstado(@RequestParam Integer idEstado) {

        log.info("Inicio request: buscar expedientes por estado");
        log.debug("Id estado: {}", idEstado);

        List<ExpedienteResponse> listaResponse = expedienteService.buscarPorEstado(idEstado);

        log.info("Fin request: buscar expedientes por estado. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}