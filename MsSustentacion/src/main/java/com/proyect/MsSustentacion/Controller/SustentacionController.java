package com.proyect.MsSustentacion.Controller;

import com.proyect.MsSustentacion.model.Entity.Sustentacion;
import com.proyect.MsSustentacion.Service.SustentacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sustentaciones")
public class SustentacionController {

    private final SustentacionService service;

    public SustentacionController(SustentacionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Sustentacion>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sustentacion> obtener(@PathVariable Long id) {
        Sustentacion sust = service.findById(id);
        if (sust == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sust);
    }

    @PostMapping
    public ResponseEntity<Sustentacion> crear(@RequestBody Sustentacion sustentacion) {
        Sustentacion nuevo = service.save(sustentacion);
        return ResponseEntity
                .created(URI.create("/api/sustentaciones/" + nuevo.getId()))
                .body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sustentacion> actualizar(@PathVariable Long id, @RequestBody Sustentacion sustentacion) {
        Sustentacion actualizado = service.update(id, sustentacion);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Sustentacion existe = service.findById(id);
        if (existe == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
