package msposgrado.Controllers;

import msposgrado.Model.TipoSolicitud;
import msposgrado.Service.TipoSolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-solicitud")
public class TipoSolicitudController {

    private final TipoSolicitudService service;

    public TipoSolicitudController(TipoSolicitudService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoSolicitud>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSolicitud> obtenerPorId(
            @org.springframework.web.bind.annotation.PathVariable("id") Integer id) {
        TipoSolicitud tipo = service.obtenerPorId(id);
        if (tipo != null) {
            return ResponseEntity.ok(tipo);
        }
        return ResponseEntity.notFound().build();
    }
}
