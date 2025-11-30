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
@RequiredArgsConstructor // Lombok genera el constructor automáticamente (Inyección de dependencias)
public class SustentacionController {

    private final SustentacionService service;

    // 1. LISTAR (Devuelve lista de DTOs)
    @GetMapping(ApiRoutes.Sustentacion.LISTAR)
    public ResponseEntity<List<SustentacionResponse>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    // 2. OBTENER POR ID
    @GetMapping(ApiRoutes.Sustentacion.OBTENER_POR_ID)
    public ResponseEntity<SustentacionResponse> obtener(@PathVariable Long id) {
        // NOTA: Ya no necesitamos validar 'if null'.
        // Si no existe, el Service lanza ResourceNotFoundException y el GlobalHandler
        // responde 404.
        return ResponseEntity.ok(service.findById(id));
    }

    // 3. GUARDAR (Crear)
    @PostMapping(ApiRoutes.Sustentacion.GUARDAR)
    public ResponseEntity<SustentacionResponse> guardar(@Valid @RequestBody SustentacionRequest request) {
        // Aseguramos que sea una creación (ID nulo)
        SustentacionResponse nuevo = service.save(request);
        // Devolvemos 201 Created en lugar de 200 OK
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // 4. ACTUALIZAR (Put)
    // Descomentado y adaptado
    @PutMapping(ApiRoutes.Sustentacion.ACTUALIZAR) // Asumiendo que tienes una ruta /{id}
    public ResponseEntity<SustentacionResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SustentacionRequest request) {

        // Forzamos el ID de la URL dentro del DTO para asegurar que actualice el
        // correcto
        request.setId(id);

        // Reutilizamos el método save (tu lógica de Service ya maneja updates si viene
        // con ID)
        return ResponseEntity.ok(service.save(request));
    }

    // 5. ELIMINAR
    @DeleteMapping(ApiRoutes.Sustentacion.ELIMINAR)
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        // Igual que obtener: Si no existe, el service lanza excepción.
        service.delete(id);

        // Devolvemos 204 No Content (Estándar para borrados exitosos)
        return ResponseEntity.noContent().build();
    }
}