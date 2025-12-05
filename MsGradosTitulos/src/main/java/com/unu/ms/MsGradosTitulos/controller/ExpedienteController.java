package com.unu.ms.MsGradosTitulos.controller;

import com.unu.ms.MsGradosTitulos.model.request.ExpedienteRequest;
import com.unu.ms.MsGradosTitulos.model.response.ExpedienteResponse;
import com.unu.ms.MsGradosTitulos.service.ExpedienteService;
import com.unu.ms.MsGradosTitulos.util.ApiRoutes;
import com.unu.ms.MsGradosTitulos.util.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Expediente.BASE)
@Tag(name = "Expediente", description = "API para gestión de expedientes")
public class ExpedienteController {

    @Autowired
    private ExpedienteService expedienteService;

    @Operation(summary = "Listar expedientes", description = "Obtiene la lista completa de expedientes")
    @GetMapping(ApiRoutes.Expediente.LISTAR)
    public ResponseBase<List<ExpedienteResponse>> listar() {
        List<ExpedienteResponse> lista = expedienteService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Expediente.OBTENER_POR_ID)
    public ResponseBase<ExpedienteResponse> obtenerPorId(@RequestParam Integer id) {
        ExpedienteResponse response = expedienteService.obtenerPorId(id);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Expediente no encontrado");
    }

    @PostMapping(ApiRoutes.Expediente.GUARDAR)
    public ResponseBase<ExpedienteResponse> guardar(@RequestBody ExpedienteRequest request) {
        ExpedienteResponse response = expedienteService.guardar(request);
        return ResponseBase.ok("Expediente creado exitosamente", response);
    }

    @PutMapping(ApiRoutes.Expediente.ACTUALIZAR)
    public ResponseBase<ExpedienteResponse> actualizar(@RequestBody ExpedienteRequest request) {
        try {
            ExpedienteResponse response = expedienteService.actualizar(request);
            return ResponseBase.ok("Expediente actualizado exitosamente", response);
        } catch (IllegalArgumentException e) {
            return ResponseBase.error(e.getMessage());
        }
    }

    @DeleteMapping(ApiRoutes.Expediente.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        expedienteService.eliminar(id);
        return ResponseBase.ok("Expediente eliminado exitosamente", null);
    }

    @GetMapping(ApiRoutes.Expediente.BUSCAR_POR_CODIGO)
    public ResponseBase<ExpedienteResponse> buscarPorCodigo(@RequestParam String codigo) {
        ExpedienteResponse response = expedienteService.buscarPorCodigo(codigo);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Expediente no encontrado");
    }

    @GetMapping(ApiRoutes.Expediente.BUSCAR_POR_PERSONA)
    public ResponseBase<List<ExpedienteResponse>> buscarPorPersona(@RequestParam Integer idPersona) {
        List<ExpedienteResponse> lista = expedienteService.buscarPorPersona(idPersona);
        return ResponseBase.ok(lista);
    }
}
