package com.service.MsTramiteTesis.controller.coordinador;

import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.dto.ProyectoResponseEnriquecido;
import com.service.MsTramiteTesis.model.dto.RevisionRequest;
import com.service.MsTramiteTesis.model.entity.AsignacionJurado;
import com.service.MsTramiteTesis.service.coordinador.CoordinadorProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordinador/proyectos")
@Tag(name = "Coordinador - Proyectos", description = "APIs para el coordinador para administrar y supervisar proyectos")
public class CoordinadorProyectoController {

    @Autowired
    private CoordinadorProyectoService coordinadorProyectoService;

    @GetMapping("/pendientes")
    @Operation(summary = "Listar proyectos pendientes", description = "Lista los proyectos pendientes de revisión del coordinador")
    public ResponseEntity<List<ProyectoResponse>> listarProyectosPendientes() {
        List<ProyectoResponse> proyectos = coordinadorProyectoService.listarProyectosPendientes();
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping
    @Operation(summary = "Listar todos los proyectos", description = "Lista todos los proyectos del sistema")
    public ResponseEntity<List<ProyectoResponse>> listarTodosLosProyectos() {
        List<ProyectoResponse> proyectos = coordinadorProyectoService.listarTodosLosProyectos();
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proyecto", description = "Obtiene los detalles de cualquier proyecto")
    public ResponseEntity<ProyectoResponse> obtenerProyecto(@PathVariable Long id) {
        ProyectoResponse response = coordinadorProyectoService.obtenerCualquierProyecto(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/revision")
    @Operation(summary = "Revisar proyecto", description = "Permite al coordinador aprobar o rechazar el formato de un proyecto")
    public ResponseEntity<ProyectoResponse> revisarProyecto(
            @PathVariable Long id,
            @Valid @RequestBody RevisionRequest request) {

        ProyectoResponse response = coordinadorProyectoService.revisarProyecto(id, request.getAprobado());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/asignar-jurados")
    @Operation(summary = "Asignar jurados", description = "Asigna docentes como jurados a un proyecto")
    public ResponseEntity<List<AsignacionJurado>> asignarJurados(
            @PathVariable Long id,
            @RequestParam List<Integer> idsDocentes,
            @RequestParam Integer rolJuradoCat) {

        List<AsignacionJurado> asignaciones = coordinadorProyectoService.asignarJurados(id, idsDocentes, rolJuradoCat);
        return ResponseEntity.status(HttpStatus.CREATED).body(asignaciones);
    }

    @DeleteMapping("/jurados/{idAsignacion}")
    @Operation(summary = "Desactivar jurado", description = "Desactiva la asignación de un jurado")
    public ResponseEntity<Void> desactivarJurado(@PathVariable Long idAsignacion) {
        coordinadorProyectoService.desactivarJurado(idAsignacion);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/jurados")
    @Operation(summary = "Listar jurados de proyecto", description = "Lista los jurados asignados a un proyecto")
    public ResponseEntity<List<AsignacionJurado>> listarJuradosDeProyecto(@PathVariable Long id) {
        List<AsignacionJurado> jurados = coordinadorProyectoService.listarJuradosDeProyecto(id);
        return ResponseEntity.ok(jurados);
    }

    @GetMapping("/enriquecidos")
    @Operation(summary = "Listar proyectos enriquecidos", description = "Lista todos los proyectos con información completa")
    public ResponseEntity<List<ProyectoResponseEnriquecido>> listarTodosLosProyectosEnriquecidos() {
        List<ProyectoResponseEnriquecido> proyectos = coordinadorProyectoService.listarTodosLosProyectosEnriquecidos();
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping("/{id}/enriquecido")
    @Operation(summary = "Obtener proyecto enriquecido", description = "Obtiene un proyecto con información completa")
    public ResponseEntity<ProyectoResponseEnriquecido> obtenerProyectoEnriquecido(@PathVariable Long id) {
        ProyectoResponseEnriquecido proyecto = coordinadorProyectoService.obtenerProyectoEnriquecido(id);
        return ResponseEntity.ok(proyecto);
    }
}
