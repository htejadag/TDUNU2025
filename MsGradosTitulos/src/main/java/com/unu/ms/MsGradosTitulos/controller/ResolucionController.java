package com.unu.ms.MsGradosTitulos.controller;

import com.unu.ms.MsGradosTitulos.model.request.ResolucionRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionResponse;
import com.unu.ms.MsGradosTitulos.service.ResolucionService;
import com.unu.ms.MsGradosTitulos.util.ApiRoutes;
import com.unu.ms.MsGradosTitulos.util.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Resolucion.BASE)
@Tag(name = "Resolución", description = "API para gestión de resoluciones")
public class ResolucionController {

    @Autowired
    private ResolucionService resolucionService;

    @GetMapping(ApiRoutes.Resolucion.LISTAR)
    public ResponseBase<List<ResolucionResponse>> listar() {
        List<ResolucionResponse> lista = resolucionService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Resolucion.OBTENER_POR_ID)
    public ResponseBase<ResolucionResponse> obtenerPorId(@RequestParam Integer id) {
        ResolucionResponse response = resolucionService.obtenerPorId(id);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Resolución no encontrada");
    }

    @PostMapping(ApiRoutes.Resolucion.GUARDAR)
    public ResponseBase<ResolucionResponse> guardar(@RequestBody ResolucionRequest request) {
        ResolucionResponse response = resolucionService.guardar(request);
        return ResponseBase.ok("Resolución creada exitosamente", response);
    }

    @PutMapping(ApiRoutes.Resolucion.ACTUALIZAR)
    public ResponseBase<ResolucionResponse> actualizar(@RequestBody ResolucionRequest request) {
        try {
            ResolucionResponse response = resolucionService.actualizar(request);
            return ResponseBase.ok("Resolución actualizada exitosamente", response);
        } catch (IllegalArgumentException e) {
            return ResponseBase.error(e.getMessage());
        }
    }

    @DeleteMapping(ApiRoutes.Resolucion.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        resolucionService.eliminar(id);
        return ResponseBase.ok("Resolución eliminada exitosamente", null);
    }

    @GetMapping(ApiRoutes.Resolucion.BUSCAR_POR_EXPEDIENTE)
    public ResponseBase<List<ResolucionResponse>> buscarPorExpediente(@RequestParam Integer idExpediente) {
        List<ResolucionResponse> lista = resolucionService.buscarPorExpediente(idExpediente);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Resolucion.BUSCAR_POR_NUMERO)
    public ResponseBase<ResolucionResponse> buscarPorNumero(@RequestParam String numero) {
        ResolucionResponse response = resolucionService.buscarPorNumero(numero);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Resolución no encontrada");
    }
}
