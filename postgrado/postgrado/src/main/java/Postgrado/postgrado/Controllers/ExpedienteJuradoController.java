package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.ExpedienteJurado;
import Postgrado.postgrado.Service.ExpedienteJuradoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expediente-jurado")
public class ExpedienteJuradoController {

    private final ExpedienteJuradoService service;

    public ExpedienteJuradoController(ExpedienteJuradoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExpedienteJurado> crear(@Valid @RequestBody ExpedienteJurado ej) {
        ExpedienteJurado nuevoEj = service.crear(ej);
        return new ResponseEntity<>(nuevoEj, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExpedienteJurado>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpedienteJurado> obtener(@PathVariable Integer id) {
        ExpedienteJurado ej = service.obtenerPorId(id);
        if (ej == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ej);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpedienteJurado> actualizar(@PathVariable Integer id,
            @Valid @RequestBody ExpedienteJurado data) {
        ExpedienteJurado ej = service.obtenerPorId(id);
        if (ej == null) {
            return ResponseEntity.notFound().build();
        }

        ej.setExpediente(data.getExpediente());
        ej.setJurado(data.getJurado());
        ej.setRol(data.getRol());
        // fechaDesignacion eliminada, se controla por auditoría

        ExpedienteJurado actualizado = service.crear(ej);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ExpedienteJurado ej = service.obtenerPorId(id);
        if (ej == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
