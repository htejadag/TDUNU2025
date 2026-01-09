package msposgrado.Controllers;

import msposgrado.Model.Tesis;
import msposgrado.Service.TesisService;
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
@RequestMapping(Routes.TESIS)
@Tag(
    name = "Tesis",
    description = "CRUD de tesis de posgrado"
)
public class TesisController {

    private final TesisService service;

    public TesisController(TesisService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Crear tesis",
        description = "Registra una nueva tesis en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tesis creada correctamente",
            content = @Content(schema = @Schema(implementation = Tesis.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos enviados",
            content = @Content
        )
    })
    public Tesis crear(
            @Parameter(description = "Objeto tesis a registrar")
            @RequestBody Tesis tesis) {

        return service.crear(tesis);
    }

    @GetMapping
    @Operation(
        summary = "Listar tesis",
        description = "Devuelve la lista de todas las tesis registradas"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de tesis obtenida correctamente",
            content = @Content(schema = @Schema(implementation = Tesis.class))
        )
    })
    public List<Tesis> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener tesis por ID",
        description = "Obtiene una tesis específica según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tesis encontrada",
            content = @Content(schema = @Schema(implementation = Tesis.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tesis no encontrada",
            content = @Content
        )
    })
    public Tesis obtener(
            @Parameter(description = "ID de la tesis", example = "1")
            @PathVariable Integer id) {

        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar tesis",
        description = "Actualiza los datos de una tesis existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tesis actualizada correctamente",
            content = @Content(schema = @Schema(implementation = Tesis.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tesis no encontrada",
            content = @Content
        )
    })
    public Tesis actualizar(
            @Parameter(description = "ID de la tesis", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados de la tesis")
            @RequestBody Tesis data) {

        Tesis t = service.obtenerPorId(id);
        if (t == null)
            return null;

        t.setTitulo(data.getTitulo());
        t.setProyectoPdf(data.getProyectoPdf());
        t.setInformeFinalPdf(data.getInformeFinalPdf());
        t.setAntiplagioProyectoPdf(data.getAntiplagioProyectoPdf());
        t.setAntiplagioFinalPdf(data.getAntiplagioFinalPdf());
        t.setEstadoProyecto(data.getEstadoProyecto());
        t.setEstadoInformeFinal(data.getEstadoInformeFinal());
        t.setFechaRegistro(data.getFechaRegistro());

        return service.crear(t);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar tesis",
        description = "Elimina una tesis según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Tesis eliminada correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tesis no encontrada",
            content = @Content
        )
    })
    public void eliminar(
            @Parameter(description = "ID de la tesis", example = "1")
            @PathVariable Integer id) {

        service.eliminar(id);
    }
}