package com.unu.TDUNU2025.Msbiblioteca.controller;

import com.unu.TDUNU2025.Msbiblioteca.model.request.CategoriaLibroRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.CategoriaLibroResponse;
import com.unu.TDUNU2025.Msbiblioteca.service.CategoriaLibroService;
import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
//@RequiredArgsConstructor
public class CategoriaLibroController {

    private final CategoriaLibroService categoriaLibroService;

    public CategoriaLibroController(CategoriaLibroService categoriaLibroService) {
        this.categoriaLibroService = categoriaLibroService;
    }
    
    // POST: Crear una nueva categoría
    @PostMapping
    public ResponseEntity<CategoriaLibroResponse> createCategoria(@Valid @RequestBody CategoriaLibroRequest request) {
        CategoriaLibroResponse response = categoriaLibroService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET: Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaLibroResponse> getCategoriaById(@PathVariable Long id) {
        CategoriaLibroResponse response = categoriaLibroService.findById(id);
        return ResponseEntity.ok(response);
    }

    // GET: Listar todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoriaLibroResponse>> getAllCategorias() {
        List<CategoriaLibroResponse> responseList = categoriaLibroService.findAll();
        return ResponseEntity.ok(responseList);
    }

    // PUT: Actualizar una categoría por ID
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaLibroResponse> updateLibroCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaLibroRequest request) {
        
        CategoriaLibroResponse response = categoriaLibroService.update(id, request);
        return ResponseEntity.ok(response);
    }

    // DELETE: Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibroCategoria(@PathVariable Long id) {
        categoriaLibroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}