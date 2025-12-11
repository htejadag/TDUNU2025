package com.MsSimulacro.MsSimulacro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MsSimulacro.MsSimulacro.entity.ResultadoSimulacro;
import com.MsSimulacro.MsSimulacro.service.ResultadoSimulacroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resultados-simulacro")
@Tag(name = "Resultados de Simulacro", description = "CRUD de resultados")
public class ResultadoSimulacroController {
    private final ResultadoSimulacroService resultadoSimulacroService;

    public ResultadoSimulacroController(ResultadoSimulacroService resultadoSimulacroService) {
        this.resultadoSimulacroService = resultadoSimulacroService;
    }

    @GetMapping
    public ResponseEntity<List<ResultadoSimulacro>> listarTodos() {
        return ResponseEntity.ok(resultadoSimulacroService.listarTodos());
    }

    @GetMapping("/sesion/{sesionId}")
    public ResponseEntity<List<ResultadoSimulacro>> listarPorSesion(@PathVariable Long sesionId) {
        return ResponseEntity.ok(resultadoSimulacroService.listarPorSesion(sesionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoSimulacro> obtenerPorId(@PathVariable Long id) {
        return resultadoSimulacroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResultadoSimulacro> crear(@RequestBody ResultadoSimulacro resultado) {
        ResultadoSimulacro creado = resultadoSimulacroService.crear(resultado);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultadoSimulacro> actualizar(@PathVariable Long id,@RequestBody ResultadoSimulacro resultado) {
        ResultadoSimulacro actualizado = resultadoSimulacroService.actualizar(id, resultado);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resultadoSimulacroService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }

}
