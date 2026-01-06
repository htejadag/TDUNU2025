package com.example.MsCursos.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.MsCursos.model.request.CatalogoRequest;
import com.example.MsCursos.model.response.CatalogoResponse;
import com.example.MsCursos.service.CatalogoService;
import com.example.MsCursos.util.ApiRoutes;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiRoutes.Catalogo.BASE)
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService catalogoService;

    @GetMapping(ApiRoutes.Catalogo.LISTAR)
    public List<CatalogoResponse> listar() {
        return catalogoService.listar();
    }

    @GetMapping(ApiRoutes.Catalogo.OBTENER)
    public ResponseEntity<CatalogoResponse> obtener(@PathVariable Integer id) {
        CatalogoResponse res = catalogoService.obtenerPorId(id);
        return (res == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(res);
    }

    @GetMapping(ApiRoutes.Catalogo.POR_CATEGORIA)
    public List<CatalogoResponse> listarPorCategoria(@PathVariable String categoria) {
        return catalogoService.listarPorCategoria(categoria);
    }

    @GetMapping(ApiRoutes.Catalogo.POR_ESTADO)
    public List<CatalogoResponse> listarPorEstado(@PathVariable Boolean estado) {
        return catalogoService.listarPorEstado(estado);
    }

    @GetMapping(ApiRoutes.Catalogo.POR_CATEGORIA_ESTADO)
    public List<CatalogoResponse> listarPorCategoriaYEstado(
            @PathVariable String categoria,
            @PathVariable Boolean estado) {
        return catalogoService.listarPorCategoriaYEstado(categoria, estado);
    }

    @GetMapping(ApiRoutes.Catalogo.POR_PADRE)
    public List<CatalogoResponse> listarHijos(@PathVariable String idPadre) {
        return catalogoService.listarHijos(idPadre);
    }

    @PostMapping(ApiRoutes.Catalogo.GUARDAR)
    public ResponseEntity<CatalogoResponse> guardar(@Valid @RequestBody CatalogoRequest request) {
        return ResponseEntity.ok(catalogoService.guardar(request));
    }

    @PutMapping(ApiRoutes.Catalogo.ACTUALIZAR)
    public ResponseEntity<CatalogoResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody CatalogoRequest request) {
        CatalogoResponse res = catalogoService.actualizar(id, request);
        return (res == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(res);
    }

    @PatchMapping(ApiRoutes.Catalogo.CAMBIAR_ESTADO)
    public ResponseEntity<Void> cambiarEstado(@PathVariable Integer id, @RequestParam Boolean estado) {
        catalogoService.cambiarEstado(id, estado);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(ApiRoutes.Catalogo.ELIMINAR)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        catalogoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
