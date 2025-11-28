package tdunu.MsCaja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdunu.MsCaja.model.entity.Cajero;
import tdunu.MsCaja.model.request.CajeroRequest;
import tdunu.MsCaja.model.response.CajeroResponse;
import tdunu.MsCaja.service.CajeroService;

import java.util.List;

@RestController
@RequestMapping("/api/cajeros")
public class CajeroController {

    @Autowired
    private CajeroService cajeroService;

    @GetMapping
    public ResponseEntity<List<CajeroResponse>> getAllCajeros() {
        List<CajeroResponse> cajeros = cajeroService.getAllCajeros();
        return ResponseEntity.ok(cajeros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CajeroResponse> getCajeroById(@PathVariable Integer id) {
        CajeroResponse cajero = cajeroService.getCajeroById(id);
        return ResponseEntity.ok(cajero);
    }

    @PostMapping
    public ResponseEntity<CajeroResponse> createCajero(@RequestBody CajeroRequest cajeroRequest) {
        CajeroResponse createdCajero = cajeroService.createCajero(cajeroRequest);
        return ResponseEntity.status(201).body(createdCajero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CajeroResponse> updateCajero(@PathVariable Integer id, @RequestBody CajeroRequest cajeroRequest) {
        CajeroResponse updatedCajero = cajeroService.updateCajero(id, cajeroRequest);
        return ResponseEntity.ok(updatedCajero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCajero(@PathVariable Integer id) {
        cajeroService.deleteCajero(id);
        return ResponseEntity.noContent().build();
    }
}