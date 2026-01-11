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
import Ms_Reingresante.Ms_Reingresante.service.ResolucionService;
import Ms_Reingresante.Ms_Reingresante.model.request.ResolucionRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.ResolucionResponse;
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.ResolucionBase;

@RestController
@RequestMapping(ApiRoutes.Resolucion.BASE)

public class ResolucionController {


  @Autowired
  ResolucionService resolucionService;

  @Autowired
  ProductorMessagePublish messageEvent;

  @GetMapping(value = ApiRoutes.Resolucion.LISTAR)
  public ResolucionBase<List<ResolucionResponse>> listar() {
    List<ResolucionResponse> lista = resolucionService.listarResolucion();
    return ResolucionBase.ok(lista);
  }
   
   @GetMapping(value = ApiRoutes.Resolucion.OBTENER_POR_ID)
  public ResolucionBase<ResolucionResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    ResolucionResponse response = resolucionService.obtenerPorIdResolucion(id);
    return ResolucionBase.ok(response);
  }

 
  @PostMapping(value = ApiRoutes.Resolucion.GUARDAR)
  public ResolucionBase<ResolucionResponse> guardar(@RequestBody ResolucionRequest model)
        throws JsonProcessingException {

    ResolucionResponse response = resolucionService.guardarResolucion(model);

    messageEvent.sendResolucionEvent(response);

    return ResolucionBase.ok(response);
 }


   @DeleteMapping(value = ApiRoutes.Resolucion.ELIMINAR)
  public ResolucionResponse eliminar(@RequestParam(value = "id") Integer id) {
    resolucionService.eliminarResolucion(id);
    return null;
  }


}
