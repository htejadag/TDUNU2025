package com.unu.ms.MsSecretariaAcademica.controller;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.ExpedienteRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.ExpedienteResponse;
import com.unu.ms.MsSecretariaAcademica.service.ExpedienteService;
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
@RequestMapping(ApiRoutes.Expediente.BASE)
@Tag(name = "Expediente Controller")
public class ExpedienteController {

    @Autowired
    ExpedienteService expedienteService;

    @GetMapping(value = ApiRoutes.Expediente.LISTAR)
    public ResponseBase<List<ExpedienteResponse>> Listar() {
        List<ExpedienteResponse> listaResponse = expedienteService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Expediente.GUARDAR)
    public ResponseBase<ExpedienteResponse> Guardar(@RequestBody ExpedienteRequest request) {
        ExpedienteResponse response = expedienteService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Expediente.ELIMINAR)
    public ResponseBase<?> Eliminar(@RequestParam(value = "id") Integer id) {
        expedienteService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Expediente.ACTUALIZAR)
    public ResponseBase<ExpedienteResponse> Actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ExpedienteRequest request) {
        ExpedienteResponse response = expedienteService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Expediente.OBTENER_POR_ID)
    public ResponseBase<ExpedienteResponse> ObtenerPorId(@RequestParam(value = "id") Integer id) {
        ExpedienteResponse response = expedienteService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(ApiRoutes.Expediente.BUSCAR_POR_CODIGO)
    public ResponseBase<ExpedienteResponse> buscarPorCodigo(@RequestParam String codigo) {
        ExpedienteResponse response = expedienteService.buscarPorCodigo(codigo);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(ApiRoutes.Expediente.BUSCAR_POR_PERSONA)
    public ResponseBase<List<ExpedienteResponse>> buscarPorPersona(@RequestParam Integer idPersona) {
        List<ExpedienteResponse> listaResponse = expedienteService.buscarPorPersona(idPersona);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
