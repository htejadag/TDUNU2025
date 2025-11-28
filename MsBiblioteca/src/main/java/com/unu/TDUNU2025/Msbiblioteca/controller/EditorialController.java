package com.unu.TDUNU2025.Msbiblioteca.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Import simplificado
import com.unu.TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import com.unu.TDUNU2025.Msbiblioteca.service.EditorialService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/editoriales")
// @RequiredArgsConstructor  <-- BORRA O COMENTA ESTO SI USAS EL CONSTRUCTOR MANUAL
public class EditorialController {

    private final EditorialService editorialService;

    // --- AGREGA ESTE CONSTRUCTOR MANUALMENTE ---
    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }
    // -------------------------------------------

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
        return ResponseEntity.noContent().build();
    }
}