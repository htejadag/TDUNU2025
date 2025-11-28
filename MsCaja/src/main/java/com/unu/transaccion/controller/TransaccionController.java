package com.unu.transaccion.controller;

import com.unu.transaccion.dto.TransaccionRequest;
import com.unu.transaccion.model.Transaccion;
import com.unu.transaccion.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService service;

    @GetMapping
    public List<Transaccion> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transaccion> crear(@RequestBody TransaccionRequest request) {
        // Podrías agregar un try-catch aquí para capturar EntityNotFoundException si envían un ID inválido
        return ResponseEntity.ok(service.guardar(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}