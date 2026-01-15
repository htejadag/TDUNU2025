package com.pago.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pago.model.request.ClasificadorIngresoRequest;
import com.pago.model.response.ClasificadorIngresoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pago.util.ApiRoutes;
import com.pago.service.ClasificadorIngresoService;
import com.pago.util.ResponseBase;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.Demo.CLA_INGRESO)
public class ClasificadorIngresoController {

    @Autowired
    ClasificadorIngresoService clasificadorIngresoService;

    @GetMapping(value = ApiRoutes.Demo.LISTAR)
    public ResponseBase<List<ClasificadorIngresoResponse>> listar() {
        List<ClasificadorIngresoResponse> resp = clasificadorIngresoService.listar();
        return ResponseBase.ok(resp);
    }

    @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
    public ResponseBase<ClasificadorIngresoResponse> obtenerPorId(@RequestParam("id") Integer id) {
        return ResponseBase.ok(clasificadorIngresoService.obtenerPorId(id));
    }

    @PostMapping(value = ApiRoutes.Demo.GUARDAR)
    public ResponseBase<ClasificadorIngresoResponse> guardar(@RequestBody ClasificadorIngresoRequest request) {
        ClasificadorIngresoResponse resp = clasificadorIngresoService.guardar(request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.EDITAR)
    public ResponseBase<ClasificadorIngresoResponse> editar(@RequestParam("id") Integer id,
            @RequestBody ClasificadorIngresoRequest request) {
        ClasificadorIngresoResponse resp = clasificadorIngresoService.editar(id, request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.DESACTIVAR)
    public ResponseBase<Void> desactivar(@RequestParam("id") Integer id) {
        clasificadorIngresoService.desactivar(id);
        return ResponseBase.ok(null);
    }

    @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam("id") Integer id) {
        clasificadorIngresoService.eliminar(id);
        return ResponseBase.ok(null);
    }
}
