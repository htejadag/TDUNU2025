package com.unu.ms.MsSecretariaAcademica.controller;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.SeguimientoRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.SeguimientoResponse;
import com.unu.ms.MsSecretariaAcademica.service.SeguimientoService;
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
@RequestMapping(ApiRoutes.Seguimiento.BASE)
@Tag(name = "Seguimiento Controller")
public class SeguimientoController {

    SeguimientoService seguimientoService;

    @GetMapping(value = ApiRoutes.Seguimiento.LISTAR)
    public ResponseBase<List<SeguimientoResponse>> listar() {

        log.info("Inicio request: listar seguimientos");

        List<SeguimientoResponse> listaResponse = seguimientoService.listar();

        log.info("Fin request: listar seguimientos. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Seguimiento.GUARDAR)
    public ResponseBase<SeguimientoResponse> guardar(@RequestBody SeguimientoRequest request) {

        log.info("Inicio request: guardar seguimiento");
        log.debug("Datos de entrada guardar seguimiento: {}", request);

        SeguimientoResponse response = seguimientoService.guardar(request);

        log.info("Fin request: guardar seguimiento. Id generado: {}", response.getIdSeguimiento());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Seguimiento.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar seguimiento");
        log.debug("Id seguimiento a eliminar: {}", id);

        seguimientoService.eliminar(id);

        log.info("Fin request: eliminar seguimiento. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Seguimiento.ACTUALIZAR)
    public ResponseBase<SeguimientoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody SeguimientoRequest request) {

        log.info("Inicio request: actualizar seguimiento");
        log.debug("Id seguimiento a actualizar: {}", id);
        log.debug("Datos de entrada actualizar seguimiento: {}", request);

        SeguimientoResponse response = seguimientoService.actualizar(id, request);

        log.info("Fin request: actualizar seguimiento. Id actualizado: {}", id);

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Seguimiento.OBTENER_POR_ID)
    public ResponseBase<SeguimientoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener seguimiento por id");
        log.debug("Id seguimiento: {}", id);

        SeguimientoResponse response = seguimientoService.obtenerPorId(id);

        log.info("Fin request: obtener seguimiento por id. Id: {}", id);

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Seguimiento.BUSCAR_POR_ENTIDAD)
    public ResponseBase<List<SeguimientoResponse>> buscarPorEntidad(
            @RequestParam Integer idEntidadCatalogo,
            @RequestParam Integer entidadId) {

        log.info("Inicio request: buscar seguimientos por entidad");
        log.debug("Id entidad catalogo: {}, Entidad id: {}", idEntidadCatalogo, entidadId);

        List<SeguimientoResponse> listaResponse =
                seguimientoService.buscarPorEntidad(idEntidadCatalogo, entidadId);

        log.info("Fin request: buscar seguimientos por entidad. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(listaResponse);
    }

}
