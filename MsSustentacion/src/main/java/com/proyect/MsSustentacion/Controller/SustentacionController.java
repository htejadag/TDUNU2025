package com.proyect.MsSustentacion.Controller;

import com.proyect.MsSustentacion.Service.SustentacionService;
import com.proyect.MsSustentacion.util.ApiRoutes;
import com.proyect.MsSustentacion.model.request.SustentacionRequest;
import com.proyect.MsSustentacion.model.response.SustentacionResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Sustentacion.BASE)
@RequiredArgsConstructor
public class SustentacionController {

    private final SustentacionService service;

    @GetMapping(ApiRoutes.Sustentacion.LISTAR)
    public ResponseEntity<List<SustentacionResponse>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(ApiRoutes.Sustentacion.OBTENER_POR_ID)
    public ResponseEntity<SustentacionResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(ApiRoutes.Sustentacion.GUARDAR)
    public ResponseEntity<SustentacionResponse> guardar(@Valid @RequestBody SustentacionRequest request) {
        SustentacionResponse nuevo = service.save(request);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping(ApiRoutes.Sustentacion.ACTUALIZAR)
    public ResponseEntity<SustentacionResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SustentacionRequest request) {

        request.setId(id);

        return ResponseEntity.ok(service.save(request));
    }

    @DeleteMapping(ApiRoutes.Sustentacion.ELIMINAR)
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}