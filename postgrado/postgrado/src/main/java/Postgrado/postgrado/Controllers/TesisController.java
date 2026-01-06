package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Tesis;
import Postgrado.postgrado.Service.TesisService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/tesis")
public class TesisController {

    private final TesisService service;

    public TesisController(TesisService service) {
        this.service = service;
    }

    @PostMapping
    public Tesis crear(@RequestBody Tesis tesis) {
        return service.crear(tesis);
    }

    @GetMapping
    public List<Tesis> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Tesis obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Tesis actualizar(@PathVariable Integer id, @RequestBody Tesis data) {
        Tesis t = service.obtenerPorId(id);
        if (t == null) return null;

        t.setTitulo(data.getTitulo());
        t.setProyectoPdf(data.getProyectoPdf());
        t.setInformeFinalPdf(data.getInformeFinalPdf());
        t.setAntiplagioProyectoPdf(data.getAntiplagioProyectoPdf());
        t.setAntiplagioFinalPdf(data.getAntiplagioFinalPdf());
        t.setEstadoProyecto(data.getEstadoProyecto());
        t.setEstadoInformeFinal(data.getEstadoInformeFinal());
        t.setFechaRegistro(data.getFechaRegistro());

        return service.crear(t);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
