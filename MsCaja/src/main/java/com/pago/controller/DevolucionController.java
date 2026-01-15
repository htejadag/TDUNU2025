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

import com.pago.model.request.DevolucionRequest;
import com.pago.model.response.DevolucionResponse;
import com.pago.service.DevolucionService;
import com.pago.util.ApiRoutes;
import com.pago.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Demo.BASE + ApiRoutes.Demo.DEVOLUCION)
public class DevolucionController {

    @Autowired
    DevolucionService devolucionService;

    @GetMapping(value = ApiRoutes.Demo.LISTAR)
    public ResponseBase<List<DevolucionResponse>> listar() {
        List<DevolucionResponse> lista = devolucionService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
    public ResponseBase<DevolucionResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        DevolucionResponse response = devolucionService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Demo.GUARDAR)
    public ResponseBase<DevolucionResponse> guardar(@RequestBody DevolucionRequest request) {
        DevolucionResponse response = devolucionService.guardar(request);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Demo.EDITAR)
    public ResponseBase<DevolucionResponse> editar(@RequestParam(value = "id") Integer id,
            @RequestBody DevolucionRequest request) {
        DevolucionResponse response = devolucionService.editar(id, request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam(value = "id") Integer id) {
        devolucionService.eliminar(id);
        return ResponseBase.ok(null);
    }

}
