package com.pago.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pago.model.request.AperturaCierreCajaRequest;
import com.pago.model.response.AperturaCierreCajaResponse;
import com.pago.service.AperturaCierreCajaService;
import com.pago.util.ApiRoutes;
import com.pago.util.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.Demo.APE_CIE_CAJA)
public class AperturaCierreCajaController {

    @Autowired
    AperturaCierreCajaService aperturaCierreCajaService;

    @GetMapping(value = ApiRoutes.Demo.LISTAR)
    public ResponseBase<List<AperturaCierreCajaResponse>> listar() {
        List<AperturaCierreCajaResponse> resp = aperturaCierreCajaService.listar();
        return ResponseBase.ok(resp);
    }

    @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
    public ResponseBase<AperturaCierreCajaResponse> obtenerPorId(@RequestParam("id") Integer id) {
        return ResponseBase.ok(aperturaCierreCajaService.obtenerPorId(id));
    }

    @PostMapping(value = ApiRoutes.Demo.GUARDAR)
    public ResponseBase<AperturaCierreCajaResponse> guardar(@RequestBody AperturaCierreCajaRequest request) {
        AperturaCierreCajaResponse resp = aperturaCierreCajaService.guardar(request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.EDITAR)
    public ResponseBase<AperturaCierreCajaResponse> editar(@RequestParam("id") Integer id,
            @RequestBody AperturaCierreCajaRequest request) {
        AperturaCierreCajaResponse resp = aperturaCierreCajaService.editar(id, request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.DESACTIVAR)
    public ResponseBase<Void> desactivar(@RequestParam("id") Integer id) {
        aperturaCierreCajaService.desactivar(id);
        return ResponseBase.ok(null);
    }

    @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam("id") Integer id) {
        aperturaCierreCajaService.eliminar(id);
        return ResponseBase.ok(null);
    }

}
