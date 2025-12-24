package com.service.MsTramiteTesis.controller;

import com.service.MsTramiteTesis.model.dto.ProyectoResponseEnriquecido;
import com.service.MsTramiteTesis.service.ProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para endpoints que retornan proyectos con información enriquecida
 * del MS Personas
 */
@RestController
@RequestMapping("/api/proyectos-enriquecidos")
@Tag(name = "Proyectos Enriquecidos", description = "Endpoints que incluyen información completa de estudiantes y docentes")
@SecurityRequirement(name = "bearerAuth")
public class ProyectoEnriquecidoController {

    @Autowired
    private ProyectoService proyectoService;

    /**
     * COORDINADOR: Listar todos los proyectos con información completa
     */
    @GetMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    @Operation(summary = "Listar proyectos enriquecidos", description = "Lista todos los proyectos con información completa de estudiantes y asesores del MS Personas")
    public ResponseEntity<List<ProyectoResponseEnriquecido>> listarTodosEnriquecidos() {
        List<ProyectoResponseEnriquecido> proyectos = proyectoService.listarTodosProyectosEnriquecidos();
        return ResponseEntity.ok(proyectos);
    }

    /**
     * TODOS: Obtener un proyecto específico con información enriquecida
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ESTUDIANTE', 'DOCENTE', 'COORDINADOR')")
    @Operation(summary = "Obtener proyecto enriquecido", description = "Obtiene un proyecto con información completa de estudiante y asesor")
    public ResponseEntity<ProyectoResponseEnriquecido> obtenerProyectoEnriquecido(@PathVariable Long id) {
        ProyectoResponseEnriquecido proyecto = proyectoService.obtenerProyectoEnriquecido(id);
        return ResponseEntity.ok(proyecto);
    }
}
