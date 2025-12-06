package com.example.demo.controller;

import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.request.PagoRequest;
import com.example.demo.model.response.PagoResponse;
import com.example.demo.service.PagoService;
import com.example.demo.util.ApiRoutes;
import com.example.demo.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Demo.BASE)
public class DemoController {

  @Autowired
  PagoService demoService;

  @GetMapping(value = ApiRoutes.Demo.LISTAR)
  public ResponseBase<List<PagoResponse>> listar() {
    List<PagoResponse> lista = demoService.listar();
    return ResponseBase.ok(lista);
  }

  @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
  public ResponseBase<PagoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    PagoResponse response = demoService.obtenerPorId(id);
    return ResponseBase.ok(response);
  }

  @PostMapping(value = ApiRoutes.Demo.GUARDAR)
  public ResponseBase<PagoResponse> guardar(@RequestBody PagoRequest request) {
    PagoResponse response = demoService.guardar(request);
    return ResponseBase.ok(response);
  }

  @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
  public ResponseBase<Void> eliminar(@RequestParam(value = "id") Integer id) {
    demoService.eliminar(id);
    return ResponseBase.ok(null);
  }

}
