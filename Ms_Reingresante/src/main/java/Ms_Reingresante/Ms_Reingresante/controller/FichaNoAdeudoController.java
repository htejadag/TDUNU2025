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
    FichaNoAdeudoService FichaNoAdeudoService;

    @GetMapping(value = ApiRoutes.FichaNoAdeudo.LISTAR)
    public FichaNoAdeudoBase<List<FichaNoAdeudoResponse>> listar() {
        List<FichaNoAdeudoResponse> lista = FichaNoAdeudoService.listarFichaNoAdeudo();
        return FichaNoAdeudoBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.FichaNoAdeudo.OBTENER_POR_ID)
  public FichaNoAdeudoBase<FichaNoAdeudoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    FichaNoAdeudoResponse response = FichaNoAdeudoService.obtenerPorIdFichaNoAdeudo(id);
    return FichaNoAdeudoBase.ok(response);
  }

 
  
 @PostMapping(value = ApiRoutes.FichaNoAdeudo.GUARDAR)
  public FichaNoAdeudoBase<FichaNoAdeudoResponse> guardar(@RequestBody FichaNoAdeudoRequest model) {
    FichaNoAdeudoResponse response = FichaNoAdeudoService.guardarFichaNoAdeudo(model);
    return FichaNoAdeudoBase.ok(response);
  }

   @DeleteMapping(value = ApiRoutes.FichaNoAdeudo.ELIMINAR)
  public FichaNoAdeudoResponse eliminar(@RequestParam(value = "id") Integer id) {
   FichaNoAdeudoService.eliminarFichaNoAdeudo(id);
    return null;
  }




    /**
     * Endpoint para obtener todas las fichas de no adeudo.
     */


    /**
     * Endpoint para crear una nueva Ficha de No Adeudo (Proceso de Emisión).
     * Nota: El Service manejará la lógica de negocio (ej. verificar que no haya deudas).
     */
   
}