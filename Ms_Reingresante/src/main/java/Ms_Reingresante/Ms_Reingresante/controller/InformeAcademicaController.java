package Ms_Reingresante.Ms_Reingresante.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Importaciones para Informe Académico
import Ms_Reingresante.Ms_Reingresante.model.request.InformeAcademicoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.InformeAcademicoResponse;
import Ms_Reingresante.Ms_Reingresante.service.InformeAcademicoService;

// Importaciones de Utilidades
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.procesoReingresanteBase;


@RestController
@RequestMapping(ApiRoutes.InformeAcademico.BASE)
public class InformeAcademicoController {
    
    @Autowired
    InformeAcademicoService informeAcademicoService;

    /**
     * Endpoint para obtener todos los informes académicos.
     */
    @GetMapping(value = ApiRoutes.InformeAcademico.LISTAR)
    public procesoReingresanteBase<List<InformeAcademicoResponse>> listar() {
        List<InformeAcademicoResponse> lista = informeAcademicoService.listar();
        return procesoReingresanteBase.ok(lista);
    }

    /**
     * Endpoint para obtener un informe por su ID.
     */
    @GetMapping(value = ApiRoutes.InformeAcademico.OBTENER_POR_ID)
    public procesoReingresanteBase<InformeAcademicoResponse> obtenerPorId(@RequestParam(value = "id") Long id) {
        InformeAcademicoResponse response = informeAcademicoService.obtenerPorId(id);
        return procesoReingresanteBase.ok(response);
    }

    /**
     * Endpoint para solicitar o generar un nuevo Informe Académico asociado a un proceso.
     * El Service manejará la consulta de datos históricos del estudiante.
     */
    @PostMapping(value = ApiRoutes.InformeAcademico.GUARDAR)
    public procesoReingresanteBase<InformeAcademicoResponse> generarInforme(@RequestBody InformeAcademicoRequest model) {
        InformeAcademicoResponse response = informeAcademicoService.generarInforme(model);
        return procesoReingresanteBase.ok(response);
    }
}