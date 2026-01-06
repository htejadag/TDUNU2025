package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Expediente;
import Postgrado.postgrado.Service.ExpedienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expedientes")
public class ExpedienteController {

    private static final Logger log = LoggerFactory.getLogger(ExpedienteController.class);

    private final ExpedienteService service;

    public ExpedienteController(ExpedienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Expediente expediente) {
        log.info("Creando expediente para estudiante {}", expediente.getIdEstudiante());

        try {
            Expediente nuevo = service.crear(expediente);
            log.info("Expediente creado con ID {}", nuevo.getIdExpediente());
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            log.error("Error creando expediente: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Expediente>> listar() {
        log.info("Listando expedientes");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        log.info("Buscando expediente {}", id);

        Expediente exp = service.obtenerPorId(id);
        if (exp == null) {
            log.warn("Expediente {} no encontrado", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(exp);
    }
}
