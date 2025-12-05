package com.unu.ms.MsConsejo.controller;

import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.service.ConsejoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Consejo.BASE)
@Tag(name = "Consejo", description = "API para gestión de consejos")
public class ConsejoController {

    @Autowired
    private ConsejoService consejoService;

    @Operation(summary = "Listar consejos", description = "Obtiene la lista completa de consejos")
    @GetMapping(ApiRoutes.Consejo.LISTAR)
    public ResponseBase<List<ConsejoResponse>> listar() {
        List<ConsejoResponse> lista = consejoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Consejo.OBTENER_POR_ID)
    public ResponseBase<ConsejoResponse> obtenerPorId(@RequestParam Integer id) {
        ConsejoResponse response = consejoService.obtenerPorId(id);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Consejo no encontrado");
    }

    @PostMapping(ApiRoutes.Consejo.GUARDAR)
    public ResponseBase<ConsejoResponse> guardar(@RequestBody ConsejoRequest request) {
        ConsejoResponse response = consejoService.guardar(request);
        return ResponseBase.ok("Consejo creado exitosamente", response);
    }

    @PutMapping(ApiRoutes.Consejo.ACTUALIZAR)
    public ResponseBase<ConsejoResponse> actualizar(@RequestBody ConsejoRequest request) {
        try {
            ConsejoResponse response = consejoService.actualizar(request);
            return ResponseBase.ok("Consejo actualizado exitosamente", response);
        } catch (IllegalArgumentException e) {
            return ResponseBase.error(e.getMessage());
        }
    }

    @DeleteMapping(ApiRoutes.Consejo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        consejoService.eliminar(id);
        return ResponseBase.ok("Consejo eliminado exitosamente", null);
    }

    @GetMapping(ApiRoutes.Consejo.BUSCAR_POR_NOMBRE)
    public ResponseBase<ConsejoResponse> buscarPorNombre(@RequestParam String nombre) {
        ConsejoResponse response = consejoService.buscarPorNombre(nombre);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Consejo no encontrado");
    }

    @GetMapping(ApiRoutes.Consejo.BUSCAR_POR_ESTADO)
    public ResponseBase<List<ConsejoResponse>> buscarPorEstado(@RequestParam Integer idEstado) {
        List<ConsejoResponse> lista = consejoService.buscarPorEstado(idEstado);
        return ResponseBase.ok(lista);
    }
}
