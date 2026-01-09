package msposgrado.Controllers;

import msposgrado.Model.Jurado;
import msposgrado.Service.JuradoService;
import msposgrado.Constantes.Routes;
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

@RestController
@RequestMapping(Routes.JURADOS)
@Tag(
    name = "Jurados",
    description = "Controlador para la gestión de jurados (crear, listar, obtener, actualizar y eliminar)"
)
public class JuradoController {

    private final JuradoService service;

    public JuradoController(JuradoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Crear jurado",
        description = "Crea un nuevo jurado en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Jurado creado correctamente",
            content = @Content(schema = @Schema(implementation = Jurado.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos enviados",
            content = @Content
        )
    })
    public Jurado crear(
            @Parameter(description = "Objeto jurado a registrar")
            @Valid @RequestBody Jurado jurado) {
        return service.crear(jurado);
    }

    @GetMapping
    @Operation(
        summary = "Listar jurados",
        description = "Devuelve la lista de todos los jurados activos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de jurados obtenida correctamente",
            content = @Content(schema = @Schema(implementation = Jurado.class))
        )
    })
    public List<Jurado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener jurado por ID",
        description = "Obtiene la información de un jurado según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Jurado encontrado",
            content = @Content(schema = @Schema(implementation = Jurado.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Jurado no encontrado",
            content = @Content
        )
    })
    public Jurado obtener(
            @Parameter(description = "ID del jurado", example = "1")
            @PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar jurado",
        description = "Actualiza la información de un jurado existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Jurado actualizado correctamente",
            content = @Content(schema = @Schema(implementation = Jurado.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Jurado no encontrado",
            content = @Content
        )
    })
    public Jurado actualizar(
            @Parameter(description = "ID del jurado", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del jurado")
            @RequestBody Jurado data) {
        return service.actualizar(id, data);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar jurado",
        description = "Elimina un jurado según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Jurado eliminado correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Jurado no encontrado",
            content = @Content
        )
    })
    public void eliminar(
            @Parameter(description = "ID del jurado", example = "1")
            @PathVariable Integer id) {
        service.eliminar(id);
    }
}