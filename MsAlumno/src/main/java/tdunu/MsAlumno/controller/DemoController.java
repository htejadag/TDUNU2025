package tdunu.MsAlumno.controller;

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
import tdunu.MsAlumno.model.entity.DemoModel;
import tdunu.MsAlumno.model.request.DemoRequest;
import tdunu.MsAlumno.model.response.DemoResponse;
import tdunu.MsAlumno.service.DemoService;
import tdunu.MsAlumno.util.ApiRoutes;
import tdunu.MsAlumno.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Demo.BASE)
public class DemoController {

  @Autowired
  DemoService demoService;

  @GetMapping(value = ApiRoutes.Demo.LISTAR)
  public ResponseBase<List<DemoResponse>> listar() {
    List<DemoResponse> lista = demoService.listar();
    return ResponseBase.ok(lista);
  }

  @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
  public ResponseBase<DemoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    DemoResponse response = demoService.obtenerPorId(id);
    return ResponseBase.ok(response);
  }

  @PostMapping(value = ApiRoutes.Demo.GUARDAR)
  public ResponseBase<DemoResponse> guardar(@RequestBody DemoRequest request) {
    DemoResponse response = demoService.guardar(request);
    return ResponseBase.ok(response);
  }

  @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
  public DemoResponse eliminar(@RequestParam(value = "id") Integer id) {
    demoService.eliminar(id);
    return null;
  }

}
