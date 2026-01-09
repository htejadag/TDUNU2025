package msposgrado.Controllers;

import msposgrado.Model.Expediente;
import msposgrado.Service.ExpedienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import msposgrado.Constantes.Routes;

@RestController
@RequestMapping(Routes.EXPEDIENTES)
@Tag(name = "Expedientes", description = "Controlador para la gestión de expedientes"
)
public class ExpedienteController {

    private final ExpedienteService service;

    public ExpedienteController(ExpedienteService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Crear expediente",
        description = "Permite registrar un nuevo expediente en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Expediente creado correctamente",
            content = @Content(schema = @Schema(implementation = Expediente.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos enviados",
            content = @Content
        )
    })
    public Expediente crear(
            @Valid
            @RequestBody
            @Parameter(description = "Objeto expediente a registrar")
            Expediente expediente) {
        return service.crear(expediente);
    }

    @GetMapping
    @Operation(
        summary = "Listar expedientes",
        description = "Devuelve la lista de todos los expedientes activos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de expedientes obtenida correctamente",
            content = @Content(schema = @Schema(implementation = Expediente.class))
        )
    })
    public List<Expediente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener expediente por ID",
        description = "Obtiene un expediente específico según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Expediente encontrado",
            content = @Content(schema = @Schema(implementation = Expediente.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Expediente no encontrado",
            content = @Content
        )
    })
    public Expediente obtener(
            @Parameter(description = "ID del expediente", example = "1")
            @PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar expediente",
        description = "Actualiza la información de un expediente existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Expediente actualizado correctamente",
            content = @Content(schema = @Schema(implementation = Expediente.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Expediente no encontrado",
            content = @Content
        )
    })
    public Expediente actualizar(
            @Parameter(description = "ID del expediente", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del expediente")
            @RequestBody Expediente data) {
        return service.actualizar(id, data);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar expediente",
        description = "Elimina un expediente según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Expediente eliminado correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Expediente no encontrado",
            content = @Content
        )
    })
    public void eliminar(
            @Parameter(description = "ID del expediente", example = "1")
            @PathVariable Integer id) {
        service.eliminar(id);
    }
}