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
import Ms_Reingresante.Ms_Reingresante.model.request.InformeAcademicoRequest;

// Importaciones para Informe Académico

import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;
import Ms_Reingresante.Ms_Reingresante.service.InformeAcademicoService;
// Importaciones de Utilidades
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.InformeAcademicoBase;
import Ms_Reingresante.Ms_Reingresante.util.ProcesoReingresanteBase;



@RestController
@RequestMapping(ApiRoutes.InformeAcademico.BASE)
public class InformeAcademicaController {
    
    @Autowired
    InformeAcademicoService informeAcademicoService;

     @Autowired
   ProductorMessagePublish messageEvent;
   
   
    @GetMapping(value = ApiRoutes.InformeAcademico.LISTAR)
    public ProcesoReingresanteBase<List<InformeAcademicoResponse>> listar() {
        List<InformeAcademicoResponse> lista = informeAcademicoService.listarInformeAcademico();
        return ProcesoReingresanteBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.InformeAcademico.OBTENER_POR_ID)
  public InformeAcademicoBase<InformeAcademicoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    InformeAcademicoResponse response =informeAcademicoService.obtenerPorIdInformeAcademico(id);
    return InformeAcademicoBase.ok(response);
  }

   @PostMapping(value = ApiRoutes.InformeAcademico.GUARDAR)
    public InformeAcademicoBase<InformeAcademicoResponse> guardar(@RequestBody InformeAcademicoRequest model)
        throws JsonProcessingException {

    InformeAcademicoResponse response = informeAcademicoService.guardarInformeAcademico(model);

    messageEvent.sendProcesoInformeAcademicoEvent(response);

    return InformeAcademicoBase.ok(response);
}


   @DeleteMapping(value = ApiRoutes.InformeAcademico.ELIMINAR)
  public InformeAcademicoResponse eliminar(@RequestParam(value = "id") Integer id) {
   informeAcademicoService.eliminarInformeAcademico(id);
    return null;
  }


    
}