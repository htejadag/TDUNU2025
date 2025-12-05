package com.unu.ms.MsConsejo.controller;

import com.unu.ms.MsConsejo.model.request.AsistenciaConsejeroRequest;
import com.unu.ms.MsConsejo.model.response.AsistenciaConsejeroResponse;
import com.unu.ms.MsConsejo.service.AsistenciaConsejeroService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.AsistenciaConsejero.BASE)
@Tag(name = "Asistencia Consejero", description = "API para gestión de asistencia de consejeros")
public class AsistenciaConsejeroController {

    @Autowired
    private AsistenciaConsejeroService asistenciaConsejeroService;

    @Operation(summary = "Listar asistencias", description = "Obtiene la lista completa de asistencias")
    @GetMapping(ApiRoutes.AsistenciaConsejero.LISTAR)
    public ResponseBase<List<AsistenciaConsejeroResponse>> listar() {
        List<AsistenciaConsejeroResponse> lista = asistenciaConsejeroService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.AsistenciaConsejero.OBTENER_POR_ID)
    public ResponseBase<AsistenciaConsejeroResponse> obtenerPorId(@RequestParam Integer id) {
        AsistenciaConsejeroResponse response = asistenciaConsejeroService.obtenerPorId(id);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Asistencia no encontrada");
    }

    @PostMapping(ApiRoutes.AsistenciaConsejero.GUARDAR)
    public ResponseBase<AsistenciaConsejeroResponse> guardar(@RequestBody AsistenciaConsejeroRequest request) {
        AsistenciaConsejeroResponse response = asistenciaConsejeroService.guardar(request);
        return ResponseBase.ok("Asistencia registrada exitosamente", response);
    }

    @PutMapping(ApiRoutes.AsistenciaConsejero.ACTUALIZAR)
    public ResponseBase<AsistenciaConsejeroResponse> actualizar(@RequestBody AsistenciaConsejeroRequest request) {
        try {
            AsistenciaConsejeroResponse response = asistenciaConsejeroService.actualizar(request);
            return ResponseBase.ok("Asistencia actualizada exitosamente", response);
        } catch (IllegalArgumentException e) {
            return ResponseBase.error(e.getMessage());
        }
    }

    @DeleteMapping(ApiRoutes.AsistenciaConsejero.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        asistenciaConsejeroService.eliminar(id);
        return ResponseBase.ok("Asistencia eliminada exitosamente", null);
    }

    @GetMapping(ApiRoutes.AsistenciaConsejero.BUSCAR_POR_SESION)
    public ResponseBase<List<AsistenciaConsejeroResponse>> buscarPorSesion(@RequestParam Integer idSesion) {
        List<AsistenciaConsejeroResponse> lista = asistenciaConsejeroService.buscarPorSesion(idSesion);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.AsistenciaConsejero.BUSCAR_POR_MIEMBRO)
    public ResponseBase<List<AsistenciaConsejeroResponse>> buscarPorMiembro(@RequestParam Integer idMiembro) {
        List<AsistenciaConsejeroResponse> lista = asistenciaConsejeroService.buscarPorMiembro(idMiembro);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.AsistenciaConsejero.BUSCAR_POR_ESTADO)
    public ResponseBase<List<AsistenciaConsejeroResponse>> buscarPorEstadoAsistencia(@RequestParam Integer idEstadoAsistencia) {
        List<AsistenciaConsejeroResponse> lista = asistenciaConsejeroService.buscarPorEstadoAsistencia(idEstadoAsistencia);
        return ResponseBase.ok(lista);
    }
}
