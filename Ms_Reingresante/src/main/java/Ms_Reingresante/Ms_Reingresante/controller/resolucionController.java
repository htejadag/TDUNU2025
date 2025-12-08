package Ms_Reingresante.Ms_Reingresante.controller;



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

import Ms_Reingresante.Ms_Reingresante.model.request.resolucionRequest;
import Ms_Reingresante.Ms_Reingresante.service.ResolucionService;
import Ms_Reingresante.Ms_Reingresante.model.response.resolucionResponse;
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.resolucionBase;

@RestController
@RequestMapping(ApiRoutes.Resolucion.BASE)
public class resolucionController {
    
    @Autowired
  ResolucionService resolucionService;

  @GetMapping(value = ApiRoutes.Resolucion.LISTAR)
  public resolucionBase<List<resolucionResponse>> listar() {
    List<resolucionResponse> lista = resolucionService.listar();
    return resolucionBase.ok(lista);
  }
   
   @GetMapping(value = ApiRoutes.Resolucion.OBTENER_POR_ID)
  public resolucionBase<resolucionResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    resolucionResponse response = resolucionService.obtenerPorId(id);
    return resolucionBase.ok(response);
  }

 @PostMapping(value = ApiRoutes.Resolucion.GUARDAR)
  public resolucionBase<resolucionResponse> guardar(@RequestBody resolucionRequest model) {
    resolucionResponse response = resolucionService.guardar(model);
    return resolucionBase.ok(response);
  }

   @DeleteMapping(value = ApiRoutes.Resolucion.ELIMINAR)
  public resolucionResponse eliminar(@RequestParam(value = "id") Integer id) {
    resolucionService.eliminar(id);
    return null;
  }


}
