package com.MsSimulacroExamen.ms_SesionSimulacro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MsSimulacroExamen.ms_SesionSimulacro.entity.SesionSimulacro;
import com.MsSimulacroExamen.ms_SesionSimulacro.service.SesionSimulacroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sesiones-simulacro")
public class SesionSimulacroController {
    private final SesionSimulacroService sesionSimulacroService;

    public SesionSimulacroController(SesionSimulacroService sesionSimulacroService) {
        this.sesionSimulacroService = sesionSimulacroService;
    }

    // CREATE - POST
    @PostMapping
    public ResponseEntity<SesionSimulacro> crearSesion(@RequestBody SesionSimulacro sesion) {
        SesionSimulacro creada = sesionSimulacroService.crearSesion(sesion);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    // READ ALL - GET
    @GetMapping
    public ResponseEntity<List<SesionSimulacro>> listarSesiones() {
        List<SesionSimulacro> sesiones = sesionSimulacroService.listarSesiones();
        return ResponseEntity.ok(sesiones);
    }

    // READ BY ID - GET
    @GetMapping("/{id}")
    public ResponseEntity<SesionSimulacro> obtenerPorId(@PathVariable Long id) {
        SesionSimulacro sesion = sesionSimulacroService.obtenerPorId(id);
        return ResponseEntity.ok(sesion);
    }

    // UPDATE - PUT
    @PutMapping("/{id}")
    public ResponseEntity<SesionSimulacro> actualizarSesion(
            @PathVariable Long id,
            @RequestBody SesionSimulacro sesionActualizada) {

        SesionSimulacro actualizada = sesionSimulacroService.actualizarSesion(id, sesionActualizada);
        return ResponseEntity.ok(actualizada);
    }

    // DELETE - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSesion(@PathVariable Long id) {
        sesionSimulacroService.eliminarSesion(id);
        return ResponseEntity.noContent().build();
    }
}
