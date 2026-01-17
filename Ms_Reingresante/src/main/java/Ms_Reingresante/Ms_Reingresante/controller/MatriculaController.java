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
import Ms_Reingresante.Ms_Reingresante.model.request.MatriculaRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.MatriculaResponse;
import Ms_Reingresante.Ms_Reingresante.service.MatriculaService;
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.MatriculaBase;



@RestController
@RequestMapping(ApiRoutes.MatriculaReingresante.BASE)
public class MatriculaController {
    
   @Autowired
  MatriculaService matriculaService;

    @Autowired
  ProductorMessagePublish messageEvent;

  @GetMapping(value = ApiRoutes.MatriculaReingresante.LISTAR)
  public MatriculaBase<List<MatriculaResponse>> listar() {
    List<MatriculaResponse> lista = matriculaService.listarMatricula();
    return MatriculaBase.ok(lista);
  }


  @PostMapping(value = ApiRoutes.MatriculaReingresante.GUARDAR)
  public MatriculaBase<MatriculaResponse> guardar(@RequestBody MatriculaRequest model)
        throws JsonProcessingException {

    MatriculaResponse response = matriculaService.guardarMatricula(model);

    messageEvent.sendProcesoMatriculaEvent(response);

    return MatriculaBase.ok(response);
}

    

  @GetMapping(value = ApiRoutes.MatriculaReingresante.OBTENER_POR_ID)
  public MatriculaBase<MatriculaResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    MatriculaResponse response = matriculaService.obtenerPorIdMatricula(id);
    return MatriculaBase.ok(response);
  }

  
   @DeleteMapping(value = ApiRoutes.MatriculaReingresante.ELIMINAR)
  public MatriculaResponse eliminar(@RequestParam(value = "id") Integer id) {
    matriculaService.eliminarMatricula(id);
    return null;
  }


}