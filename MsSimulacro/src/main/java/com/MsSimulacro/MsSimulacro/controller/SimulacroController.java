package com.MsSimulacro.MsSimulacro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MsSimulacro.MsSimulacro.entity.Simulacro;
import com.MsSimulacro.MsSimulacro.service.SimulacroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/simulacros")
@Tag(name = "Simulacros", description = "CRUD de simulacros")
public class SimulacroController {
    private final SimulacroService simulacroService;

    public SimulacroController(SimulacroService simulacroService) {
        this.simulacroService = simulacroService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los simulacros")
    public ResponseEntity<List<Simulacro>> listarTodos() {
        return ResponseEntity.ok(simulacroService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un simulacro por ID")
    public ResponseEntity<Simulacro> obtenerPorId(@PathVariable Long id) {
        return simulacroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un simulacro")
    public ResponseEntity<Simulacro> crear(@RequestBody Simulacro simulacro) {
        Simulacro creado = simulacroService.crear(simulacro);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un simulacro")
    public ResponseEntity<Simulacro> actualizar(@PathVariable Long id,@RequestBody Simulacro simulacro) {
        Simulacro actualizado = simulacroService.actualizar(id, simulacro);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un simulacro")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        simulacroService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }
}
