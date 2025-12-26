package com.service.MsTramiteTesis.controller;

import com.service.MsTramiteTesis.config.ApiRoutes;
import com.service.MsTramiteTesis.model.dto.ProyectoRequest;
import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.dto.RevisionRequest;
import com.service.MsTramiteTesis.service.ProyectoService;
import com.service.MsTramiteTesis.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Proyectos.BASE)
@Tag(name = "Proyectos", description = "Gestión de proyectos de tesis")
@SecurityRequirement(name = "bearerAuth")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @PreAuthorize("hasRole('ESTUDIANTE')")
    @Operation(summary = "Crear proyecto", description = "Permite a un estudiante crear un nuevo proyecto de tesis")
    public ResponseEntity<ProyectoResponse> crearProyecto(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody ProyectoRequest request) {

        String token = authHeader.substring(7);
        Long idEstudiante = jwtUtil.extractUserId(token);

        ProyectoResponse response = proyectoService.crearProyecto(idEstudiante, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(ApiRoutes.Proyectos.ACTUALIZAR_PDF)
    @PreAuthorize("hasRole('ESTUDIANTE')")
    @Operation(summary = "Actualizar PDF del proyecto", description = "Permite al estudiante subir una versión corregida del proyecto")
    public ResponseEntity<ProyectoResponse> actualizarPdf(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String rutaPdf) {

        String token = authHeader.substring(7);
        Long idEstudiante = jwtUtil.extractUserId(token);

        ProyectoResponse response = proyectoService.actualizarPdf(id, idEstudiante, rutaPdf);
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiRoutes.Proyectos.MIS_PROYECTOS)
    @PreAuthorize("hasRole('ESTUDIANTE')")
    @Operation(summary = "Mis proyectos", description = "Lista los proyectos del estudiante autenticado")
    public ResponseEntity<List<ProyectoResponse>> listarMisProyectos(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        Long idEstudiante = jwtUtil.extractUserId(token);

        List<ProyectoResponse> proyectos = proyectoService.listarProyectosPorEstudiante(idEstudiante);
        return ResponseEntity.ok(proyectos);
    }

    @PutMapping(ApiRoutes.Proyectos.REVISION_COORDINADOR)
    @PreAuthorize("hasRole('COORDINADOR')")
    @Operation(summary = "Revisión del coordinador", description = "Permite al coordinador aprobar o rechazar el formato del proyecto")
    public ResponseEntity<ProyectoResponse> revisionCoordinador(
            @PathVariable Long id,
            @Valid @RequestBody RevisionRequest request) {

        ProyectoResponse response = proyectoService.revisionCoordinador(id, request.getAprobado());
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiRoutes.Proyectos.PENDIENTES_COORDINADOR)
    @PreAuthorize("hasRole('COORDINADOR')")
    @Operation(summary = "Proyectos pendientes", description = "Lista los proyectos pendientes de revisión del coordinador")
    public ResponseEntity<List<ProyectoResponse>> listarProyectosPendientes() {
        List<ProyectoResponse> proyectos = proyectoService.listarProyectosPendientesCoordinador();
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping(ApiRoutes.Proyectos.TODOS)
    @PreAuthorize("hasRole('COORDINADOR')")
    @Operation(summary = "Todos los proyectos", description = "Lista todos los proyectos del sistema")
    public ResponseEntity<List<ProyectoResponse>> listarTodosProyectos() {
        List<ProyectoResponse> proyectos = proyectoService.listarTodosProyectos();
        return ResponseEntity.ok(proyectos);
    }

    @PutMapping(ApiRoutes.Proyectos.REVISION_ASESOR)
    @PreAuthorize("hasRole('DOCENTE')")
    @Operation(summary = "Revisión del asesor", description = "Permite al asesor aprobar o rechazar el proyecto")
    public ResponseEntity<ProyectoResponse> revisionAsesor(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody RevisionRequest request) {

        String token = authHeader.substring(7);
        Long idAsesor = jwtUtil.extractUserId(token);

        ProyectoResponse response = proyectoService.revisionAsesor(id, idAsesor, request.getAprobado());
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiRoutes.Proyectos.MIS_ASESORIAS)
    @PreAuthorize("hasRole('DOCENTE')")
    @Operation(summary = "Mis asesorías", description = "Lista los proyectos que el docente está asesorando")
    public ResponseEntity<List<ProyectoResponse>> listarMisAsesorias(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        Long idAsesor = jwtUtil.extractUserId(token);

        List<ProyectoResponse> proyectos = proyectoService.listarProyectosPorAsesor(idAsesor);
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping(ApiRoutes.Proyectos.BY_ID)
    @PreAuthorize("hasAnyRole('ESTUDIANTE', 'DOCENTE', 'COORDINADOR')")
    @Operation(summary = "Obtener proyecto", description = "Obtiene los detalles de un proyecto específico")
    public ResponseEntity<ProyectoResponse> obtenerProyecto(@PathVariable Long id) {
        ProyectoResponse response = proyectoService.obtenerProyecto(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ApiRoutes.Testing.BASE_KAFKA)
    @Operation(summary = "TEST Kafka - Crear proyecto (SIN AUTH)", description = "SOLO PARA TESTING - Crea un proyecto y dispara evento Kafka sin requerir autenticación. ELIMINAR EN PRODUCCIÓN.")
    public ResponseEntity<ProyectoResponse> testKafkaCrearProyecto(
            @Valid @RequestBody ProyectoRequest request) {

        Long idEstudianteTest = 1L;

        ProyectoResponse response = proyectoService.crearProyecto(idEstudianteTest, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(ApiRoutes.Testing.BASE_KAFKA + ApiRoutes.Testing.ACTUALIZAR_ESTADO)
    @Operation(summary = "TEST Kafka - Actualizar estado (SIN AUTH)", description = "SOLO PARA TESTING - Actualiza estado del proyecto sin autenticación. ELIMINAR EN PRODUCCIÓN.")
    public ResponseEntity<ProyectoResponse> testKafkaActualizarEstado(
            @PathVariable Long id,
            @RequestParam String estadoNuevo) {

        ProyectoResponse response = proyectoService.actualizarEstadoTest(id, estadoNuevo);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(ApiRoutes.Testing.BASE_KAFKA + ApiRoutes.Testing.ELIMINAR_PROYECTO)
    @Operation(summary = "TEST Kafka - Eliminar proyecto (SIN AUTH)", description = "SOLO PARA TESTING - Elimina proyecto sin autenticación. ELIMINAR EN PRODUCCIÓN.")
    public ResponseEntity<Void> testKafkaEliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyectoTest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(ApiRoutes.Testing.BASE_ALL)
    @Operation(summary = "TEST - Listar todos (SIN AUTH)", description = "SOLO PARA TESTING - Lista todos los proyectos sin autenticación.")
    public ResponseEntity<List<ProyectoResponse>> testListarTodos() {
        List<ProyectoResponse> proyectos = proyectoService.listarTodosProyectos();
        return ResponseEntity.ok(proyectos);
    }
}
