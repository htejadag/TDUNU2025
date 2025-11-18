package tdunu.MsTemplate.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tdunu.MsTemplate.model.DemoModel;
import tdunu.MsTemplate.service.DemoService;
import tdunu.MsTemplate.util.ApiRoutes;

@RestController
@RequestMapping(ApiRoutes.Demo.BASE)
public class DemoController {

  @Autowired
  DemoService demoService;

  @GetMapping(value = ApiRoutes.Demo.LISTAR)
  public List<DemoModel> listar() {
    return demoService.listar();
  }

  @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
  public DemoModel obtenerPorId(@RequestParam(value = "id") Integer id) {
    return demoService.obtenerPorId(id);
  }

  @PostMapping(value = ApiRoutes.Demo.GUARDAR)
  public DemoModel guardar(@RequestBody DemoModel model) {
    return demoService.guardar(model);
  }

  @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
  public DemoModel eliminar(@RequestParam(value = "id") Integer id) {
    demoService.eliminar(id);
    return null;
  }

}
