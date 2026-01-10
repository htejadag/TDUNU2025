package com.service.MsTramiteTesis.controller.docente;

import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.model.dto.RevisionRequest;
import com.service.MsTramiteTesis.model.entity.RevisionProyecto;
import com.service.MsTramiteTesis.service.docente.DocenteProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docente/proyectos")
@Tag(name = "Docente - Proyectos", description = "APIs para docentes (asesores y jurados) para revisar proyectos")
public class DocenteProyectoController {

    @Autowired
    private DocenteProyectoService docenteProyectoService;

    @GetMapping("/asesorias")
    @Operation(summary = "Listar mis asesorías", description = "Lista los proyectos que el docente está asesorando")
    public ResponseEntity<List<ProyectoResponse>> listarMisAsesorias(
            @RequestParam Integer idAsesor) {

        List<ProyectoResponse> proyectos = docenteProyectoService.listarProyectosQueAsesoro(idAsesor);
        return ResponseEntity.ok(proyectos);
    }

    @PutMapping("/{id}/revision-asesor")
    @Operation(summary = "Revisar como asesor", description = "Permite al asesor aprobar o rechazar un proyecto")
    public ResponseEntity<ProyectoResponse> revisarComoAsesor(
            @PathVariable Long id,
            @RequestParam Integer idAsesor,
            @Valid @RequestBody RevisionRequest request) {

        ProyectoResponse response = docenteProyectoService.revisarComoAsesor(id, idAsesor, request.getAprobado());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/jurado")
    @Operation(summary = "Listar proyectos como jurado", description = "Lista los proyectos asignados al docente como jurado")
    public ResponseEntity<List<ProyectoResponse>> listarProyectosComoJurado(
            @RequestParam Integer idDocente) {

        List<ProyectoResponse> proyectos = docenteProyectoService.listarProyectosComoJurado(idDocente);
        return ResponseEntity.ok(proyectos);
    }

    @PostMapping("/{id}/revision-jurado")
    @Operation(summary = "Revisar como jurado", description = "Permite al jurado crear una revisión del proyecto con comentarios")
    public ResponseEntity<RevisionProyecto> revisarComoJurado(
            @PathVariable Long id,
            @RequestParam Integer idDocente,
            @RequestParam Integer rolJuradoCat,
            @RequestParam String comentarios,
            @RequestParam Integer resultadoCat) {

        RevisionProyecto revision = docenteProyectoService.revisarComoJurado(
                id, idDocente, rolJuradoCat, comentarios, resultadoCat);
        return ResponseEntity.status(HttpStatus.CREATED).body(revision);
    }

    @GetMapping("/{id}/revisiones")
    @Operation(summary = "Ver revisiones del proyecto", description = "Obtiene todas las revisiones realizadas a un proyecto")
    public ResponseEntity<List<RevisionProyecto>> obtenerRevisionesDeProyecto(
            @PathVariable Long id) {

        List<RevisionProyecto> revisiones = docenteProyectoService.obtenerRevisionesDeProyecto(id);
        return ResponseEntity.ok(revisiones);
    }
}
