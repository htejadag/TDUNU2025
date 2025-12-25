package com.unu.ms.MsGradosTitulos.controller;

import java.util.List;

import com.unu.ms.MsGradosTitulos.model.request.ResolucionRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionResponse;
import com.unu.ms.MsGradosTitulos.service.ResolucionService;
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
@RequestMapping(ApiRoutes.Resolucion.BASE)
@Tag(name = "Resolucion Controller")
public class ResolucionController {

    @Autowired
    ResolucionService resolucionService;

    @GetMapping(value = ApiRoutes.Resolucion.LISTAR)
    public ResponseBase<List<ResolucionResponse>> Listar() {
        List<ResolucionResponse> listaResponse = resolucionService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Resolucion.GUARDAR)
    public ResponseBase<ResolucionResponse> Guardar(@RequestBody ResolucionRequest request) {
        ResolucionResponse response = resolucionService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Resolucion.ELIMINAR)
    public ResponseBase<?> Eliminar(@RequestParam(value = "id") Integer id) {
        resolucionService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Resolucion.ACTUALIZAR)
    public ResponseBase<ResolucionResponse> Actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ResolucionRequest request) {
        ResolucionResponse response = resolucionService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Resolucion.OBTENER_POR_ID)
    public ResponseBase<ResolucionResponse> ObtenerPorId(@RequestParam(value = "id") Integer id) {
        ResolucionResponse response = resolucionService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(ApiRoutes.Resolucion.BUSCAR_POR_EXPEDIENTE)
    public ResponseBase<List<ResolucionResponse>> buscarPorExpediente(@RequestParam Integer idExpediente) {
        List<ResolucionResponse> listaResponse = resolucionService.buscarPorExpediente(idExpediente);
         return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(ApiRoutes.Resolucion.BUSCAR_POR_NUMERO)
    public ResponseBase<ResolucionResponse> buscarPorNumero(@RequestParam String numero) {
        ResolucionResponse response = resolucionService.buscarPorNumero(numero);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

}
