package Ms_Reingresante.Ms_Reingresante.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import Ms_Reingresante.Ms_Reingresante.util.procesoReingresanteBase;


@RestController
@RequestMapping(ApiRoutes.FichaNoAdeudo.BASE)
public class FichaNoAdeudoController {
    
    @Autowired
    FichaNoAdeudoService fichaNoAdeudoService;

    /**
     * Endpoint para obtener todas las fichas de no adeudo.
     */
    @GetMapping(value = ApiRoutes.FichaNoAdeudo.LISTAR)
    public procesoReingresanteBase<List<FichaNoAdeudoResponse>> listar() {
        List<FichaNoAdeudoResponse> lista = fichaNoAdeudoService.listar();
        return procesoReingresanteBase.ok(lista);
    }

    /**
     * Endpoint para obtener una ficha por su ID.
     */
    @GetMapping(value = ApiRoutes.FichaNoAdeudo.OBTENER_POR_ID)
    public procesoReingresanteBase<FichaNoAdeudoResponse> obtenerPorId(@RequestParam(value = "id") Long id) {
        FichaNoAdeudoResponse response = fichaNoAdeudoService.obtenerPorId(id);
        return procesoReingresanteBase.ok(response);
    }

    /**
     * Endpoint para crear una nueva Ficha de No Adeudo (Proceso de Emisión).
     * Nota: El Service manejará la lógica de negocio (ej. verificar que no haya deudas).
     */
    @PostMapping(value = ApiRoutes.FichaNoAdeudo.GUARDAR)
    public procesoReingresanteBase<FichaNoAdeudoResponse> generarFicha(@RequestBody FichaNoAdeudoRequest model) {
        FichaNoAdeudoResponse response = fichaNoAdeudoService.generarFicha(model);
        return procesoReingresanteBase.ok(response);
    }
}