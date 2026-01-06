package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Asesor;
import Postgrado.postgrado.Service.AsesorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asesores")
public class AsesorController {

    private static final Logger log = LoggerFactory.getLogger(AsesorController.class);

    private final AsesorService service;

    public AsesorController(AsesorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Asesor> crear(@RequestBody Asesor asesor) {
        log.info("Creando asesor: {}", asesor.getNombres());

        try {
            Asesor nuevo = service.crear(asesor);
            log.info("Asesor creado con ID {}", nuevo.getIdAsesor());
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            log.error("Error al crear asesor: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Asesor>> listar() {
        log.info("Listando asesores");

        try {
            return ResponseEntity.ok(service.listar());
        } catch (Exception e) {
            log.error("Error listando asesores: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        log.info("Buscando asesor con ID {}", id);

        try {
            Asesor asesor = service.obtenerPorId(id);
            if (asesor == null) {
                log.warn("Asesor {} no encontrado", id);
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(asesor);

        } catch (Exception e) {
            log.error("Error buscando asesor {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        log.info("Eliminando asesor {}", id);

        try {
            service.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error eliminando asesor {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
