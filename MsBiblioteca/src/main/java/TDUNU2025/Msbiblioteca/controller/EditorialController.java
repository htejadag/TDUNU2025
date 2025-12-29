package TDUNU2025.Msbiblioteca.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import TDUNU2025.Msbiblioteca.service.EditorialService;
import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor; 

@RestController
@RequestMapping("/v1/editoriales") 
//@RequiredArgsConstructor 
public class EditorialController {

    private final EditorialService editorialService;


    public EditorialController (EditorialService editorialService){
        this.editorialService = editorialService;
    } 

    @GetMapping
    public ResponseEntity<List<EditorialResponse>> listar() {
        List<EditorialResponse> lista = editorialService.listar();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EditorialResponse> obtenerPorId(@PathVariable Long id) {
        EditorialResponse editorial = editorialService.obtenerPorId(id);
        return ResponseEntity.ok(editorial); 
    }

    @PostMapping
    public ResponseEntity<EditorialResponse> guardar(@Valid @RequestBody EditorialRequest request) {
        EditorialResponse guardado = editorialService.guardar(request);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED); 
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EditorialResponse> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody EditorialRequest request) {
        
        EditorialResponse actualizado = editorialService.actualizar(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        editorialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
}