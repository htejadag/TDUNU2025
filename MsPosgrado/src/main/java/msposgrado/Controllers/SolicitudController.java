package msposgrado.Controllers;

import msposgrado.Model.Solicitud;
import msposgrado.Service.SolicitudService;
import msposgrado.Constantes.Routes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.SOLICITUDES)
@Tag(
    name = "Solicitudes",
    description = "CRUD de solicitudes asociadas a expedientes"
)
public class SolicitudController {

    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Crear solicitud",
        description = "Registra una nueva solicitud dentro del sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Solicitud creada correctamente",
            content = @Content(schema = @Schema(implementation = Solicitud.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos enviados",
            content = @Content
        )
    })
    public ResponseEntity<Solicitud> crear(
            @Parameter(description = "Objeto solicitud a registrar")
            @Valid @RequestBody Solicitud solicitud) {

        Solicitud nuevaSolicitud = service.crear(solicitud);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
        summary = "Listar solicitudes",
        description = "Devuelve la lista de todas las solicitudes registradas"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de solicitudes obtenida correctamente",
            content = @Content(schema = @Schema(implementation = Solicitud.class))
        )
    })
    public ResponseEntity<List<Solicitud>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener solicitud por ID",
        description = "Obtiene una solicitud específica según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Solicitud encontrada",
            content = @Content(schema = @Schema(implementation = Solicitud.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Solicitud no encontrada",
            content = @Content
        )
    })
    public ResponseEntity<Solicitud> obtener(
            @Parameter(description = "ID de la solicitud", example = "1")
            @PathVariable Integer id) {

        Solicitud solicitud = service.obtenerPorId(id);
        if (solicitud == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(solicitud);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar solicitud",
        description = "Actualiza los datos de una solicitud existente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Solicitud actualizada correctamente",
            content = @Content(schema = @Schema(implementation = Solicitud.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Solicitud no encontrada",
            content = @Content
        )
    })
    public ResponseEntity<Solicitud> actualizar(
            @Parameter(description = "ID de la solicitud", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados de la solicitud")
            @Valid @RequestBody Solicitud data) {

        Solicitud s = service.obtenerPorId(id);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }

        s.setTipoSolicitud(data.getTipoSolicitud());
        s.setDescripcion(data.getDescripcion());
        s.setEstadoSolicitud(data.getEstadoSolicitud());

        Solicitud actualizada = service.crear(s);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar solicitud",
        description = "Elimina una solicitud según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Solicitud eliminada correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Solicitud no encontrada",
            content = @Content
        )
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la solicitud", example = "1")
            @PathVariable Integer id) {

        Solicitud s = service.obtenerPorId(id);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}