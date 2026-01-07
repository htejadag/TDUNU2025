package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Tesis;
import Postgrado.postgrado.Service.TesisService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/tesis")
public class TesisController {

    private final TesisService service;

    public TesisController(TesisService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Tesis> crear(@Valid @RequestBody Tesis tesis) {
        Tesis nuevaTesis = service.crear(tesis);
        return new ResponseEntity<>(nuevaTesis, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tesis>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tesis> obtener(@PathVariable Integer id) {
        Tesis tesis = service.obtenerPorId(id);
        if (tesis == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tesis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tesis> actualizar(@PathVariable Integer id, @Valid @RequestBody Tesis data) {
        Tesis t = service.obtenerPorId(id);
        if (t == null) {
            return ResponseEntity.notFound().build();
        }

        t.setTitulo(data.getTitulo());
        t.setProyectoPdf(data.getProyectoPdf());
        t.setInformeFinalPdf(data.getInformeFinalPdf());
        t.setAntiplagioProyectoPdf(data.getAntiplagioProyectoPdf());
        t.setAntiplagioFinalPdf(data.getAntiplagioFinalPdf());
        t.setEstadoProyecto(data.getEstadoProyecto());
        t.setEstadoInformeFinal(data.getEstadoInformeFinal());
        // fechaRegistro eliminada, se controla por auditoría

        Tesis actualizada = service.crear(t);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Tesis t = service.obtenerPorId(id);
        if (t == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
