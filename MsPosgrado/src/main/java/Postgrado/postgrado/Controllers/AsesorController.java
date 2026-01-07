package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Asesor;
import Postgrado.postgrado.Service.AsesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/asesores")
@Tag(name = "Asesores", description = "Controlador para la gestión y mantenimiento de asesores de tesis")
public class AsesorController {

    private final AsesorService service;

    public AsesorController(AsesorService service) {
        this.service = service;
    }

    /**
     * Obtiene la lista completa de asesores registrados.
     * @return Lista de objetos Asesor.
     */
    @GetMapping
    @Operation(summary = "Listar asesores", description = "Devuelve una lista completa de todos los asesores registrados en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asesor.class)))
    public List<Asesor> listar() {
        return service.listar();
    }

    /**
     * Busca un asesor específico por su ID.
     * @param id Identificador único del asesor.
     * @return El objeto Asesor si es encontrado.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener asesor por ID", description = "Busca y devuelve los detalles de un asesor específico basado en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Asesor encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asesor.class))),
        @ApiResponse(responseCode = "404", description = "Asesor no encontrado", content = @Content)
    })
    public Asesor obtenerPorId(@Parameter(description = "ID del asesor a buscar", required = true) @PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    /**
     * Crea un nuevo registro de asesor.
     * @param asesor Objeto Asesor con los datos a persistir.
     * @return El asesor creado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Indica que el status correcto es 201 Created
    @Operation(summary = "Crear asesor", description = "Crea un nuevo registro de asesor en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Asesor creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asesor.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
    })
    public Asesor crear(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo asesor", required = true) @Valid @RequestBody Asesor asesor) {
        return service.crear(asesor);
    }

    /**
     * Actualiza los datos de un asesor existente.
     * @param id ID del asesor a actualizar.
     * @param data Nuevos datos del asesor.
     * @return El asesor actualizado.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar asesor", description = "Actualiza la información de un asesor existente identificado por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Asesor actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asesor.class))),
        @ApiResponse(responseCode = "404", description = "Asesor no encontrado", content = @Content)
    })
    public Asesor actualizar(
            @Parameter(description = "ID del asesor a actualizar", required = true) @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Nuevos datos del asesor", required = true) @RequestBody Asesor data) {
        return service.actualizar(id, data);
    }

    /**
     * Elimina un asesor del sistema.
     * @param id ID del asesor a eliminar.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que el status correcto es 204 No Content
    @Operation(summary = "Eliminar asesor", description = "Elimina permanentemente un registro de asesor por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Asesor eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Asesor no encontrado")
    })
    public void eliminar(@Parameter(description = "ID del asesor a eliminar", required = true) @PathVariable Integer id) {
        service.eliminar(id);
    }
}