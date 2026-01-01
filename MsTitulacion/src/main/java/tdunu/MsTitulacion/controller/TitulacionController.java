package tdunu.MsTitulacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdunu.MsTitulacion.model.request.DictamenPostgradoRequest;
import tdunu.MsTitulacion.model.response.ActaAprobacionResponse;
import tdunu.MsTitulacion.service.TitulacionService;
import tdunu.MsTitulacion.util.ApiRoutes; 
import tdunu.MsTitulacion.config.exception.ResourceException; 

@RestController
@CrossOrigin(origins = "*") 
public class TitulacionController {

    @Autowired
    private TitulacionService titulacionService;

    @PostMapping(ApiRoutes.Dictamen.BASE + ApiRoutes.Dictamen.REGISTRAR)
    public ResponseEntity<?> registrarDictamen(@RequestBody DictamenPostgradoRequest request) {
        
        ActaAprobacionResponse response = titulacionService.procesarDictamenYGenerarActa(request);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
           
            return ResponseEntity.ok("Dictamen registrado correctamente. El resultado no amerita generación de acta automática.");
        }
    }

    @GetMapping(ApiRoutes.Acta.BASE + ApiRoutes.Acta.POR_PROYECTO)
    public ResponseEntity<ActaAprobacionResponse> obtenerActa(@PathVariable Integer idProyecto) {
        
        ActaAprobacionResponse response = titulacionService.obtenerActaPorProyecto(idProyecto);
        
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            throw new ResourceException("No se encontró un acta para el proyecto ID: " + idProyecto);
        }
    }
}