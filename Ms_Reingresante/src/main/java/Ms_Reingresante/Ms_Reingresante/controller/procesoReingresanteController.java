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

import com.fasterxml.jackson.core.JsonProcessingException;

import Ms_Reingresante.Ms_Reingresante.message.ProductorMessagePublish;
import Ms_Reingresante.Ms_Reingresante.model.request.ProcesoReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.ProcesoReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.service.ProcesoReingresoService;
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.ProcesoReingresanteBase;



@RestController
@RequestMapping(ApiRoutes.ProcesoReingresante.BASE)
public class ProcesoReingresanteController {
    
@Autowired
  ProcesoReingresoService procesoReingresoService;

 @Autowired
  ProductorMessagePublish messageEvent;

  @GetMapping(value = ApiRoutes.ProcesoReingresante.LISTAR)
  public ProcesoReingresanteBase<List<ProcesoReingresoResponse>> listar() {
    List<ProcesoReingresoResponse> lista = procesoReingresoService.listarProcesoReingreso();
    return ProcesoReingresanteBase.ok(lista);
  }

@GetMapping(value = ApiRoutes.ProcesoReingresante.OBTENER_POR_ID)
  public ProcesoReingresanteBase<ProcesoReingresoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    ProcesoReingresoResponse response = procesoReingresoService.obtenerPorIdProcesoReingreso(id);
    return ProcesoReingresanteBase.ok(response);
  }

@PostMapping(value = ApiRoutes.ProcesoReingresante.GUARDAR)
public ProcesoReingresanteBase<ProcesoReingresoResponse> guardar(@RequestBody ProcesoReingresoRequest model)
        throws JsonProcessingException {

    ProcesoReingresoResponse response = procesoReingresoService.guardarProcesoReingreso(model);

    messageEvent.sendProcesoReingresoEvent(response);

    return ProcesoReingresanteBase.ok(response);
}


    @DeleteMapping(value = ApiRoutes.ProcesoReingresante.ELIMINAR)
  public ProcesoReingresoResponse eliminar(@RequestParam(value = "id") Integer id) {
    procesoReingresoService.eliminarProcesoReingreso(id);
    return null;
  }

}
