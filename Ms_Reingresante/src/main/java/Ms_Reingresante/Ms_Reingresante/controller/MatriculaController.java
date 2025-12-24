package Ms_Reingresante.Ms_Reingresante.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Ms_Reingresante.Ms_Reingresante.model.request.MatriculaRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.MatriculaResponse;
import Ms_Reingresante.Ms_Reingresante.model.response.resolucionResponse;
import Ms_Reingresante.Ms_Reingresante.service.MatriculaService;
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.MatriculaBase;
import Ms_Reingresante.Ms_Reingresante.util.resolucionBase;



@RestController
@RequestMapping(ApiRoutes.MatriculaReingresante.BASE)
public class MatriculaController {
    
   @Autowired
  MatriculaService MatriculaService;

  @GetMapping(value = ApiRoutes.MatriculaReingresante.LISTAR)
  public MatriculaBase<List<MatriculaResponse>> listar() {
    List<MatriculaResponse> lista = MatriculaService.listarMatricula();
    return MatriculaBase.ok(lista);
  }


  @PostMapping(value = ApiRoutes.MatriculaReingresante.GUARDAR)
  public MatriculaBase<MatriculaResponse> guardar(@RequestBody MatriculaRequest model) {
    MatriculaResponse response = MatriculaService.guardarMatricula(model);
    return MatriculaBase.ok(response);
  }
    

  @GetMapping(value = ApiRoutes.MatriculaReingresante.OBTENER_POR_ID)
  public MatriculaBase<MatriculaResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    MatriculaResponse response = MatriculaService.obtenerPorIdMatricula(id);
    return MatriculaBase.ok(response);
  }

  
   @DeleteMapping(value = ApiRoutes.MatriculaReingresante.ELIMINAR)
  public MatriculaResponse eliminar(@RequestParam(value = "id") Integer id) {
    MatriculaService.eliminarMatricula(id);
    return null;
  }


}