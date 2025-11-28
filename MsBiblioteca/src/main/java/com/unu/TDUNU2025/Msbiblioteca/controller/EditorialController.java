package com.unu.TDUNU2025.Msbiblioteca.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unu.TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import com.unu.TDUNU2025.Msbiblioteca.service.EditorialService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/editoriales") // URL Base: http://localhost:8080/api/editoriales
@RequiredArgsConstructor 
public class EditorialController {

    private final EditorialService editorialService;

    @GetMapping
    public ResponseEntity<List<EditorialResponse>> listar() {
        List<EditorialResponse> lista = editorialService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditorialResponse> obtenerPorId(@PathVariable Long id) {
        EditorialResponse editorial = editorialService.obtenerPorId(id);
        
        if (editorial == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editorial); 
    }

    @PostMapping
    public ResponseEntity<EditorialResponse> guardar(@Valid @RequestBody EditorialRequest request) {
        EditorialResponse guardado = editorialService.guardar(request);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        EditorialResponse editorial = editorialService.obtenerPorId(id);
        if (editorial == null) {
            return ResponseEntity.notFound().build();
        }

        editorialService.eliminar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content (éxito sin cuerpo)
    }
}