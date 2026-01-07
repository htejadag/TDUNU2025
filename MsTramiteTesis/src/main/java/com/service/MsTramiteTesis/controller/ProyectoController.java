package com.service.MsTramiteTesis.controller;

import com.service.MsTramiteTesis.config.ApiRoutes;
import com.service.MsTramiteTesis.model.dto.ProyectoRequest;
import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.dto.RevisionRequest;
import com.service.MsTramiteTesis.service.ProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Proyectos.BASE)
@Tag(name = "Proyectos", description = "Gestión de proyectos de tesis")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @PostMapping
    @Operation(summary = "Crear proyecto", description = "Permite a un estudiante crear un nuevo proyecto de tesis")
    public ResponseEntity<ProyectoResponse> crearProyecto(
            @RequestParam Long idEstudiante,
            @Valid @RequestBody ProyectoRequest request) {

        ProyectoResponse response = proyectoService.crearProyecto(idEstudiante, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(ApiRoutes.Proyectos.ACTUALIZAR_PDF)
    @Operation(summary = "Actualizar PDF del proyecto", description = "Permite al estudiante subir una versión corregida del proyecto")
    public ResponseEntity<ProyectoResponse> actualizarPdf(
            @PathVariable Long id,
            @RequestParam Long idEstudiante,
            @RequestParam String rutaPdf) {

        ProyectoResponse response = proyectoService.actualizarPdf(id, idEstudiante, rutaPdf);
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiRoutes.Proyectos.MIS_PROYECTOS)
    @Operation(summary = "Mis proyectos", description = "Lista los proyectos del estudiante especificado")
    public ResponseEntity<List<ProyectoResponse>> listarMisProyectos(
            @RequestParam Long idEstudiante) {

        List<ProyectoResponse> proyectos = proyectoService.listarProyectosPorEstudiante(idEstudiante);
        return ResponseEntity.ok(proyectos);
    }

    @PutMapping(ApiRoutes.Proyectos.REVISION_COORDINADOR)
    @Operation(summary = "Revisión del coordinador", description = "Permite al coordinador aprobar o rechazar el formato del proyecto")
    public ResponseEntity<ProyectoResponse> revisionCoordinador(
            @PathVariable Long id,
            @Valid @RequestBody RevisionRequest request) {

        ProyectoResponse response = proyectoService.revisionCoordinador(id, request.getAprobado());
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiRoutes.Proyectos.PENDIENTES_COORDINADOR)
    @Operation(summary = "Proyectos pendientes", description = "Lista los proyectos pendientes de revisión del coordinador")
    public ResponseEntity<List<ProyectoResponse>> listarProyectosPendientes() {
        List<ProyectoResponse> proyectos = proyectoService.listarProyectosPendientesCoordinador();
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping(ApiRoutes.Proyectos.TODOS)
    @Operation(summary = "Todos los proyectos", description = "Lista todos los proyectos del sistema")
    public ResponseEntity<List<ProyectoResponse>> listarTodosProyectos() {
        List<ProyectoResponse> proyectos = proyectoService.listarTodosProyectos();
        return ResponseEntity.ok(proyectos);
    }

    @PutMapping(ApiRoutes.Proyectos.REVISION_ASESOR)
    @Operation(summary = "Revisión del asesor", description = "Permite al asesor aprobar o rechazar el proyecto")
    public ResponseEntity<ProyectoResponse> revisionAsesor(
            @PathVariable Long id,
            @RequestParam Long idAsesor,
            @Valid @RequestBody RevisionRequest request) {

        ProyectoResponse response = proyectoService.revisionAsesor(id, idAsesor, request.getAprobado());
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiRoutes.Proyectos.MIS_ASESORIAS)
    @Operation(summary = "Mis asesorías", description = "Lista los proyectos que el docente está asesorando")
    public ResponseEntity<List<ProyectoResponse>> listarMisAsesorias(
            @RequestParam Long idAsesor) {

        List<ProyectoResponse> proyectos = proyectoService.listarProyectosPorAsesor(idAsesor);
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping(ApiRoutes.Proyectos.BY_ID)
    @Operation(summary = "Obtener proyecto", description = "Obtiene los detalles de un proyecto específico")
    public ResponseEntity<ProyectoResponse> obtenerProyecto(@PathVariable Long id) {
        ProyectoResponse response = proyectoService.obtenerProyecto(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiRoutes.Proyectos.ENRIQUECIDOS)
    @Operation(summary = "Listar proyectos enriquecidos", description = "Lista todos los proyectos con información completa de estudiantes y asesores del MS Personas")
    public ResponseEntity<List<com.service.MsTramiteTesis.model.dto.ProyectoResponseEnriquecido>> listarTodosEnriquecidos() {
        List<com.service.MsTramiteTesis.model.dto.ProyectoResponseEnriquecido> proyectos = proyectoService
                .listarTodosProyectosEnriquecidos();
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping(ApiRoutes.Proyectos.ENRIQUECIDO_BY_ID)
    @Operation(summary = "Obtener proyecto enriquecido", description = "Obtiene un proyecto con información completa de estudiante y asesor")
    public ResponseEntity<com.service.MsTramiteTesis.model.dto.ProyectoResponseEnriquecido> obtenerProyectoEnriquecido(
            @PathVariable Long id) {
        com.service.MsTramiteTesis.model.dto.ProyectoResponseEnriquecido proyecto = proyectoService
                .obtenerProyectoEnriquecido(id);
        return ResponseEntity.ok(proyecto);
    }
}
