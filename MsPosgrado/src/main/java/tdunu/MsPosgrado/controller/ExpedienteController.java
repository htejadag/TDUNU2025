package tdunu.MsPosgrado.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import tdunu.MsPosgrado.model.ExpedienteModel;
import tdunu.MsPosgrado.service.ExpedienteService;
import tdunu.MsPosgrado.util.ApiRoutes;

@RestController
@RequestMapping(ApiRoutes.Expediente.BASE)
@Tag(name = "Expediente", description = "GESTIÓN DE EXPEDIENTES")
public class ExpedienteController {

    @Autowired
    private ExpedienteService expedienteService;

    // 1. CREAR EXPEDIENTE
    @Operation(
        summary = "Registra un nuevo expediente",
        description = "Crea un nuevo registro para un expediente académico o administrativo.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Expediente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
        }
    )
    @PostMapping
    public ResponseEntity<ExpedienteModel> guardar(@RequestBody ExpedienteModel expedienteModel) {
        ExpedienteModel nuevo = expedienteService.guardar(expedienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // 2. LISTAR TODOS
    @Operation(
        summary = "Lista todos los expedientes",
        description = "Obtiene todos los registros de expedientes activos e inactivos.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
        }
    )
    @GetMapping
    public ResponseEntity<List<ExpedienteModel>> listarTodos() {
        return ResponseEntity.ok(expedienteService.listar());
    }

    // 3. OBTENER POR ID
    @Operation(
        summary = "Obtiene un expediente por ID",
        description = "Busca un expediente por ID y retorna toda su información almacenada.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Expediente encontrado"),
            @ApiResponse(responseCode = "404", description = "Expediente no encontrado")
        }
    )
    @GetMapping(ApiRoutes.Expediente.OBTENER_POR_ID)
    public ResponseEntity<ExpedienteModel> obtenerPorId(@PathVariable Integer id) {
        ExpedienteModel expediente = expedienteService.obtenerPorId(id);

        if (expediente != null) {
            return ResponseEntity.ok(expediente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. ACTUALIZAR EXPEDIENTE
    @Operation(
        summary = "Actualiza los datos de un expediente",
        description = "Permite modificar información del expediente, como estado, fecha, documentos, etc.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Expediente actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Expediente no encontrado")
        }
    )
    @PutMapping(ApiRoutes.Expediente.ACTUALIZAR)
    public ResponseEntity<ExpedienteModel> actualizar(@PathVariable Integer id, @RequestBody ExpedienteModel expedienteModel) {

        ExpedienteModel existente = expedienteService.obtenerPorId(id);

        if (existente != null) {
            expedienteModel.setIdExpediente(id);
            return ResponseEntity.ok(expedienteService.guardar(expedienteModel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. ELIMINAR EXPEDIENTE (ELIMINACIÓN LÓGICA)
    @Operation(
        summary = "Elimina lógicamente un expediente",
        description = "Marca el registro como inactivo en lugar de eliminarlo físicamente.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Expediente eliminado"),
            @ApiResponse(responseCode = "404", description = "Expediente no encontrado")
        }
    )
    @DeleteMapping(ApiRoutes.Expediente.ELIMINAR)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ExpedienteModel existente = expedienteService.obtenerPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        expedienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
