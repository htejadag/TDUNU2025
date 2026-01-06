package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Solicitud;
import Postgrado.postgrado.Service.SolicitudService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {
private static final Logger log = LoggerFactory.getLogger(SolicitudController.class);
    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

     @PostMapping
    public ResponseEntity<?> crear(@RequestBody Solicitud solicitud) {
        log.info("Creando solicitud {}", solicitud.getDescripcion());
        try {
            return ResponseEntity.ok(service.crear(solicitud));
        } catch (Exception e) {
            log.error("Error creando solicitud: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Error interno");
        }
    }

    @GetMapping
    public ResponseEntity<List<Solicitud>> listar() {
        log.info("Listando solicitudes");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        log.info("Buscando solicitud {}", id);

        Solicitud s = service.obtenerPorId(id);
        if (s == null) {
            log.warn("Solicitud no encontrada {}", id);
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(s);
    }

    @PutMapping("/{id}")
    public Solicitud actualizar(@PathVariable Integer id, @RequestBody Solicitud data) {
         log.info("Actualizando solicitud {}", id);
        Solicitud s = service.obtenerPorId(id);
        if (s == null) {
            log.warn("Solicitud {} no existe", id);
            return ResponseEntity.notFound().build();
        }
        s.setTipoSolicitud(data.getTipoSolicitud());
        s.setDescripcion(data.getDescripcion());
        s.setFechaSolicitud(data.getFechaSolicitud());
        s.setEstadoSolicitud(data.getEstadoSolicitud());

        return service.crear(s);
        return ResponseEntity.ok(s);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
