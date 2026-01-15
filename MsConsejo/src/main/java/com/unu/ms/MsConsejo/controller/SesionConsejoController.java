package com.unu.ms.MsConsejo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.service.SesionConsejoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.SesionConsejo.BASE)
@Tag(name = "SesionConsejo Controller")
public class SesionConsejoController {

    SesionConsejoService sesionConsejoService;

    @GetMapping(value = ApiRoutes.SesionConsejo.LISTAR)
    public ResponseBase<List<SesionConsejoResponse>> listar() {

        log.info("Inicio request: listar sesiones de consejo");

        List<SesionConsejoResponse> listaResponse = sesionConsejoService.listar();

        log.info(
                "Fin request: listar sesiones de consejo. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.SesionConsejo.GUARDAR)
    public ResponseBase<SesionConsejoResponse> guardar(@RequestBody SesionConsejoRequest request) {

        log.info("Inicio request: guardar sesión de consejo");
        log.debug("Request guardar sesión consejo: {}", request);

        SesionConsejoResponse response = sesionConsejoService.guardar(request);
       
        log.info(
                "Fin request: guardar sesión de consejo. Id generado: {}",
                response.getIdSesion()
        );

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.SesionConsejo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar sesión de consejo");
        log.debug("Id sesión consejo a eliminar: {}", id);

        sesionConsejoService.eliminar(id);

        log.info(
                "Fin request: eliminar sesión de consejo. Id eliminado: {}",
                id
        );

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.SesionConsejo.ACTUALIZAR)
    public ResponseBase<SesionConsejoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody SesionConsejoRequest request) {

        log.info("Inicio request: actualizar sesión de consejo");
        log.debug("Id sesión consejo a actualizar: {}", id);
        log.debug("Request actualizar sesión consejo: {}", request);

        SesionConsejoResponse response = sesionConsejoService.actualizar(id, request);

        log.info(
                "Fin request: actualizar sesión de consejo. Id actualizado: {}",
                response.getIdSesion()
        );

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.OBTENER_POR_ID)
    public ResponseBase<SesionConsejoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener sesión de consejo por id");
        log.debug("Id sesión consejo: {}", id);

        SesionConsejoResponse response = sesionConsejoService.obtenerPorId(id);

        log.info("Fin request: obtener sesión de consejo por id");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_CONSEJO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorConsejo(@RequestParam Integer idConsejo) {

        log.info("Inicio request: buscar sesiones por consejo");
        log.debug("Id consejo: {}", idConsejo);

        List<SesionConsejoResponse> listaResponse =
                sesionConsejoService.buscarPorConsejo(idConsejo);

        log.info(
                "Fin request: buscar sesiones por consejo. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_NUMERO)
    public ResponseBase<SesionConsejoResponse> buscarPorNumeroSesion(@RequestParam String numeroSesion) {

        log.info("Inicio request: buscar sesión por número");
        log.debug("Número sesión: {}", numeroSesion);

        SesionConsejoResponse response =
                sesionConsejoService.buscarPorNumeroSesion(numeroSesion);

        log.info("Fin request: buscar sesión por número");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_FECHA)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSesion) {

        log.info("Inicio request: buscar sesiones por fecha");
        log.debug("Fecha sesión: {}", fechaSesion);

        List<SesionConsejoResponse> listaResponse =
                sesionConsejoService.buscarPorFecha(fechaSesion);

        log.info(
                "Fin request: buscar sesiones por fecha. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.BUSCAR_POR_TIPO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorTipoSesion(@RequestParam Integer idTipoSesion) {

        log.info("Inicio request: buscar sesiones por tipo");
        log.debug("Id tipo sesión: {}", idTipoSesion);

        List<SesionConsejoResponse> listaResponse =
                sesionConsejoService.buscarPorTipoSesion(idTipoSesion);

        log.info(
                "Fin request: buscar sesiones por tipo. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}