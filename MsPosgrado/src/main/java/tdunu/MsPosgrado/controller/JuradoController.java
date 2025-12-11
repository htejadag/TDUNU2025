package tdunu.MsPosgrado.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import tdunu.MsPosgrado.model.JuradoModel;
import tdunu.MsPosgrado.service.JuradoService;
import tdunu.MsPosgrado.util.ApiRoutes;

@RestController
@RequestMapping(ApiRoutes.Jurado.BASE)
@Tag(name = "Jurado", description = "GESTIÓN DE JURADOS")
public class JuradoController {

    @Autowired
    private JuradoService juradoService;

    // 1. CREAR JURADO
    @Operation(
        summary = "Registra un nuevo jurado",
        description = "Crea un nuevo registro para un miembro del jurado, incluyendo su especialidad y resolución de designación.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Jurado creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
        }
    )
    @PostMapping
    public ResponseEntity<JuradoModel> guardar(@RequestBody JuradoModel juradoModel) {
        JuradoModel nuevo = juradoService.guardar(juradoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // 2. LISTAR TODOS
    @Operation(
        summary = "Lista todos los jurados",
        description = "Obtiene todos los registros de jurados activos e inactivos.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
        }
    )
    @GetMapping
    public ResponseEntity<List<JuradoModel>> listarTodos() {
        return ResponseEntity.ok(juradoService.listar());
    }

    // 3. OBTENER POR ID
    @Operation(
        summary = "Obtiene un jurado por ID",
        description = "Busca un jurado por ID y retorna toda su información almacenada.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Jurado encontrado"),
            @ApiResponse(responseCode = "404", description = "Jurado no encontrado")
        }
    )
    @GetMapping(ApiRoutes.Jurado.OBTENER_POR_ID)
    public ResponseEntity<JuradoModel> obtenerPorId(@PathVariable Integer id) {
        JuradoModel jurado = juradoService.obtenerPorId(id);

        if (jurado != null) {
            return ResponseEntity.ok(jurado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. ACTUALIZAR JURADO
    @Operation(
        summary = "Actualiza los datos de un jurado",
        description = "Permite modificar nombre, apellidos, especialidad, resolución u otros campos del jurado.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Jurado actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Jurado no encontrado")
        }
    )
    @PutMapping(ApiRoutes.Jurado.ACTUALIZAR)
    public ResponseEntity<JuradoModel> actualizar(@PathVariable Integer id, @RequestBody JuradoModel juradoModel) {

        JuradoModel existente = juradoService.obtenerPorId(id);

        if (existente != null) {
            juradoModel.setIdJurado(id);
            return ResponseEntity.ok(juradoService.guardar(juradoModel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. ELIMINAR JURADO (ELIMINACIÓN LÓGICA)
    @Operation(
        summary = "Elimina lógicamente un jurado",
        description = "Marca el registro como inactivo en lugar de eliminarlo físicamente.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Jurado eliminado"),
            @ApiResponse(responseCode = "404", description = "Jurado no encontrado")
        }
    )
    @DeleteMapping(ApiRoutes.Jurado.ELIMINAR)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        JuradoModel existente = juradoService.obtenerPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        juradoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
