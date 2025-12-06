package com.unu.ms.MsGradosTitulos.controller;

import com.unu.ms.MsGradosTitulos.model.request.ResolucionArticuloRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionArticuloResponse;
import com.unu.ms.MsGradosTitulos.service.ResolucionArticuloService;
import com.unu.ms.MsGradosTitulos.util.ApiRoutes;
import com.unu.ms.MsGradosTitulos.util.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.ResolucionArticulo.BASE)
@Tag(name = "Artículos de Resolución", description = "API para gestión de artículos de resoluciones")
public class ResolucionArticuloController {

    @Autowired
    private ResolucionArticuloService resolucionArticuloService;

    @GetMapping(ApiRoutes.ResolucionArticulo.LISTAR)
    public ResponseBase<List<ResolucionArticuloResponse>> listar() {
        List<ResolucionArticuloResponse> lista = resolucionArticuloService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.ResolucionArticulo.OBTENER_POR_ID)
    public ResponseBase<ResolucionArticuloResponse> obtenerPorId(@RequestParam Integer id) {
        ResolucionArticuloResponse response = resolucionArticuloService.obtenerPorId(id);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Artículo no encontrado");
    }

    @PostMapping(ApiRoutes.ResolucionArticulo.GUARDAR)
    public ResponseBase<ResolucionArticuloResponse> guardar(@RequestBody ResolucionArticuloRequest request) {
        ResolucionArticuloResponse response = resolucionArticuloService.guardar(request);
        return ResponseBase.ok("Artículo creado exitosamente", response);
    }

    @PutMapping(ApiRoutes.ResolucionArticulo.ACTUALIZAR)
    public ResponseBase<ResolucionArticuloResponse> actualizar(@RequestBody ResolucionArticuloRequest request) {
        try {
            ResolucionArticuloResponse response = resolucionArticuloService.actualizar(request);
            return ResponseBase.ok("Artículo actualizado exitosamente", response);
        } catch (IllegalArgumentException e) {
            return ResponseBase.error(e.getMessage());
        }
    }

    @DeleteMapping(ApiRoutes.ResolucionArticulo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        resolucionArticuloService.eliminar(id);
        return ResponseBase.ok("Artículo eliminado exitosamente", null);
    }

    @GetMapping(ApiRoutes.ResolucionArticulo.BUSCAR_POR_RESOLUCION)
    public ResponseBase<List<ResolucionArticuloResponse>> buscarPorResolucion(@RequestParam Integer idResolucion) {
        List<ResolucionArticuloResponse> lista = resolucionArticuloService.buscarPorResolucion(idResolucion);
        return ResponseBase.ok(lista);
    }
}
