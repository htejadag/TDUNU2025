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
// Importaciones para Ficha No Adeudo
import Ms_Reingresante.Ms_Reingresante.model.request.FichaNoAdeudoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.FichaNoAdeudoResponse;
import Ms_Reingresante.Ms_Reingresante.service.FichaNoAdeudoService;
// Importaciones de Utilidades (asumiendo que sigues la misma estructura)
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.FichaNoAdeudoBase;





@RestController
@RequestMapping(ApiRoutes.FichaNoAdeudo.BASE)
public class FichaNoAdeudoController {
    
    @Autowired
    FichaNoAdeudoService fichaNoAdeudoService;

    @Autowired
    ProductorMessagePublish messageEvent;

    @GetMapping(value = ApiRoutes.FichaNoAdeudo.LISTAR)
    public FichaNoAdeudoBase<List<FichaNoAdeudoResponse>> listar() {
        List<FichaNoAdeudoResponse> lista = fichaNoAdeudoService.listarFichaNoAdeudo();
        return FichaNoAdeudoBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.FichaNoAdeudo.OBTENER_POR_ID)
  public FichaNoAdeudoBase<FichaNoAdeudoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    FichaNoAdeudoResponse response = fichaNoAdeudoService.obtenerPorIdFichaNoAdeudo(id);
    return FichaNoAdeudoBase.ok(response);
  }

 
  
 @PostMapping(value = ApiRoutes.FichaNoAdeudo.GUARDAR)
  public FichaNoAdeudoBase<FichaNoAdeudoResponse> guardar(@RequestBody FichaNoAdeudoRequest model)
        throws JsonProcessingException {

    FichaNoAdeudoResponse response = fichaNoAdeudoService.guardarFichaNoAdeudo(model);

    messageEvent.sendFichaNoAdeudoEvent(response);

    return FichaNoAdeudoBase.ok(response);
}

   @DeleteMapping(value = ApiRoutes.FichaNoAdeudo.ELIMINAR)
  public FichaNoAdeudoResponse eliminar(@RequestParam(value = "id") Integer id) {
   fichaNoAdeudoService.eliminarFichaNoAdeudo(id);
    return null;
  }




   
}