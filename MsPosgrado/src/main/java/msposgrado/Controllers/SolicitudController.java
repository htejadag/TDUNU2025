package msposgrado.Controllers;

import msposgrado.Model.Solicitud;
import msposgrado.Service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import msposgrado.Constantes.Routes;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(Routes.SOLICITUDES)
public class SolicitudController {

    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Solicitud> crear(@Valid @RequestBody Solicitud solicitud) {
        Solicitud nuevaSolicitud = service.crear(solicitud);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Solicitud>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> obtener(@PathVariable Integer id) {
        Solicitud solicitud = service.obtenerPorId(id);
        if (solicitud == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(solicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> actualizar(@PathVariable Integer id, @Valid @RequestBody Solicitud data) {
        Solicitud s = service.obtenerPorId(id);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }

        s.setTipoSolicitud(data.getTipoSolicitud());
        s.setDescripcion(data.getDescripcion());
        s.setEstadoSolicitud(data.getEstadoSolicitud());

        Solicitud actualizada = service.crear(s);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Solicitud s = service.obtenerPorId(id);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
