package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Asesor;
import Postgrado.postgrado.Service.AsesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asesores")
@Tag(name = "Asesores", description = "CRUD de asesores de tesis")
public class AsesorController {

    private final AsesorService service;

    public AsesorController(AsesorService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar asesores", description = "Devuelve todos los asesores registrados")
    public List<Asesor> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener asesor por ID")
    public Asesor obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear asesor", description = "Crea un nuevo asesor")
    public Asesor crear(@Valid @RequestBody Asesor asesor) {
        return service.crear(asesor);
    }

    @PutMapping("/{id}")
    public Asesor actualizar(@PathVariable Integer id, @RequestBody Asesor data) {
        return service.actualizar(id, data);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar asesor")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
