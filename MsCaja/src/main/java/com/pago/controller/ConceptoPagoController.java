package com.pago.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pago.model.request.ConceptoPagoRequest;
import com.pago.model.response.ConceptoPagoResponse;
import com.pago.service.ConceptoPagoService;
import com.pago.util.ApiRoutes;
import com.pago.util.ResponseBase;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.Demo.CONCEPTO_PAGO)
public class ConceptoPagoController {

    @Autowired
    ConceptoPagoService conceptoPagoService;

    @GetMapping(value = ApiRoutes.Demo.LISTAR)
    public ResponseBase<List<ConceptoPagoResponse>> listar() {
        List<ConceptoPagoResponse> resp = conceptoPagoService.listar();
        return ResponseBase.ok(resp);
    }

    @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
    public ResponseBase<ConceptoPagoResponse> obtenerPorId(@RequestParam("id") Integer id) {
        return ResponseBase.ok(conceptoPagoService.obtenerPorId(id));
    }

    @PostMapping(value = ApiRoutes.Demo.GUARDAR)
    public ResponseBase<ConceptoPagoResponse> guardar(@RequestBody ConceptoPagoRequest request) {
        ConceptoPagoResponse resp = conceptoPagoService.guardar(request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.EDITAR)
    public ResponseBase<ConceptoPagoResponse> editar(@RequestParam("id") Integer id,
            @RequestBody ConceptoPagoRequest request) {
        ConceptoPagoResponse resp = conceptoPagoService.editar(id, request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.DESACTIVAR)
    public ResponseBase<Void> desactivar(@RequestParam("id") Integer id) {
        conceptoPagoService.desactivar(id);
        return ResponseBase.ok(null);
    }

    @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam("id") Integer id) {
        conceptoPagoService.eliminar(id);
        return ResponseBase.ok(null);
    }
}
