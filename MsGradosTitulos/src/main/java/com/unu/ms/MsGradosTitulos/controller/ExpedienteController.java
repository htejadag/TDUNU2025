package com.unu.ms.MsGradosTitulos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsGradosTitulos.dto.ExpedienteRequest;
import com.unu.ms.MsGradosTitulos.dto.ExpedienteResponse;
import com.unu.ms.MsGradosTitulos.service.IExpedienteService;
import com.unu.ms.MsGradosTitulos.util.ApiRoutes;





@RestController
@RequestMapping(ApiRoutes.BASE)
public class ExpedienteController {
    
    @Autowired
    private IExpedienteService expedienteService;

    // ------------------------------------------------------------------
    // 2. CREAR
    // ------------------------------------------------------------------
    
    @PostMapping(ApiRoutes.CREAR)
    public ResponseEntity<?> crearExpediente(@RequestBody ExpedienteRequest request) {
        ExpedienteResponse creado = expedienteService.agregar(request);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }
    
    // ------------------------------------------------------------------
    // 2. LISTAR
    // ------------------------------------------------------------------
    
    @GetMapping(ApiRoutes.LISTAR)
    public ResponseEntity<List<ExpedienteResponse>> obtenerExpedientes() {
        List<ExpedienteResponse> lista = expedienteService.obtenerExpedientes();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // ------------------------------------------------------------------
    // 3. ACTUALIZAR
    // ------------------------------------------------------------------
    @PutMapping(ApiRoutes.ACTUALIZAR)
    public ResponseEntity<ExpedienteResponse> actualizarExpediente(
            @PathVariable Integer id,
            @RequestBody ExpedienteRequest request) {

        // Llamamos al servicio
        ExpedienteResponse response = expedienteService.actualizarExpediente(id, request);

        // Si el servicio devolvió algo, es éxito. Si devolvió null, es que el ID no existía.
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ------------------------------------------------------------------
    // 4. OBTENER POR ID
    // ------------------------------------------------------------------
    
    @GetMapping(ApiRoutes.OBTENER_POR_ID)
    public ResponseEntity<ExpedienteResponse> obtenerExpedientePorId(@PathVariable Integer id) {
        ExpedienteResponse response = expedienteService.obtenerExpedientePorId(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // ------------------------------------------------------------------
    // 5. ELIMINAR
    // ------------------------------------------------------------------

    @DeleteMapping(ApiRoutes.ELIMINAR)
    public ResponseEntity<Void> eliminarExpediente(@PathVariable Integer id) {
        boolean eliminado = expedienteService.eliminarExpediente(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
