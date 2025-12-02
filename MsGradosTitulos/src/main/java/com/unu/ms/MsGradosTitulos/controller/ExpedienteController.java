package com.unu.ms.MsGradosTitulos.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import com.unu.ms.MsGradosTitulos.dto.ExpedienteDTO;
import com.unu.ms.MsGradosTitulos.service.ExpedienteService;
import com.unu.ms.MsGradosTitulos.util.ApuRoutes;

@RestController
@RequestMapping(ApuRoutes.EXPEDIENTE_BASE)
@Tag(name = "Expedientes", description = "API para gestión de expedientes de grados y títulos")
public class ExpedienteController {
    
    @Autowired
    private ExpedienteService expedienteService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los expedientes", description = "Retorna una lista de todos los expedientes registrados")
    @ApiResponse(responseCode = "200", description = "Lista de expedientes obtenida exitosamente")
    public ResponseEntity<List<ExpedienteDTO>> obtenerTodos() {
        List<ExpedienteDTO> expedientes = expedienteService.obtenerTodos();
        return ResponseEntity.ok(expedientes);
    }
    
 
     
    @GetMapping("/{id}")
    @Operation(summary = "Obtener expediente por ID", description = "Retorna un expediente específico según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Expediente encontrado"),
        @ApiResponse(responseCode = "404", description = "Expediente no encontrado")
    })
    public ResponseEntity<ExpedienteDTO> obtenerPorId(@PathVariable Integer id) {
        Optional<ExpedienteDTO> expediente = expedienteService.obtenerPorId(id);
        
        if (expediente.isPresent()) {
            return ResponseEntity.ok(expediente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PostMapping
    @Operation(summary = "Crear nuevo expediente", description = "Crea un nuevo expediente con los datos proporcionados")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Expediente creado exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExpedienteDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ExpedienteDTO> crear(@RequestBody ExpedienteDTO expedienteDTO) {
        ExpedienteDTO expedienteCreado = expedienteService.crear(expedienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(expedienteCreado);
    }
    
   
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar expediente", description = "Actualiza un expediente existente con los datos proporcionados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Expediente actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Expediente no encontrado")
    })
    public ResponseEntity<ExpedienteDTO> actualizar(@PathVariable Integer id, @RequestBody ExpedienteDTO expedienteDTO) {
        Optional<ExpedienteDTO> expedienteActualizado = expedienteService.actualizar(id, expedienteDTO);
        
        if (expedienteActualizado.isPresent()) {
            return ResponseEntity.ok(expedienteActualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
  
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar expediente", description = "Elimina un expediente existente")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Expediente eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Expediente no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (expedienteService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
