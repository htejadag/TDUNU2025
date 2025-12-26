package com.microservice.MsCatalogoTesis.controller;

import com.microservice.MsCatalogoTesis.config.ApiRoutes;
import com.microservice.MsCatalogoTesis.model.Catalogo;
import com.microservice.MsCatalogoTesis.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Catalogo.BASE)
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService catalogoService;

    @GetMapping
    public ResponseEntity<List<Catalogo>> obtenerTodos() {
        return ResponseEntity.ok(catalogoService.obtenerTodos());
    }

    @GetMapping(ApiRoutes.Catalogo.BY_GRUPO)
    public ResponseEntity<List<Catalogo>> obtenerPorGrupo(@PathVariable String grupo) {
        return ResponseEntity.ok(catalogoService.obtenerPorGrupo(grupo));
    }

    @GetMapping(ApiRoutes.Catalogo.ACTIVOS_BY_GRUPO)
    public ResponseEntity<List<Catalogo>> obtenerActivosPorGrupo(@PathVariable String grupo) {
        return ResponseEntity.ok(catalogoService.obtenerActivosPorGrupo(grupo));
    }

    @GetMapping(ApiRoutes.Catalogo.BY_GRUPO_AND_CODIGO)
    public ResponseEntity<Catalogo> obtenerPorGrupoYCodigo(
            @PathVariable String grupo,
            @PathVariable String codigo) {
        return catalogoService.obtenerPorGrupoYCodigo(grupo, codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Catalogo> crear(@RequestBody Catalogo catalogo) {
        Catalogo guardado = catalogoService.guardar(catalogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping(ApiRoutes.Catalogo.BY_ID)
    public ResponseEntity<Catalogo> actualizar(
            @PathVariable String id,
            @RequestBody Catalogo catalogo) {
        catalogo.setId(id);
        Catalogo actualizado = catalogoService.guardar(catalogo);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping(ApiRoutes.Catalogo.BY_ID)
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        catalogoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
