package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.unu.ms.MsConsejo.model.request.AsistenciaSesionRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaSesionResponse;
import com.unu.ms.MsConsejo.service.AsistenciaSesionService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.AsistenciaSesion.BASE)
@Tag(name = "AsistenciaSesion Controller")
public class AsistenciaSesionController {

    @Autowired
    private AsistenciaSesionService asistenciaSesionService;

    @GetMapping(value = ApiRoutes.AsistenciaSesion.LISTAR)
    public ResponseBase<List<AsistenciaSesionResponse>> Listar() {
        List<AsistenciaSesionResponse> listaResponse = asistenciaSesionService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK,listaResponse);
    }

    @PostMapping(value = ApiRoutes.AsistenciaSesion.GUARDAR)
    public ResponseBase<AsistenciaSesionResponse> Guardar(@RequestBody AsistenciaSesionRequest request) {
        AsistenciaSesionResponse response = asistenciaSesionService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.AsistenciaSesion.ELIMINAR)
    public ResponseBase<?> Eliminar(@RequestParam(value = "id") Integer id) {
        asistenciaSesionService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.AsistenciaSesion.ACTUALIZAR)
    public ResponseBase<AsistenciaSesionResponse> Actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody AsistenciaSesionRequest request) {
        AsistenciaSesionResponse response = asistenciaSesionService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.AsistenciaSesion.OBTENER_POR_ID)
    public ResponseBase<AsistenciaSesionResponse> ObtenerPorId(@RequestParam(value = "id") Integer id) {
        AsistenciaSesionResponse response = asistenciaSesionService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }


    @GetMapping(value = ApiRoutes.AsistenciaSesion.BUSCAR_POR_SESION)
    public ResponseBase<List<AsistenciaSesionResponse>> buscarPorSesion(@RequestParam Integer idSesion) {
        List<AsistenciaSesionResponse> listaResponse = asistenciaSesionService.buscarPorSesion(idSesion);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.AsistenciaSesion.BUSCAR_POR_MIEMBRO)
    public ResponseBase<List<AsistenciaSesionResponse>> buscarPorMiembro(@RequestParam Integer idMiembro) {
        List<AsistenciaSesionResponse> listaResponse = asistenciaSesionService.buscarPorMiembro(idMiembro);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.AsistenciaSesion.BUSCAR_POR_ESTADO)
    public ResponseBase<List<AsistenciaSesionResponse>> buscarPorEstadoAsistencia(@RequestParam Integer idEstadoAsistencia) {
        List<AsistenciaSesionResponse> listaResponse = asistenciaSesionService.buscarPorEstadoAsistencia(idEstadoAsistencia);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
