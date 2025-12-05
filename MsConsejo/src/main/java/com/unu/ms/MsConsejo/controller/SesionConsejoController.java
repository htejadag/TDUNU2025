package com.unu.ms.MsConsejo.controller;

import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.service.SesionConsejoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiRoutes.SesionConsejo.BASE)
@Tag(name = "Sesión Consejo", description = "API para gestión de sesiones del consejo")
public class SesionConsejoController {

    @Autowired
    private SesionConsejoService sesionConsejoService;

    @Operation(summary = "Listar sesiones", description = "Obtiene la lista completa de sesiones del consejo")
    @GetMapping(ApiRoutes.SesionConsejo.LISTAR)
    public ResponseBase<List<SesionConsejoResponse>> listar() {
        List<SesionConsejoResponse> lista = sesionConsejoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.SesionConsejo.OBTENER_POR_ID)
    public ResponseBase<SesionConsejoResponse> obtenerPorId(@RequestParam Integer id) {
        SesionConsejoResponse response = sesionConsejoService.obtenerPorId(id);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Sesión no encontrada");
    }

    @PostMapping(ApiRoutes.SesionConsejo.GUARDAR)
    public ResponseBase<SesionConsejoResponse> guardar(@RequestBody SesionConsejoRequest request) {
        SesionConsejoResponse response = sesionConsejoService.guardar(request);
        return ResponseBase.ok("Sesión creada exitosamente", response);
    }

    @PutMapping(ApiRoutes.SesionConsejo.ACTUALIZAR)
    public ResponseBase<SesionConsejoResponse> actualizar(@RequestBody SesionConsejoRequest request) {
        try {
            SesionConsejoResponse response = sesionConsejoService.actualizar(request);
            return ResponseBase.ok("Sesión actualizada exitosamente", response);
        } catch (IllegalArgumentException e) {
            return ResponseBase.error(e.getMessage());
        }
    }

    @DeleteMapping(ApiRoutes.SesionConsejo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        sesionConsejoService.eliminar(id);
        return ResponseBase.ok("Sesión eliminada exitosamente", null);
    }

    @GetMapping(ApiRoutes.SesionConsejo.BUSCAR_POR_CONSEJO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorConsejo(@RequestParam Integer idConsejo) {
        List<SesionConsejoResponse> lista = sesionConsejoService.buscarPorConsejo(idConsejo);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.SesionConsejo.BUSCAR_POR_NUMERO)
    public ResponseBase<SesionConsejoResponse> buscarPorNumeroSesion(@RequestParam String numeroSesion) {
        SesionConsejoResponse response = sesionConsejoService.buscarPorNumeroSesion(numeroSesion);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Sesión no encontrada");
    }

    @GetMapping(ApiRoutes.SesionConsejo.BUSCAR_POR_FECHA)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSesion) {
        List<SesionConsejoResponse> lista = sesionConsejoService.buscarPorFecha(fechaSesion);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.SesionConsejo.BUSCAR_POR_TIPO)
    public ResponseBase<List<SesionConsejoResponse>> buscarPorTipoSesion(@RequestParam Integer idTipoSesion) {
        List<SesionConsejoResponse> lista = sesionConsejoService.buscarPorTipoSesion(idTipoSesion);
        return ResponseBase.ok(lista);
    }
}
