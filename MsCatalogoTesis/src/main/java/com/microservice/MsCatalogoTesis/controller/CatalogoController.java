package com.microservice.MsCatalogoTesis.controller;

import com.microservice.MsCatalogoTesis.model.Catalogo;
import com.microservice.MsCatalogoTesis.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar el catálogo
 */
@RestController
@RequestMapping("/api/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService catalogoService;

    /**
     * Obtener todos los elementos del catálogo
     * GET /api/catalogo
     */
    @GetMapping
    public ResponseEntity<List<Catalogo>> obtenerTodos() {
        return ResponseEntity.ok(catalogoService.obtenerTodos());
    }

    /**
     * Obtener elementos por grupo
     * GET /api/catalogo/grupo/{grupo}
     */
    @GetMapping("/grupo/{grupo}")
    public ResponseEntity<List<Catalogo>> obtenerPorGrupo(@PathVariable String grupo) {
        return ResponseEntity.ok(catalogoService.obtenerPorGrupo(grupo));
    }

    /**
     * Obtener elementos activos por grupo
     * GET /api/catalogo/grupo/{grupo}/activos
     */
    @GetMapping("/grupo/{grupo}/activos")
    public ResponseEntity<List<Catalogo>> obtenerActivosPorGrupo(@PathVariable String grupo) {
        return ResponseEntity.ok(catalogoService.obtenerActivosPorGrupo(grupo));
    }

    /**
     * Obtener un elemento específico por grupo y código
     * GET /api/catalogo/grupo/{grupo}/codigo/{codigo}
     */
    @GetMapping("/grupo/{grupo}/codigo/{codigo}")
    public ResponseEntity<Catalogo> obtenerPorGrupoYCodigo(
            @PathVariable String grupo,
            @PathVariable String codigo) {
        return catalogoService.obtenerPorGrupoYCodigo(grupo, codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crear o actualizar un elemento del catálogo
     * POST /api/catalogo
     */
    @PostMapping
    public ResponseEntity<Catalogo> crear(@RequestBody Catalogo catalogo) {
        Catalogo guardado = catalogoService.guardar(catalogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    /**
     * Actualizar un elemento del catálogo
     * PUT /api/catalogo/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Catalogo> actualizar(
            @PathVariable String id,
            @RequestBody Catalogo catalogo) {
        catalogo.setId(id);
        Catalogo actualizado = catalogoService.guardar(catalogo);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Eliminar un elemento del catálogo
     * DELETE /api/catalogo/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        catalogoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
