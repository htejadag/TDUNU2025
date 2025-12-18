package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;
import com.unu.ms.MsConsejo.service.MiembroConsejoService;
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
@RequestMapping(ApiRoutes.MiembroConsejo.BASE)
@Tag(name = "MiembroConsejo Controller")
public class MiembroConsejoController {

    private MiembroConsejoService miembroConsejoService;

    @GetMapping(value = ApiRoutes.MiembroConsejo.LISTAR)
    public ResponseBase<List<MiembroConsejoResponse>> listar() {
        List<MiembroConsejoResponse> listaResponse = miembroConsejoService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.MiembroConsejo.GUARDAR)
    public ResponseBase<MiembroConsejoResponse> guardar(@RequestBody MiembroConsejoRequest request) {
        MiembroConsejoResponse response = miembroConsejoService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.MiembroConsejo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {
        miembroConsejoService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.MiembroConsejo.ACTUALIZAR)
    public ResponseBase<MiembroConsejoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody MiembroConsejoRequest request) {
        MiembroConsejoResponse response = miembroConsejoService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.MiembroConsejo.OBTENER_POR_ID)
    public ResponseBase<MiembroConsejoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        MiembroConsejoResponse response = miembroConsejoService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.MiembroConsejo.BUSCAR_POR_CONSEJO)
    public ResponseBase<List<MiembroConsejoResponse>> buscarPorConsejo(@RequestParam Integer idConsejo) {
        List<MiembroConsejoResponse> listaResponse = miembroConsejoService.buscarPorConsejo(idConsejo);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.MiembroConsejo.BUSCAR_POR_PERSONA)
    public ResponseBase<List<MiembroConsejoResponse>> buscarPorPersona(@RequestParam Integer idPersona) {
        List<MiembroConsejoResponse> listaResponse = miembroConsejoService.buscarPorPersona(idPersona);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.MiembroConsejo.BUSCAR_POR_CARGO)
    public ResponseBase<List<MiembroConsejoResponse>> buscarPorCargo(@RequestParam Integer idCargo) {
        List<MiembroConsejoResponse> listaResponse = miembroConsejoService.buscarPorCargo(idCargo);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
