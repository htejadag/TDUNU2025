package com.unu.ms.MsConsejo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.service.SesionConsejoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.SesionConsejo.BASE)
@Tag(name = "SesionConsejo Controller")
public class SesionConsejoController {

    @Autowired
    private SesionConsejoService sesionConsejoService;

    @GetMapping(value = ApiRoutes.SesionConsejo.LISTAR)
    public ResponseBase<List<SesionConsejoResponse>> Listar() {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.SesionConsejo.GUARDAR)
    public ResponseBase<SesionConsejoResponse> Guardar(@RequestBody SesionConsejoRequest request) {
        SesionConsejoResponse response = sesionConsejoService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.SesionConsejo.ELIMINAR)
    public ResponseBase<?> Eliminar(@RequestParam(value = "id") Integer id) {
        sesionConsejoService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.SesionConsejo.ACTUALIZAR)
    public ResponseBase<SesionConsejoResponse> Actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody SesionConsejoRequest request) {
        SesionConsejoResponse response = sesionConsejoService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.SesionConsejo.OBTENER_POR_ID)
    public ResponseBase<SesionConsejoResponse> ObtenerPorId(@RequestParam(value = "id") Integer id) {
        // SesionConsejoResponse response = sesionConsejoService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, null);
    }

    @GetMapping(ApiRoutes.SesionConsejo.BUSCAR_POR_CONSEJO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorConsejo(@RequestParam Integer idConsejo) {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.buscarPorConsejo(idConsejo);
        return ResponseBase.ok(listaResponse);
    }

    @GetMapping(ApiRoutes.SesionConsejo.BUSCAR_POR_NUMERO)
    public ResponseBase<SesionConsejoResponse> buscarPorNumeroSesion(@RequestParam String numeroSesion) {
        SesionConsejoResponse response = sesionConsejoService.buscarPorNumeroSesion(numeroSesion);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(ApiRoutes.SesionConsejo.BUSCAR_POR_FECHA)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSesion) {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.buscarPorFecha(fechaSesion);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(ApiRoutes.SesionConsejo.BUSCAR_POR_TIPO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorTipoSesion(@RequestParam Integer idTipoSesion) {
        List<SesionConsejoResponse> listaResponse = sesionConsejoService.buscarPorTipoSesion(idTipoSesion);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
