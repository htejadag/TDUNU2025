package msposgrado.Controllers;

import msposgrado.Model.EstadoSolicitud;
import msposgrado.Service.EstadoSolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estados-solicitud")
public class EstadoSolicitudController {

    private final EstadoSolicitudService service;

    public EstadoSolicitudController(EstadoSolicitudService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EstadoSolicitud>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoSolicitud> obtenerPorId(
            @org.springframework.web.bind.annotation.PathVariable("id") Integer id) {
        EstadoSolicitud estado = service.obtenerPorId(id);
        if (estado != null) {
            return ResponseEntity.ok(estado);
        }
        return ResponseEntity.notFound().build();
    }
}
