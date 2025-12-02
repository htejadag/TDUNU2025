package com.unu.ms.MsGradosTitulos.controller;

import java.util.List;

import com.unu.ms.MsGradosTitulos.model.request.ExpedienteRequest;
import com.unu.ms.MsGradosTitulos.model.response.ExpedienteResponse;
import com.unu.ms.MsGradosTitulos.service.ExpedienteService;
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
@RequestMapping(ApiRoutes.Expediente.BASE)
@Tag(name = "Expediente Controller")
public class ExpedienteController {

    @Autowired
    ExpedienteService expedienteService;

    @GetMapping(value = ApiRoutes.Expediente.LISTAR)
    public ResponseBase<List<ExpedienteResponse>> Listar() {
        //List<ExpedienteResponse> resoluciones = expedienteService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, null);
    }

    @PostMapping(value = ApiRoutes.Expediente.CREAR)
    public ResponseBase<ExpedienteResponse> Crear(@RequestBody ExpedienteRequest request) {
        //ExpedienteResponse response = expedienteService.crear(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, null);
    }

    @DeleteMapping(value = ApiRoutes.Expediente.ELIMINAR)
    public ResponseBase<?> Eliminar(@RequestParam(value = "id") Integer id) {
        //expedienteService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Expediente.ACTUALIZAR)
    public ResponseBase<ExpedienteResponse> Actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ExpedienteRequest request) {
        //ExpedienteResponse response = expedienteService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, null);
    }

    @GetMapping(value = ApiRoutes.Expediente.OBTENER_POR_ID)
    public ResponseBase<ExpedienteResponse> ObtenerPorId(@RequestParam(value = "id") Integer id) {
        //ExpedienteResponse resp = expedienteService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_ID_OK, null);
    }

}
