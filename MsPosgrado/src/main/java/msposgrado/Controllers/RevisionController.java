package msposgrado.Controllers;

import msposgrado.Model.Revision;
import msposgrado.Service.RevisionService;
import msposgrado.Constantes.Routes;
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
@RequestMapping(Routes.REVISIONES)
@Tag(
    name = "Revisiones",
    description = "Controlador para la gestión de revisiones de tesis realizadas por jurados"
)
public class RevisionController {

    private final RevisionService service;

    public RevisionController(RevisionService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Crear revisión",
        description = "Registra una nueva revisión de tesis"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Revisión creada correctamente",
            content = @Content(schema = @Schema(implementation = Revision.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos enviados",
            content = @Content
        )
    })
    public Revision crear(
            @Parameter(description = "Objeto revisión a registrar")
            @RequestBody Revision revision) {
        return service.crear(revision);
    }

    @GetMapping
    @Operation(
        summary = "Listar revisiones",
        description = "Devuelve la lista de todas las revisiones registradas"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de revisiones obtenida correctamente",
            content = @Content(schema = @Schema(implementation = Revision.class))
        )
    })
    public List<Revision> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener revisión por ID",
        description = "Obtiene una revisión específica según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Revisión encontrada",
            content = @Content(schema = @Schema(implementation = Revision.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Revisión no encontrada",
            content = @Content
        )
    })
    public Revision obtener(
            @Parameter(description = "ID de la revisión", example = "1")
            @PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar revisión",
        description = "Actualiza los datos de una revisión existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Revisión actualizada correctamente",
            content = @Content(schema = @Schema(implementation = Revision.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Revisión no encontrada",
            content = @Content
        )
    })
    public Revision actualizar(
            @Parameter(description = "ID de la revisión", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados de la revisión")
            @RequestBody Revision data) {

        Revision r = service.obtenerPorId(id);
        if (r == null)
            return null;

        r.setTesis(data.getTesis());
        r.setJurado(data.getJurado());
        r.setTipoRevision(data.getTipoRevision());
        r.setComentario(data.getComentario());
        r.setDictamen(data.getDictamen());
        r.setFechaRevision(data.getFechaRevision());

        return service.crear(r);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar revisión",
        description = "Elimina una revisión según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Revisión eliminada correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Revisión no encontrada",
            content = @Content
        )
    })
    public void eliminar(
            @Parameter(description = "ID de la revisión", example = "1")
            @PathVariable Integer id) {
        service.eliminar(id);
    }
}