package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Asesor;
import Postgrado.postgrado.Service.AsesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asesores")
@Tag(name = "Asesores", description = "CRUD de asesores de tesis")
public class AsesorController {

    private final AsesorService service;

    public AsesorController(AsesorService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Crear asesor", description = "Crea un nuevo asesor")
    public Asesor crear(@RequestBody Asesor asesor) {
        return service.crear(asesor);
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

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar asesor")
    public Asesor actualizar(@PathVariable Integer id, @RequestBody Asesor data) {
        Asesor a = service.obtenerPorId(id);
        if (a == null) return null;

        a.setNombres(data.getNombres());
        a.setApellidos(data.getApellidos());
        a.setGradoMaximo(data.getGradoMaximo());
        a.setCvRuta(data.getCvRuta());
        a.setDeclaracionRuta(data.getDeclaracionRuta());
        a.setEstadoAsesor(data.getEstadoAsesor());

        return service.crear(a);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar asesor")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
