package com.MsSimulacroExamen.ms_SesionSimulacro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MsSimulacroExamen.ms_SesionSimulacro.entity.SesionSimulacro;
import com.MsSimulacroExamen.ms_SesionSimulacro.service.SesionSimulacroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sesiones-simulacro")
@Tag(
        name = "Sesiones de Simulacro",
        description = "CRUD completo para administrar las sesiones de simulacro del CEPRE-UNU"
)
public class SesionSimulacroController {

    private final SesionSimulacroService sesionSimulacroService;
    private static final Logger log = LoggerFactory.getLogger(SesionSimulacroController.class);

    public SesionSimulacroController(SesionSimulacroService sesionSimulacroService) {
        this.sesionSimulacroService = sesionSimulacroService;
    }

    // CREATE - POST
    @Operation(
            summary = "Crear una sesión de simulacro",
            description = "Registra una nueva sesión asociada a un simulacro, incluyendo fecha, hora, aula y estado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sesión creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos enviados")
    })
    @PostMapping
    public ResponseEntity<SesionSimulacro> crearSesion(@RequestBody SesionSimulacro sesion) {
        log.info("Solicitud para crear sesión de simulacro: {}", sesion);
        SesionSimulacro creada = sesionSimulacroService.crearSesion(sesion);
        log.info("Sesión de simulacro creada con ID: {}", creada.getIdSesionSimulacro());
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    // READ ALL - GET
    @Operation(
            summary = "Listar todas las sesiones",
            description = "Obtiene todas las sesiones de simulacro registradas en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sesiones listadas correctamente")
    })
    @GetMapping
    public ResponseEntity<List<SesionSimulacro>> listarSesiones() {
        log.info("Solicitud para listar todas las sesiones de simulacro");
        List<SesionSimulacro> sesiones = sesionSimulacroService.listarSesiones();
        log.info("Se encontraron {} sesiones de simulacro", sesiones.size());
        return ResponseEntity.ok(sesiones);
    }


    // READ BY ID - GET
    @Operation(
            summary = "Obtener sesión por ID",
            description = "Devuelve los datos de una sesión de simulacro específica."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sesión encontrada"),
            @ApiResponse(responseCode = "404", description = "Sesión no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SesionSimulacro> obtenerPorId(@PathVariable Long id) {
        log.info("Solicitud para obtener sesión de simulacro con ID: {}", id);
        SesionSimulacro sesion = sesionSimulacroService.obtenerPorId(id);
        log.info("Sesión encontrada: {}", sesion);
        return ResponseEntity.ok(sesion);
    }

    // UPDATE - PUT
    @Operation(
            summary = "Actualizar una sesión de simulacro",
            description = "Modifica los datos de una sesión de simulacro existente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sesión actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sesión no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SesionSimulacro> actualizarSesion(
            @PathVariable Long id,
            @RequestBody SesionSimulacro sesionActualizada) {
        log.info("Solicitud para actualizar sesión con ID: {}", id);
        SesionSimulacro actualizada = sesionSimulacroService.actualizarSesion(id, sesionActualizada);
        log.info("Sesión actualizada: {}", actualizada)
        return ResponseEntity.ok(actualizada);
    }

    // DELETE - DELETE
    @Operation(
            summary = "Eliminar una sesión de simulacro",
            description = "Elimina una sesión de simulacro por su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sesión eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sesión no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSesion(@PathVariable Long id) {
        log.info("Solicitud para eliminar sesión de simulacro con ID: {}", id);
        sesionSimulacroService.eliminarSesion(id);
        log.info("Sesión eliminada correctamente, ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
