package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.service.ConsejoService;

import com.unu.ms.MsConsejo.util.ApiRoutes;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



    

@RestController
@RequestMapping(ApiRoutes.Consejo.BASE)
@Tag(name = "Consejo Controller", description = "Endpoints para gestionar los consejos")
public class ConsejoController {

    @Autowired
    private ConsejoService consejoService;

    @GetMapping(value = ApiRoutes.Consejo.LISTAR)
    public ResponseEntity<List<ConsejoModel>> ListarConsejos() {
        List<ConsejoModel> consejos = (List<ConsejoModel>) consejoService.listar();
        return ResponseEntity.ok(consejos);
    }

   
    @PostMapping(ApiRoutes.Consejo.CREAR)
    public  ResponseEntity<ConsejoResponse> CrearConsejo(@RequestBody ConsejoRequest request) {
        return ResponseEntity.ok(consejoService.crear(request));
    }

    @DeleteMapping(ApiRoutes.Consejo.ELIMINAR)  
    public  ResponseEntity<?> EliminarConsejo(@PathVariable Integer idConsejo) {
        consejoService.eliminar(idConsejo);
        return ResponseEntity.ok("Registro eliminado"); 
    }   

    @PostMapping(ApiRoutes.Consejo.ACTUALIZAR)
    public ResponseEntity<ConsejoResponse> ActualizarMovimiento(
        @PathVariable Integer idConsejo,
        @RequestBody ConsejoRequest request) {
        return ResponseEntity.ok(consejoService.actualizar(idConsejo, request));
    }
    
    
}
