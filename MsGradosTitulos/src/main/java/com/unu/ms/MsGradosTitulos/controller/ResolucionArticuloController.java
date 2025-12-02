package com.unu.ms.MsGradosTitulos.controller;

import java.util.List;

import com.unu.ms.MsGradosTitulos.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionArticuloResponse;
import com.unu.ms.MsGradosTitulos.service.ResolucionArticuloService;
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
@RequestMapping(ApiRoutes.ResolucionArticulo.BASE)
@Tag(name = "ResolucionArticulo Controller")
public class ResolucionArticuloController {

    @Autowired
    ResolucionArticuloService resolucionArticuloService;

    @GetMapping(value = ApiRoutes.ResolucionArticulo.LISTAR)
    public ResponseBase<List<ResolucionArticuloResponse>> Listar() {
        //List<ResolucionArticuloResponse> resoluciones = resolucionArticuloService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, null);
    }

    @PostMapping(value = ApiRoutes.ResolucionArticulo.CREAR)
    public ResponseBase<ResolucionArticuloResponse> Crear(@RequestBody ResolucionArticuloRequest request) {
        //ResolucionArticuloResponse response = resolucionArticuloService.crear(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, null);
    }

    @DeleteMapping(value = ApiRoutes.ResolucionArticulo.ELIMINAR)
    public ResponseBase<?> Eliminar(@RequestParam(value = "id") Integer id) {
        //resolucionArticuloService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.ResolucionArticulo.ACTUALIZAR)
    public ResponseBase<ResolucionArticuloResponse> Actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ResolucionArticuloRequest request) {
        //ResolucionArticuloResponse response = resolucionArticuloService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, null);
    }

    @GetMapping(value = ApiRoutes.ResolucionArticulo.OBTENER_POR_ID)
    public ResponseBase<ResolucionArticuloResponse> ObtenerPorId(@RequestParam(value = "id") Integer id) {
        //ResolucionArticuloResponse resp = resolucionArticuloService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_ID_OK, null);
    }

}
