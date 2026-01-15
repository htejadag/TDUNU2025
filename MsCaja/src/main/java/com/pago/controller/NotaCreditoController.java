package com.pago.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pago.model.request.NotaCreditoRequest;
import com.pago.model.response.NotaCreditoResponse;
import com.pago.service.NotaCreditoService;
import com.pago.util.ApiRoutes;
import com.pago.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Demo.BASE + ApiRoutes.Demo.NOTA_CREDITO)
public class NotaCreditoController {

    @Autowired
    NotaCreditoService notaCreditoService;

    @GetMapping(value = ApiRoutes.Demo.LISTAR)
    public ResponseBase<List<NotaCreditoResponse>> listar() {
        List<NotaCreditoResponse> lista = notaCreditoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
    public ResponseBase<NotaCreditoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        NotaCreditoResponse response = notaCreditoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Demo.GUARDAR)
    public ResponseBase<NotaCreditoResponse> guardar(@RequestBody NotaCreditoRequest request) {
        NotaCreditoResponse response = notaCreditoService.guardar(request);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Demo.EDITAR)
    public ResponseBase<NotaCreditoResponse> editar(@RequestParam(value = "id") Integer id,
            @RequestBody NotaCreditoRequest request) {
        NotaCreditoResponse response = notaCreditoService.editar(id, request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam(value = "id") Integer id) {
        notaCreditoService.eliminar(id);
        return ResponseBase.ok(null);
    }

}
