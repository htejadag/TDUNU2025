package msgestionlegal.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import MsGL.MS_Gestion_Legal.Service.ExpedienteFinalService;    
import MsGL.MS_Gestion_Legal.domain.model.ExpedienteFinal;


import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expedientes-finales")
@RequiredArgsConstructor
@Tag(name = "Expedientes Finales", description = "CRUD completo de Expediente Final")
public class ExpedienteFinalController {
     private final ExpedienteFinalService service;

    @PostMapping
    @Operation(summary = "Crear expediente final")
    public ResponseEntity<ExpedienteFinal> crear(@RequestBody ExpedienteFinal ex) {
        return new ResponseEntity<>(service.crear(ex), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar expedientes")
    public ResponseEntity<List<ExpedienteFinal>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar expediente por ID")
    public ResponseEntity<ExpedienteFinal> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado del expediente")
    public ResponseEntity<ExpedienteFinal> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado,
            @RequestParam(required = false) String observaciones) {

        return ResponseEntity.ok(service.actualizarEstado(id, estado, observaciones)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar expediente")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
