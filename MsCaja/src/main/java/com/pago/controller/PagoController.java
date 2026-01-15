package com.pago.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.Demo.BASE + ApiRoutes.Demo.CONCEPTO_PAGO)
public class PagoController {

  private final PagoService pagoService;

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