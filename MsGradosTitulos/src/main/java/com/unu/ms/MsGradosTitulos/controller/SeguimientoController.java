package com.unu.ms.MsGradosTitulos.controller;

import com.unu.ms.MsGradosTitulos.model.request.SeguimientoRequest;
import com.unu.ms.MsGradosTitulos.model.response.SeguimientoResponse;
import com.unu.ms.MsGradosTitulos.service.SeguimientoService;
import com.unu.ms.MsGradosTitulos.util.ApiRoutes;
import com.unu.ms.MsGradosTitulos.util.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Seguimiento.BASE)
@Tag(name = "Seguimiento", description = "API para gestión de seguimientos")
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    @GetMapping(ApiRoutes.Seguimiento.LISTAR)
    public ResponseBase<List<SeguimientoResponse>> listar() {
        List<SeguimientoResponse> lista = seguimientoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Seguimiento.OBTENER_POR_ID)
    public ResponseBase<SeguimientoResponse> obtenerPorId(@RequestParam Integer id) {
        SeguimientoResponse response = seguimientoService.obtenerPorId(id);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Seguimiento no encontrado");
    }

    @PostMapping(ApiRoutes.Seguimiento.GUARDAR)
    public ResponseBase<SeguimientoResponse> guardar(@RequestBody SeguimientoRequest request) {
        SeguimientoResponse response = seguimientoService.guardar(request);
        return ResponseBase.ok("Seguimiento creado exitosamente", response);
    }

    @PutMapping(ApiRoutes.Seguimiento.ACTUALIZAR)
    public ResponseBase<SeguimientoResponse> actualizar(@RequestBody SeguimientoRequest request) {
        try {
            SeguimientoResponse response = seguimientoService.actualizar(request);
            return ResponseBase.ok("Seguimiento actualizado exitosamente", response);
        } catch (IllegalArgumentException e) {
            return ResponseBase.error(e.getMessage());
        }
    }

    @DeleteMapping(ApiRoutes.Seguimiento.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        seguimientoService.eliminar(id);
        return ResponseBase.ok("Seguimiento eliminado exitosamente", null);
    }

    @GetMapping(ApiRoutes.Seguimiento.BUSCAR_POR_ENTIDAD)
    public ResponseBase<List<SeguimientoResponse>> buscarPorEntidad(
            @RequestParam Integer idEntidadCatalogo,
            @RequestParam Integer entidadId) {
        List<SeguimientoResponse> lista = seguimientoService.buscarPorEntidad(idEntidadCatalogo, entidadId);
        return ResponseBase.ok(lista);
    }
}
