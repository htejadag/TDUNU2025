package unu.MsGestionDocumental.MsExpediente.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import unu.MsGestionDocumental.MsExpediente.service.expedienteIService;
import unu.MsGestionDocumental.MsExpediente.model.request.requestExpediente;
import unu.MsGestionDocumental.MsExpediente.model.response.responseExpediente;
import unu.MsGestionDocumental.MsExpediente.util.ResponseBase;
import unu.MsGestionDocumental.MsExpediente.util.apiRoutes; 

@RestController
@RequestMapping (apiRoutes.Demo.BASE)
public class ExpedienteController {

    @Autowired
    private expedienteIService expedienteService;
    
    @PostMapping(value = apiRoutes.Demo.GUARDAR)
    public ResponseEntity<ResponseBase<responseExpediente>> guardarExpediente(@Valid @RequestBody requestExpediente request) {
        responseExpediente creado = expedienteService.guardar(request);        
        return new ResponseEntity<>(
            ResponseBase.ok("Expediente creado correctamente", creado), 
            HttpStatus.CREATED 
        );
    }
    
    @GetMapping(value = apiRoutes.Demo.LISTAR)
    public ResponseEntity<ResponseBase<List<responseExpediente>>> listarExpedientes() {
        
        List<responseExpediente> expedientes = expedienteService.listar();        
        return new ResponseEntity<>(
            ResponseBase.ok(expedientes), 
            HttpStatus.OK
        );
    }

    @GetMapping(value = apiRoutes.Demo.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<responseExpediente>> obtenerExpedientePorId(@PathVariable Integer id) {

        responseExpediente expediente = expedienteService.obtenerPorId(id);
        
        return new ResponseEntity<>(
            ResponseBase.ok(expediente), 
            HttpStatus.OK
        );
    }

    @PutMapping(value = apiRoutes.Demo.ACTUALIZAR)
    public ResponseEntity<ResponseBase<responseExpediente>> actualizarExpediente(@Valid @RequestBody requestExpediente request) {

        responseExpediente actualizado = expedienteService.guardar(request);
        
        return new ResponseEntity<>(
            ResponseBase.ok("Expediente actualizado correctamente", actualizado), 
            HttpStatus.OK
        );
    }

    @DeleteMapping(value = apiRoutes.Demo.ELIMINAR)
    public ResponseEntity<ResponseBase<String>> eliminarExpediente(@PathVariable Integer id) {
        
        expedienteService.eliminar(id);
        
        return new ResponseEntity<>(
            ResponseBase.ok("Expediente eliminado lógicamente con éxito."), 
            HttpStatus.OK 
        );
    }
}