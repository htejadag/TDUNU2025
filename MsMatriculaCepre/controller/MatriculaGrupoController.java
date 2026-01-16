package unu.MsMatriculaCepre.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unu.MsMatriculaCepre.model.request.GrupoRequest;
import unu.MsMatriculaCepre.model.request.MatriculaGrupoRequest;
import unu.MsMatriculaCepre.model.response.GrupoResponse;
import unu.MsMatriculaCepre.model.response.MatriculaGrupoResponse;
import unu.MsMatriculaCepre.service.MatriculaGrupoService;
import unu.MsMatriculaCepre.util.ResponseBase;
import unu.MsMatriculaCepre.util.Turno;

import java.util.List;

@RestController
@RequestMapping("/api/matricula-grupos")
@CrossOrigin(origins = "*")
@Tag(name = "Sistema de Matrícula CEPRE-UNU", description = "API completa para gestión de matrículas y grupos académicos")
public class MatriculaGrupoController {
    
    @Autowired
    private MatriculaGrupoService matriculaGrupoService;
    
    // ==================== ENDPOINTS DE GRUPOS ====================
    
    @Operation(
        summary = "Crear nuevo grupo académico",
        description = "Crea un nuevo grupo con turno (MAÑANA/TARDE), aula, capacidad y horario",
        tags = {"1. Gestión de Grupos"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "✅ Grupo creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Error: Código duplicado o datos inválidos")
    })
    @PostMapping("/grupos")
    public ResponseEntity<ResponseBase<GrupoResponse>> crearGrupo(@RequestBody GrupoRequest request) {
        try {
            GrupoResponse response = matriculaGrupoService.crearGrupo(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseBase.ok("Grupo creado exitosamente", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseBase.error(e.getMessage()));
        }
    }
    
    @Operation(
        summary = "Actualizar grupo existente",
        description = "Modifica los datos de un grupo: nombre, turno, aula, capacidad, horario, docente",
        tags = {"1. Gestión de Grupos"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = " Grupo actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = " Grupo no encontrado"),
        @ApiResponse(responseCode = "400", description = " Datos inválidos")
    })
    @PutMapping("/grupos/{id}")
    public ResponseEntity<ResponseBase<GrupoResponse>> actualizarGrupo(
            @Parameter(description = "ID del grupo", required = true, example = "1")
            @PathVariable Long id,
            @RequestBody GrupoRequest request) {
        try {
            GrupoResponse response = matriculaGrupoService.actualizarGrupo(id, request);
            return ResponseEntity.ok(ResponseBase.ok("Grupo actualizado exitosamente", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseBase.error(e.getMessage()));
        }
    }
    
    @Operation(
        summary = "Eliminar grupo",
        description = "Marca un grupo como INACTIVO. Solo se puede eliminar si no tiene estudiantes asignados",
        tags = {"1. Gestión de Grupos"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = " Grupo eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = " El grupo tiene estudiantes asignados"),
        @ApiResponse(responseCode = "404", description = " Grupo no encontrado")
    })
    @DeleteMapping("/grupos/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarGrupo(
            @Parameter(description = "ID del grupo a eliminar", required = true, example = "1")
            @PathVariable Long id) {
        try {
            matriculaGrupoService.eliminarGrupo(id);
            return ResponseEntity.ok(ResponseBase.ok("Grupo eliminado exitosamente", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseBase.error(e.getMessage()));
        }
    }
    
    @Operation(
        summary = "Obtener grupo por ID",
        description = "Consulta la información completa de un grupo específico",
        tags = {"3. Consultas"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = " Grupo encontrado"),
        @ApiResponse(responseCode = "404", description = " Grupo no encontrado")
    })
    @GetMapping("/grupos/{id}")
    public ResponseEntity<ResponseBase<GrupoResponse>> obtenerGrupo(
            @Parameter(description = "ID del grupo", required = true, example = "1")
            @PathVariable Long id) {
        try {
            GrupoResponse response = matriculaGrupoService.obtenerGrupoPorId(id);
            return ResponseEntity.ok(ResponseBase.ok("Grupo encontrado", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseBase.error(e.getMessage()));
        }
    }
    
    @Operation(
        summary = "Listar todos los grupos",
        description = "Obtiene la lista completa de grupos académicos",
        tags = {"3. Consultas"}
    )
    @ApiResponse(responseCode = "200", description = " Lista obtenida exitosamente")
    @GetMapping("/grupos")
    public ResponseEntity<ResponseBase<List<GrupoResponse>>> listarGrupos() {
        List<GrupoResponse> grupos = matriculaGrupoService.listarGrupos();
        return ResponseEntity.ok(ResponseBase.ok("Grupos listados exitosamente", grupos));
    }
    
    @Operation(
        summary = "Listar grupos disponibles",
        description = "Obtiene solo los grupos con cupos disponibles para un proceso académico",
        tags = {"3. Consultas"}
    )
    @ApiResponse(responseCode = "200", description = " Grupos disponibles obtenidos")
    @GetMapping("/grupos/disponibles")
    public ResponseEntity<ResponseBase<List<GrupoResponse>>> listarGruposDisponibles(
            @Parameter(description = "Proceso académico", required = true, example = "2026-I")
            @RequestParam String procesoAcademico) {
        List<GrupoResponse> grupos = matriculaGrupoService.listarGruposDisponibles(procesoAcademico);
        return ResponseEntity.ok(ResponseBase.ok("Grupos disponibles obtenidos", grupos));
    }
    
    @Operation(
        summary = "Listar grupos por proceso académico",
        description = "Obtiene todos los grupos activos de un ciclo específico",
        tags = {"3. Consultas"}
    )
    @GetMapping("/grupos/proceso/{procesoAcademico}")
    public ResponseEntity<ResponseBase<List<GrupoResponse>>> listarGruposPorProceso(
            @Parameter(description = "Proceso académico", required = true, example = "2026-I")
            @PathVariable String procesoAcademico) {
        List<GrupoResponse> grupos = matriculaGrupoService.listarGruposPorProceso(procesoAcademico);
        return ResponseEntity.ok(ResponseBase.ok("Grupos obtenidos", grupos));
    }
    
    @Operation(
        summary = "Listar grupos por turno",
        description = "Filtra grupos por turno: MAÑANA o TARDE",
        tags = {"3. Consultas"}
    )
    @GetMapping("/grupos/turno/{turno}")
    public ResponseEntity<ResponseBase<List<GrupoResponse>>> listarGruposPorTurno(
            @Parameter(description = "Turno del grupo", required = true, 
                      schema = @Schema(allowableValues = {"MAÑANA", "TARDE"}))
            @PathVariable Turno turno) {
        try {
            List<GrupoResponse> grupos = matriculaGrupoService.listarGruposPorTurno(turno);
            return ResponseEntity.ok(ResponseBase.ok("Grupos del turno " + turno.name() + " obtenidos", grupos));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseBase.error(e.getMessage()));
        }
    }

    @Operation(
        summary = "Grupos disponibles por turno",
        description = "Combina filtros: proceso académico + turno + solo con cupos disponibles",
        tags = {"3. Consultas"}
    )
    @GetMapping("/grupos/disponibles/turno")
    public ResponseEntity<ResponseBase<List<GrupoResponse>>> listarGruposDisponiblesPorTurno(
            @Parameter(description = "Proceso académico", required = true, example = "2026-I")
            @RequestParam String procesoAcademico,
            @Parameter(description = "Turno", required = true, 
                      schema = @Schema(allowableValues = {"MAÑANA", "TARDE"}))
            @RequestParam Turno turno) {
        try {
            List<GrupoResponse> grupos = matriculaGrupoService.listarGruposDisponiblesPorTurno(procesoAcademico, turno);
            return ResponseEntity.ok(ResponseBase.ok("Grupos disponibles del turno " + turno.name(), grupos));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseBase.error(e.getMessage()));
        }
    }
    
    // ==================== ENDPOINTS DE ASIGNACIONES ====================
    
    @Operation(
        summary = "Asignar estudiante a grupo",
        description = "Asigna un estudiante matriculado a un grupo. Valida capacidad y que no tenga asignación previa",
        tags = {"2. Asignación de Estudiantes"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = " Estudiante asignado exitosamente"),
        @ApiResponse(responseCode = "400", description = " Grupo lleno / Estudiante ya tiene grupo")
    })
    @PostMapping("/asignar")
    public ResponseEntity<ResponseBase<MatriculaGrupoResponse>> asignarGrupo(
            @RequestBody MatriculaGrupoRequest request) {
        try {
            MatriculaGrupoResponse response = matriculaGrupoService.asignarGrupo(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseBase.ok("Grupo asignado exitosamente", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseBase.error(e.getMessage()));
        }
    }
    
    @Operation(
        summary = "Reasignar estudiante a otro grupo",
        description = "Cambia al estudiante de un grupo a otro. La asignación anterior queda marcada como REASIGNADO",
        tags = {"2. Asignación de Estudiantes"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = " Estudiante reasignado exitosamente"),
        @ApiResponse(responseCode = "400", description = " Nuevo grupo lleno / Ya está en ese grupo"),
        @ApiResponse(responseCode = "404", description = " Asignación no encontrada")
    })
    @PutMapping("/reasignar/{matriculaGrupoId}")
    public ResponseEntity<ResponseBase<MatriculaGrupoResponse>> reasignarGrupo(
            @Parameter(description = "ID de la asignación actual", required = true, example = "1")
            @PathVariable Long matriculaGrupoId,
            @Parameter(description = "ID del nuevo grupo", required = true, example = "2")
            @RequestParam Long nuevoGrupoId) {
        try {
            MatriculaGrupoResponse response = matriculaGrupoService.reasignarGrupo(matriculaGrupoId, nuevoGrupoId);
            return ResponseEntity.ok(ResponseBase.ok("Grupo reasignado exitosamente", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseBase.error(e.getMessage()));
        }
    }
    
    @Operation(
        summary = "Desasignar estudiante de grupo",
        description = "Elimina la asignación activa del estudiante, liberando el cupo",
        tags = {"2. Asignación de Estudiantes"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudiante desasignado exitosamente"),
        @ApiResponse(responseCode = "404", description = " Asignación no encontrada"),
        @ApiResponse(responseCode = "400", description = " La asignación no está activa")
    })
    @DeleteMapping("/desasignar/{matriculaGrupoId}")
    public ResponseEntity<ResponseBase<Void>> desasignarGrupo(
            @Parameter(description = "ID de la asignación", required = true, example = "1")
            @PathVariable Long matriculaGrupoId) {
        try {
            matriculaGrupoService.desasignarGrupo(matriculaGrupoId);
            return ResponseEntity.ok(ResponseBase.ok("Grupo desasignado exitosamente", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ResponseBase.error(e.getMessage()));
        }
    }
    
    @Operation(
        summary = "Historial de asignaciones de estudiante",
        description = "Muestra todas las asignaciones (activas, reasignadas, inactivas) de un estudiante",
        tags = {"3. Consultas"}
    )
    @GetMapping("/matricula/{matriculaId}/historial")  
    public ResponseEntity<ResponseBase<List<MatriculaGrupoResponse>>> obtenerPorMatricula(
            @Parameter(description = "ID de matrícula del estudiante", required = true, example = "1")
            @PathVariable Integer matriculaId) {
        List<MatriculaGrupoResponse> asignaciones = matriculaGrupoService.obtenerPorMatricula(matriculaId);
        return ResponseEntity.ok(ResponseBase.ok("Asignaciones obtenidas", asignaciones));
    }
    
    @Operation(
        summary = "Lista de estudiantes de un grupo",
        description = "Obtiene todos los estudiantes actualmente asignados a un grupo",
        tags = {"3. Consultas"}
    )
    @GetMapping("/grupo/{grupoId}/estudiantes")
    public ResponseEntity<ResponseBase<List<MatriculaGrupoResponse>>> obtenerEstudiantesPorGrupo(
            @Parameter(description = "ID del grupo", required = true, example = "1")
            @PathVariable Long grupoId) {
        List<MatriculaGrupoResponse> estudiantes = matriculaGrupoService.obtenerEstudiantesPorGrupo(grupoId);
        return ResponseEntity.ok(ResponseBase.ok("Estudiantes obtenidos", estudiantes));
    }
    
    @Operation(
        summary = "Asignación activa de estudiante",
        description = "Consulta el grupo actual al que está asignado un estudiante",
        tags = {"3. Consultas"}
    )
    @GetMapping("/matricula/{matriculaId}/activa")
    public ResponseEntity<ResponseBase<MatriculaGrupoResponse>> obtenerAsignacionActiva(
            @Parameter(description = "ID de matrícula", required = true, example = "1")
            @PathVariable Integer matriculaId) {
        MatriculaGrupoResponse response = matriculaGrupoService.obtenerAsignacionActiva(matriculaId);
        if (response != null) {
            return ResponseEntity.ok(ResponseBase.ok("Asignación activa encontrada", response));
        } else {
            return ResponseEntity.ok(ResponseBase.ok("No tiene asignación activa", null));
        }
    }
}