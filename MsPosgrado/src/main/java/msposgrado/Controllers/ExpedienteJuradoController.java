package msposgrado.Controllers;

import msposgrado.Constantes.Routes;
import msposgrado.Model.ExpedienteJurado;
import msposgrado.Service.ExpedienteJuradoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(Routes.EXPEDIENTE_JURADO)
@Tag(name = "Expediente Jurado", description = "Controlador para la gestión de jurados asignados a expedientes"
)
public class ExpedienteJuradoController {

    private final ExpedienteJuradoService service;

    public ExpedienteJuradoController(ExpedienteJuradoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Crear expediente jurado",
        description = "Registra la asignación de un jurado a un expediente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Expediente jurado creado correctamente",
            content = @Content(schema = @Schema(implementation = ExpedienteJurado.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos enviados",
            content = @Content
        )
    })
    public ExpedienteJurado crear(
            @Parameter(description = "Objeto ExpedienteJurado a registrar")
            @RequestBody ExpedienteJurado ej) {
        return service.crear(ej);
    }

    @GetMapping
    @Operation(
        summary = "Listar expedientes jurado",
        description = "Devuelve la lista de todas las asignaciones de jurados a expedientes"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de expedientes jurado obtenida correctamente",
            content = @Content(schema = @Schema(implementation = ExpedienteJurado.class))
        )
    })
    public List<ExpedienteJurado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener expediente jurado por ID",
        description = "Obtiene una asignación de jurado según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Expediente jurado encontrado",
            content = @Content(schema = @Schema(implementation = ExpedienteJurado.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Expediente jurado no encontrado",
            content = @Content
        )
    })
    public ExpedienteJurado obtener(
            @Parameter(description = "ID del expediente jurado", example = "1")
            @PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar expediente jurado",
        description = "Actualiza los datos de una asignación de jurado existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Expediente jurado actualizado correctamente",
            content = @Content(schema = @Schema(implementation = ExpedienteJurado.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Expediente jurado no encontrado",
            content = @Content
        )
    })
    public ExpedienteJurado actualizar(
            @Parameter(description = "ID del expediente jurado", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del expediente jurado")
            @RequestBody ExpedienteJurado data) {

        ExpedienteJurado ej = service.obtenerPorId(id);
        if (ej == null)
            return null;

        ej.setExpediente(data.getExpediente());
        ej.setJurado(data.getJurado());
        ej.setRol(data.getRol());
        ej.setFechaDesignacion(data.getFechaDesignacion());

        return service.crear(ej);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar expediente jurado",
        description = "Elimina una asignación de jurado según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Expediente jurado eliminado correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Expediente jurado no encontrado",
            content = @Content
        )
    })
    public void eliminar(
            @Parameter(description = "ID del expediente jurado", example = "1")
            @PathVariable Integer id) {
        service.eliminar(id);
    }
}