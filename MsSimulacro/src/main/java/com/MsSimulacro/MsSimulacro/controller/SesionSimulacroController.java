package com.MsSimulacro.MsSimulacro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MsSimulacro.MsSimulacro.entity.SesionSimulacro;
import com.MsSimulacro.MsSimulacro.service.SesionSimulacroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sesiones-simulacro")
@Tag(name = "Sesiones de Simulacro", description = "CRUD de sesiones de simulacro")
public class SesionSimulacroController {
    private final SesionSimulacroService sesionSimulacroService;

    public SesionSimulacroController(SesionSimulacroService sesionSimulacroService) {
        this.sesionSimulacroService = sesionSimulacroService;
    }
    @GetMapping
    @Operation(summary = "Listar todas las sesiones de simulacro")
    public ResponseEntity<List<SesionSimulacro>> listarTodos() {
        return ResponseEntity.ok(sesionSimulacroService.listarTodos());
    }

    @GetMapping("/simulacro/{simulacroId}")
    @Operation(summary = "Listar sesiones por id de simulacro")
    public ResponseEntity<List<SesionSimulacro>> listarPorSimulacro(@PathVariable Long simulacroId) {
        return ResponseEntity.ok(sesionSimulacroService.listarPorSimulacro(simulacroId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una sesión de simulacro por ID")
    public ResponseEntity<SesionSimulacro> obtenerPorId(@PathVariable Long id) {
        return sesionSimulacroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una sesión de simulacro")
    public ResponseEntity<SesionSimulacro> crear(@RequestBody SesionSimulacro sesion) {
        SesionSimulacro creada = sesionSimulacroService.crear(sesion);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una sesión de simulacro")
    public ResponseEntity<SesionSimulacro> actualizar(@PathVariable Long id,@RequestBody SesionSimulacro sesion) {
        SesionSimulacro actualizada = sesionSimulacroService.actualizar(id, sesion);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar lógicamente una sesión de simulacro")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sesionSimulacroService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }
}
