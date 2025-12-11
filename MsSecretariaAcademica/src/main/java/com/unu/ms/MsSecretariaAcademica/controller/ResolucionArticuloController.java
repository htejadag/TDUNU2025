package com.unu.ms.MsSecretariaAcademica.controller;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ResolucionArticuloResponse;
import com.unu.ms.MsSecretariaAcademica.service.ResolucionArticuloService;
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
        List<ResolucionArticuloResponse> listaResponse = resolucionArticuloService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.ResolucionArticulo.GUARDAR)
    public ResponseBase<ResolucionArticuloResponse> Guardar(@RequestBody ResolucionArticuloRequest request) {
        ResolucionArticuloResponse response = resolucionArticuloService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.ResolucionArticulo.ELIMINAR)
    public ResponseBase<?> Eliminar(@RequestParam(value = "id") Integer id) {
        resolucionArticuloService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.ResolucionArticulo.ACTUALIZAR)
    public ResponseBase<ResolucionArticuloResponse> Actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ResolucionArticuloRequest request) {
        ResolucionArticuloResponse response = resolucionArticuloService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.ResolucionArticulo.OBTENER_POR_ID)
    public ResponseBase<ResolucionArticuloResponse> ObtenerPorId(@RequestParam(value = "id") Integer id) {
        ResolucionArticuloResponse response = resolucionArticuloService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.ResolucionArticulo.BUSCAR_POR_RESOLUCION)
    public ResponseBase<List<ResolucionArticuloResponse>> buscarPorResolucion(@RequestParam Integer idResolucion) {
        List<ResolucionArticuloResponse> listaResponse = resolucionArticuloService.buscarPorResolucion(idResolucion);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
