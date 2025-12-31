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


import Ms_Reingresante.Ms_Reingresante.model.request.InformeAcademicoRequest;

// Importaciones para Informe Académico

import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;
import Ms_Reingresante.Ms_Reingresante.service.InformeAcademicoService;
// Importaciones de Utilidades
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.InformeAcademicoBase;
import Ms_Reingresante.Ms_Reingresante.util.procesoReingresanteBase;


@RestController
@RequestMapping(ApiRoutes.InformeAcademico.BASE)
public class InformeAcademicaController {
    
    @Autowired
    InformeAcademicoService InformeAcademicoService;

    /**
     * Endpoint para obtener todos los informes académicos.
     */
    @GetMapping(value = ApiRoutes.InformeAcademico.LISTAR)
    public procesoReingresanteBase<List<InformeAcademicoResponse>> listar() {
        List<InformeAcademicoResponse> lista = InformeAcademicoService.listar();
        return procesoReingresanteBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.InformeAcademico.OBTENER_POR_ID)
  public InformeAcademicoBase<InformeAcademicoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
    InformeAcademicoResponse response =InformeAcademicoService.obtenerPorId(id);
    return InformeAcademicoBase.ok(response);
  }

  @PostMapping(value = ApiRoutes.InformeAcademico.GUARDAR)
  public InformeAcademicoBase<InformeAcademicoResponse> guardar(@RequestBody InformeAcademicoRequest model) {
   InformeAcademicoResponse response = InformeAcademicoService.guardar(model);
    return InformeAcademicoBase.ok(response);
  }

   @DeleteMapping(value = ApiRoutes.InformeAcademico.ELIMINAR)
  public InformeAcademicoResponse eliminar(@RequestParam(value = "id") Integer id) {
   InformeAcademicoService.eliminar(id);
    return null;
  }


    /**
     * Endpoint para obtener un informe por su ID.
     */
   
    /**
     * Endpoint para solicitar o generar un nuevo Informe Académico asociado a un proceso.
     * El Service manejará la consulta de datos históricos del estudiante.
     */
    
}