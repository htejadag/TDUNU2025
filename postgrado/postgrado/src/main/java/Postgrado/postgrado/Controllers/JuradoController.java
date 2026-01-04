package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Jurado;
import Postgrado.postgrado.Service.JuradoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jurados")
@Tag(name = "Jurados", description = "CRUD de jurados")
public class JuradoController {

    private final JuradoService service;

    public JuradoController(JuradoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Crear jurado", description = "Crea un nuevo jurado")
    public Jurado crear(@Valid @RequestBody Jurado jurado) {
        return service.crear(jurado);
    }

    @GetMapping
    @Operation(summary = "Listar jurados", description = "Devuelve todos los jurados activos")
    public List<Jurado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener jurado por ID")
    public Jurado obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar jurado")
    public Jurado actualizar(@PathVariable Integer id, @RequestBody Jurado data) {
        return service.actualizar(id, data);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar jurado")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
