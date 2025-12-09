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

import com.pago.model.request.DetallePagoRequest;
import com.pago.model.response.DetallePagoResponse;
import com.pago.service.DetallePagoService;
import com.pago.util.ApiRoutes;
import com.pago.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Demo.BASE)
public class DetallePagoController {

  @Autowired
  DetallePagoService detallePagoService;

  @GetMapping(value = ApiRoutes.Demo.LISTAR)
  public ResponseBase<List<DetallePagoResponse>> listar() {
    List<DetallePagoResponse> lista = detallePagoService.listar();
    return ResponseBase.ok(lista);
  }

  @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
  public ResponseBase<DetallePagoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    DetallePagoResponse response = detallePagoService.obtenerPorId(id);
    return ResponseBase.ok(response);
  }

  @PostMapping(value = ApiRoutes.Demo.GUARDAR)
  public ResponseBase<DetallePagoResponse> guardar(@RequestBody DetallePagoRequest request) {
    DetallePagoResponse response = detallePagoService.guardar(request);
    return ResponseBase.ok(response);
  }

  @PutMapping(value = ApiRoutes.Demo.EDITAR)
  public ResponseBase<DetallePagoResponse> editar(@RequestParam(value = "id") Integer id, @RequestBody DetallePagoRequest request) {
    DetallePagoResponse response = detallePagoService.editar(id, request);
    return ResponseBase.ok(response);
  }

  @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
  public ResponseBase<Void> eliminar(@RequestParam(value = "id") Integer id) {
    detallePagoService.eliminar(id);
    return ResponseBase.ok(null);
  }

}
