package com.service.MsTramiteTesis.controller.estudiante;

import com.service.MsTramiteTesis.config.ApiRoutes;
import com.service.MsTramiteTesis.model.dto.ProyectoRequest;
import com.service.MsTramiteTesis.model.dto.ProyectoResponse;
import com.service.MsTramiteTesis.service.estudiante.EstudianteProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Estudiante.Proyectos.BASE)
@Tag(name = "Estudiante - Proyectos", description = "APIs para que los estudiantes gestionen sus proyectos de tesis")
public class EstudianteProyectoController {

    @Autowired
    private EstudianteProyectoService estudianteProyectoService;

    @PostMapping(ApiRoutes.Estudiante.Proyectos.CREAR)
    @Operation(summary = "Crear nuevo proyecto", description = "Permite al estudiante crear un nuevo proyecto de tesis")
    public ResponseEntity<ProyectoResponse> crearProyecto(
            @RequestParam Integer idEstudiante,
            @Valid @RequestBody ProyectoRequest request) {

        ProyectoResponse response = estudianteProyectoService.crearProyecto(idEstudiante, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(ApiRoutes.Estudiante.Proyectos.ACTUALIZAR_PDF)
    @Operation(summary = "Actualizar PDF del proyecto", description = "Permite al estudiante subir una versión corregida del proyecto")
    public ResponseEntity<ProyectoResponse> actualizarPdf(
            @PathVariable Long id,
            @RequestParam Integer idEstudiante,
            @RequestParam String rutaPdf) {

        ProyectoResponse response = estudianteProyectoService.actualizarPdf(id, idEstudiante, rutaPdf);
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiRoutes.Estudiante.Proyectos.MIS_PROYECTOS)
    @Operation(summary = "Listar mis proyectos", description = "Lista todos los proyectos del estudiante")
    public ResponseEntity<List<ProyectoResponse>> listarMisProyectos(
            @RequestParam Integer idEstudiante) {

        List<ProyectoResponse> proyectos = estudianteProyectoService.listarMisProyectos(idEstudiante);
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping(ApiRoutes.Estudiante.Proyectos.BY_ID)
    @Operation(summary = "Obtener mi proyecto", description = "Obtiene los detalles de un proyecto específico del estudiante")
    public ResponseEntity<ProyectoResponse> obtenerMiProyecto(
            @PathVariable Long id,
            @RequestParam Integer idEstudiante) {

        ProyectoResponse response = estudianteProyectoService.obtenerMiProyecto(id, idEstudiante);
        return ResponseEntity.ok(response);
    }
}
