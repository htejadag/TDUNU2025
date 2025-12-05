package Ms_Reingresante.Ms_Reingresante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Ms_Reingresante.Ms_Reingresante.model.request.procesoReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.procesoReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.service.ProcesoReingresoService;
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.procesoReingresanteBase;




@RestController
@RequestMapping(ApiRoutes.ProcesoReingresante.BASE)
public class procesoReingresanteController {
    
@Autowired
  ProcesoReingresoService ProcesoReingresoService;

  @GetMapping(value = ApiRoutes.ProcesoReingresante.LISTAR)
  public procesoReingresanteBase<List<procesoReingresoResponse>> listar() {
    List<procesoReingresoResponse> lista = ProcesoReingresoService.listar();
    return procesoReingresanteBase.ok(lista);
  }

@GetMapping(value = ApiRoutes.ProcesoReingresante.OBTENER_POR_ID)
  public procesoReingresanteBase<procesoReingresoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    procesoReingresoResponse response = ProcesoReingresoService.obtenerPorId(id);
    return procesoReingresanteBase.ok(response);
  }

@PostMapping(value = ApiRoutes.ProcesoReingresante.GUARDAR)
  public procesoReingresanteBase<procesoReingresoResponse> guardar(@RequestBody procesoReingresoRequest model) {
    procesoReingresoResponse response = ProcesoReingresoService.guardar(model);
    return procesoReingresanteBase.ok(response);
  }

    @DeleteMapping(value = ApiRoutes.ProcesoReingresante.ELIMINAR)
  public procesoReingresoResponse eliminar(@RequestParam(value = "id") Integer id) {
    ProcesoReingresoService.eliminar(id);
    return null;
  }

}
