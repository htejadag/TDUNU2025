package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Solicitud;
import Postgrado.postgrado.Service.SolicitudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    @PostMapping
    public Solicitud crear(@RequestBody Solicitud solicitud) {
        return service.crear(solicitud);
    }

    @GetMapping
    public List<Solicitud> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Solicitud obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Solicitud actualizar(@PathVariable Integer id, @RequestBody Solicitud data) {
        Solicitud s = service.obtenerPorId(id);
        if (s == null) return null;

        s.setTipoSolicitud(data.getTipoSolicitud());
        s.setDescripcion(data.getDescripcion());
        s.setFechaSolicitud(data.getFechaSolicitud());
        s.setEstadoSolicitud(data.getEstadoSolicitud());

        return service.crear(s);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
