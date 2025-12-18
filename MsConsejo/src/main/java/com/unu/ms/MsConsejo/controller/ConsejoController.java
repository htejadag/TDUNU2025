package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.service.ConsejoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.Consejo.BASE)
@Tag(name = "Consejo Controller")
public class ConsejoController {

    private ConsejoService consejoService;

    @GetMapping(value = ApiRoutes.Consejo.LISTAR)
    public ResponseBase<List<ConsejoResponse>> listar() {
        List<ConsejoResponse> listaResponse = consejoService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Consejo.GUARDAR)
    public ResponseBase<ConsejoResponse> guardar(@RequestBody ConsejoRequest request) {
        ConsejoResponse response = consejoService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Consejo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {
        consejoService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Consejo.ACTUALIZAR)
    public ResponseBase<ConsejoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ConsejoRequest request) {
        ConsejoResponse response = consejoService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Consejo.OBTENER_POR_ID)
    public ResponseBase<ConsejoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        ConsejoResponse response = consejoService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Consejo.BUSCAR_POR_NOMBRE)
    public ResponseBase<ConsejoResponse> buscarPorNombre(@RequestParam String nombre) {
        ConsejoResponse response = consejoService.buscarPorNombre(nombre);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Consejo.BUSCAR_POR_ESTADO)
    public ResponseBase<List<ConsejoResponse>> buscarPorEstado(@RequestParam Integer idEstado) {
        List<ConsejoResponse> listaResponse = consejoService.buscarPorEstado(idEstado);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
