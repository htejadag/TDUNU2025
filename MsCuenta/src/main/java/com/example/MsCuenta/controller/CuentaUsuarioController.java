package com.example.MsCuenta.controller;

import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.request.CuentaRequest;
import com.example.MsCuenta.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas") // <--- RUTA BASE
public class CuentaUsuarioController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<CuentaUsuarioModel> listar() {
        return cuentaService.listarCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaUsuarioModel> obtener(@PathVariable Integer id) {
        return cuentaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CuentaUsuarioModel> crear(
            @RequestBody CuentaRequest request, 
            @RequestParam Integer idUsuarioOperador) { 
        return ResponseEntity.ok(cuentaService.crearCuenta(request, idUsuarioOperador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaUsuarioModel> actualizar(
            @PathVariable Integer id, 
            @RequestBody CuentaRequest request,
            @RequestParam Integer idUsuarioOperador) {
        try {
            return ResponseEntity.ok(cuentaService.actualizarCuenta(id, request, idUsuarioOperador));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id,
            @RequestParam Integer idUsuarioOperador) {
        try {
            cuentaService.eliminarCuenta(id, idUsuarioOperador);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}