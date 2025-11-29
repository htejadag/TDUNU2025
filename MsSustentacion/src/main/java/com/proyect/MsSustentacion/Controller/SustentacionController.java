package com.proyect.MsSustentacion.Controller;

import com.proyect.MsSustentacion.model.Entity.Sustentacion;
import com.proyect.MsSustentacion.Service.SustentacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyect.MsSustentacion.util.ApiRoutes;

import jakarta.validation.Valid;

import java.net.URI;

@RestController
@RequestMapping(ApiRoutes.Sustentacion.BASE)
public class SustentacionController {

    private final SustentacionService service;

    public SustentacionController(SustentacionService service) {
        this.service = service;
    }

    @GetMapping(ApiRoutes.Sustentacion.LISTAR)
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(ApiRoutes.Sustentacion.OBTENER_POR_ID)
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Sustentacion sust = service.findById(id);
        if (sust == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sust);
    }

    @PostMapping(ApiRoutes.Sustentacion.GUARDAR)
    public ResponseEntity<?> crear(@RequestBody @Valid Sustentacion sustentacion) {
        Sustentacion nuevo = service.save(sustentacion);
        return ResponseEntity
                .created(URI.create(ApiRoutes.Sustentacion.BASE + "/" + nuevo.getId()))
                .body(nuevo);
    }

    @PutMapping(ApiRoutes.Sustentacion.ACTUALIZAR)
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid Sustentacion sustentacion) {
        Sustentacion actualizado = service.update(id, sustentacion);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping(ApiRoutes.Sustentacion.ELIMINAR)
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Sustentacion existe = service.findById(id);
        if (existe == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.ok("Sustentación eliminada correctamente");
    }
}