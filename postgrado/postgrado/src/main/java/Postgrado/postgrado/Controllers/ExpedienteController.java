package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Expediente;
import Postgrado.postgrado.Service.ExpedienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expedientes")
@Tag(name = "Expedientes", description = "CRUD de expedientes")
public class ExpedienteController {

    private final ExpedienteService service;

    public ExpedienteController(ExpedienteService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Crear expediente", description = "Crea un nuevo expediente")
    public Expediente crear(@Valid @RequestBody Expediente expediente) {
        return service.crear(expediente);
    }

    @GetMapping
    @Operation(summary = "Listar expedientes", description = "Devuelve todos los expedientes activos")
    public List<Expediente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener expediente por ID")
    public Expediente obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar expediente")
    public Expediente actualizar(@PathVariable Integer id, @RequestBody Expediente data) {
        return service.actualizar(id, data);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar expediente")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
