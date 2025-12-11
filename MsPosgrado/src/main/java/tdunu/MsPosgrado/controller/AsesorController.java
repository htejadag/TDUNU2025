package tdunu.MsPosgrado.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import tdunu.MsPosgrado.model.AsesorModel;
import tdunu.MsPosgrado.service.AsesorService;
import tdunu.MsPosgrado.util.ApiRoutes;

@RestController
@RequestMapping(ApiRoutes.Asesor.BASE)
@Tag(name = "Asesor", description = "GESTION DE ASESORES")
public class AsesorController {

    @Autowired
    private AsesorService asesorService;

    // 1. REGISTRAR ASESOR (CREAR)
    // POST /api/msposgrado/asesor
    // Utilizado para registrar la información base del profesional
    // Requisitos
    @Operation(summary = "Registra un nuevo profesional como Asesor", description = "Crea un nuevo registro de asesor con su CV, grado y declaración jurada, cumpliendo los requisitos iniciales de la Etapa A.", responses = {
            @ApiResponse(responseCode = "201", description = "Asesor creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<AsesorModel> guardar(@RequestBody AsesorModel asesorModel) {
        AsesorModel nuevoAsesor = asesorService.guardar(asesorModel);
        // Usar 201 Created para una nueva creación
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAsesor);
    }

    // 2. OBTENER TODOS
    // GET /api/msposgrado/asesor
    @Operation(summary = "Lista todos los asesores registrados", description = "Retorna una lista completa de todos los profesionales disponibles para la designación.", responses = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    })
    @GetMapping
    public ResponseEntity<List<AsesorModel>> listarTodos() {
        return ResponseEntity.ok(asesorService.listar());
    }

    // 3. OBTENER POR ID
    // GET /api/msposgrado/asesor/{id}
    @Operation(summary = "Busca un asesor por ID", description = "Obtiene los detalles completos del asesor, incluyendo los atributos de control y la fecha de su resolución.", responses = {
            @ApiResponse(responseCode = "200", description = "Asesor encontrado"),
            @ApiResponse(responseCode = "404", description = "Asesor no encontrado con el ID proporcionado")
    })
    @GetMapping(ApiRoutes.Asesor.OBTENER_POR_ID)
    public ResponseEntity<AsesorModel> obtenerPorId(@PathVariable("id") Integer id) {
        AsesorModel asesor = asesorService.obtenerPorId(id);

        if (asesor != null) {
            return ResponseEntity.ok(asesor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. ACTUALIZAR ASESOR (Ej. actualizar estado, grado)
    // PUT /api/msposgrado/asesor/{id}
    @Operation(summary = "Actualiza la información de un asesor existente", description = "Permite modificar datos del asesor. NOTA: Utilizado para actualizar el estado de actividad o reasignar un grado.", responses = {
            @ApiResponse(responseCode = "200", description = "Asesor actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Asesor no encontrado")
    })
    @PutMapping(ApiRoutes.Asesor.ACTUALIZAR)
    public ResponseEntity<AsesorModel> actualizar(@PathVariable Integer id, @RequestBody AsesorModel asesorModel) {
        // 1. Intentamos obtener el asesor existente para la actualización
        AsesorModel existente = asesorService.obtenerPorId(id);

        if (existente != null) {
            // 2. Establecer el ID del Path en el modelo para el servicio
            asesorModel.setIdAsesor(id);
            // 3. Guardar actualizará si el ID existe
            return ResponseEntity.ok(asesorService.guardar(asesorModel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. ELIMINAR
    // DELETE /api/msposgrado/asesor/{id}
    @Operation(summary = "Elimina un asesor por ID", description = "Realiza la eliminación lógica del asesor. Se recomienda internamente cambiar el 'estado_asesor' a inactivo (0).", responses = {
            @ApiResponse(responseCode = "204", description = "Asesor eliminado/desactivado con éxito"),
            @ApiResponse(responseCode = "404", description = "Asesor no encontrado")
    })
    @DeleteMapping(ApiRoutes.Asesor.ELIMINAR)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        AsesorModel existente = asesorService.obtenerPorId(id);
        if (existente == null) {
        return ResponseEntity.notFound().build();
        }
    asesorService.eliminar(id);
    return ResponseEntity.noContent().build();

    }
}