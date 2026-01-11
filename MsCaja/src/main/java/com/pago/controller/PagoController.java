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
import com.pago.model.request.PagoRequest;
import com.pago.model.response.PagoResponse;
import com.pago.service.PagoService;
import com.pago.util.ApiRoutes;
import com.pago.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Demo.BASE + ApiRoutes.Demo.CONCEPTO_PAGO)
public class PagoController {

  @Autowired
  PagoService pagoService;

  @GetMapping(ApiRoutes.Demo.LISTAR)
  public ResponseBase<List<PagoResponse>> listar() {
    return ResponseBase.ok(pagoService.listar());
  }

  @GetMapping(ApiRoutes.Demo.OBTENER_POR_ID)
  public ResponseBase<PagoResponse> obtenerPorId(@RequestParam("id") Integer id) {
    return ResponseBase.ok(pagoService.obtenerPorId(id));
  }

  @PostMapping(ApiRoutes.Demo.GUARDAR)
  public ResponseBase<PagoResponse> guardar(@RequestBody PagoRequest request) {
    return ResponseBase.ok(pagoService.guardar(request));
  }

  @PutMapping(ApiRoutes.Demo.EDITAR)
  public ResponseBase<PagoResponse> editar(@RequestParam("id") Integer id, @RequestBody PagoRequest request) {
    return ResponseBase.ok(pagoService.editar(id, request));
  }

  @DeleteMapping(ApiRoutes.Demo.ELIMINAR)
  public ResponseBase<Void> eliminar(@RequestParam("id") Integer id) {
    pagoService.eliminar(id);
    return ResponseBase.ok(null);
  }
}

/* 
@RestController
@RequestMapping(ApiRoutes.Demo.BASE)
public class PagoController {

  @Autowired
  PagoService pagoService;

  @GetMapping(value = ApiRoutes.Demo.LISTAR)
  public ResponseBase<List<PagoResponse>> listar() {
    List<PagoResponse> lista = pagoService.listar();
    return ResponseBase.ok(lista);
  }

  @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
  public ResponseBase<PagoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    PagoResponse response = pagoService.obtenerPorId(id);
    return ResponseBase.ok(response);
  }

  @PostMapping(value = ApiRoutes.Demo.GUARDAR)
  public ResponseBase<PagoResponse> guardar(@RequestBody PagoRequest request) {
    PagoResponse response = pagoService.guardar(request);
    return ResponseBase.ok(response);
  }

  @PutMapping(value = ApiRoutes.Demo.EDITAR)
  public ResponseBase<PagoResponse> editar(@RequestParam(value = "id") Integer id, @RequestBody PagoRequest request) {
    PagoResponse response = pagoService.editar(id, request);
    return ResponseBase.ok(response);
  }

  @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
  public ResponseBase<Void> eliminar(@RequestParam(value = "id") Integer id) {
    pagoService.eliminar(id);
    return ResponseBase.ok(null);
  }

}
*/