package msposgrado.Controllers;

import msposgrado.Constantes.Routes;

import msposgrado.Model.Documento;
import msposgrado.Service.DocumentoService;
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
@RequestMapping(Routes.DOCUMENTOS)
@Tag(name = "Documentos", description = "Controlador para la gestión de documentos académicos y tesis")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    /**
     * Registra un nuevo documento en el sistema.
     * 
     * @param documento Objeto con los metadatos del documento.
     * @return El documento creado con status 201.
     */
    @PostMapping
    @Operation(summary = "Crear documento", description = "Registra un nuevo documento asociado a una tesis o proyecto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Documento creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Documento.class))),
            @ApiResponse(responseCode = "400", description = "Datos del documento inválidos", content = @Content)
    })
    public ResponseEntity<Documento> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo documento", required = true) @Valid @RequestBody Documento documento) {
        Documento nuevoDocumento = service.crear(documento);
        return new ResponseEntity<>(nuevoDocumento, HttpStatus.CREATED);
    }

    /**
     * Obtiene el listado completo de documentos.
     * 
     * @return Lista de documentos con status 200.
     */
    @GetMapping
    @Operation(summary = "Listar documentos", description = "Recupera la lista de todos los documentos registrados.")
    @ApiResponse(responseCode = "200", description = "Lista recuperada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Documento.class)))
    public ResponseEntity<List<Documento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Busca un documento por su identificador.
     * 
     * @param id ID del documento.
     * @return El documento encontrado (200) o Not Found (404).
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener documento por ID", description = "Busca un documento específico por su ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documento encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Documento.class))),
            @ApiResponse(responseCode = "404", description = "Documento no encontrado", content = @Content)
    })
    public ResponseEntity<Documento> obtener(
            @Parameter(description = "ID del documento a buscar", required = true) @PathVariable Integer id) {
        Documento documento = service.obtenerPorId(id);
        if (documento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(documento);
    }

    /**
     * Actualiza la información de un documento existente.
     * 
     * @param id   ID del documento a modificar.
     * @param data Nuevos datos (Tipo y Ruta).
     * @return El documento actualizado (200) o Not Found (404).
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar documento", description = "Actualiza el tipo y la ruta de archivo de un documento existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documento actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Documento.class))),
            @ApiResponse(responseCode = "404", description = "Documento no encontrado para actualizar", content = @Content)
    })
    public ResponseEntity<Documento> actualizar(
            @Parameter(description = "ID del documento a actualizar", required = true) @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Nuevos datos del documento", required = true) @Valid @RequestBody Documento data) {

        Documento d = service.obtenerPorId(id);
        if (d == null) {
            return ResponseEntity.notFound().build();
        }

        d.setTipoDocumento(data.getTipoDocumento());
        d.setArchivoRuta(data.getArchivoRuta());
        // Fecha y auditoría se mantienen

        // Nota: Asumiendo que service.crear funciona como un save/update
        Documento actualizado = service.crear(d);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Elimina un documento del sistema.
     * 
     * @param id ID del documento.
     * @return No Content (204) si se elimina, o Not Found (404).
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar documento", description = "Elimina permanentemente un documento del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Documento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Documento no encontrado para eliminar")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del documento a eliminar", required = true) @PathVariable Integer id) {
        Documento d = service.obtenerPorId(id);
        if (d == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
