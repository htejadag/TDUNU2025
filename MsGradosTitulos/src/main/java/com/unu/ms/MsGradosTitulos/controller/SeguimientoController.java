package com.unu.ms.MsGradosTitulos.controller;

import java.util.List;

import com.unu.ms.MsGradosTitulos.model.request.SeguimientoRequest;
import com.unu.ms.MsGradosTitulos.model.response.SeguimientoResponse;
import com.unu.ms.MsGradosTitulos.service.SeguimientoService;
import com.unu.ms.MsGradosTitulos.util.ApiRoutes;
import com.unu.ms.MsGradosTitulos.util.Mensajes;
import com.unu.ms.MsGradosTitulos.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.Seguimiento.BASE)
@Tag(name = "Seguimiento Controller")
public class SeguimientoController {

    @Autowired
    SeguimientoService seguimientoService;

    @GetMapping(value = ApiRoutes.Seguimiento.LISTAR)
    public ResponseBase<List<SeguimientoResponse>> Listar() {
        //List<SeguimientoResponse> resoluciones = seguimientoService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, null);
    }

    @PostMapping(value = ApiRoutes.Seguimiento.CREAR)
    public ResponseBase<SeguimientoResponse> Crear(@RequestBody SeguimientoRequest request) {
        //SeguimientoResponse response = seguimientoService.crear(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, null);
    }

    @DeleteMapping(value = ApiRoutes.Seguimiento.ELIMINAR)
    public ResponseBase<?> Eliminar(@RequestParam(value = "id") Integer id) {
        //seguimientoService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Seguimiento.ACTUALIZAR)
    public ResponseBase<SeguimientoResponse> Actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody SeguimientoRequest request) {
        //SeguimientoResponse response = seguimientoService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, null);
    }

    @GetMapping(value = ApiRoutes.Seguimiento.OBTENER_POR_ID)
    public ResponseBase<SeguimientoResponse> ObtenerPorId(@RequestParam(value = "id") Integer id) {
        //SeguimientoResponse resp = seguimientoService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_ID_OK, null);
    }

}
