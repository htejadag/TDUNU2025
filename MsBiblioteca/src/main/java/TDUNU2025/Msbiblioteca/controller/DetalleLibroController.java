package TDUNU2025.Msbiblioteca.controller;

import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import TDUNU2025.Msbiblioteca.service.DetalleLibroService;

import java.util.List;

@RestController
@RequestMapping("/v1/detalle-libros")
//@RequiredArgsConstructor
public class DetalleLibroController {

    private final DetalleLibroService detalleLibroService;

    public DetalleLibroController(DetalleLibroService detalleLibroService) {
        this.detalleLibroService = detalleLibroService;
    }
    // POST: Crear un nuevo detalle de libro (Inventario)
    @PostMapping
    public ResponseEntity<DetalleLibroResponse> createDetalleLibro(@Valid @RequestBody DetalleLibroRequest request) {
        DetalleLibroResponse response = detalleLibroService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET: Obtener un detalle de libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleLibroResponse> getDetalleLibroById(@PathVariable Integer id) {
        DetalleLibroResponse response = detalleLibroService.findById(id);
        return ResponseEntity.ok(response);
    }

    // GET: Listar todos los detalles de libros
    @GetMapping
    public ResponseEntity<List<DetalleLibroResponse>> getAllDetalleLibros() {
        List<DetalleLibroResponse> responseList = detalleLibroService.findAll();
        return ResponseEntity.ok(responseList);
    }

    // PUT: Actualizar un detalle de libro por ID
    @PutMapping("/{id}")
    public ResponseEntity<DetalleLibroResponse> updateDetalleLibro(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleLibroRequest request) {
        
        DetalleLibroResponse response = detalleLibroService.update(id, request);
        return ResponseEntity.ok(response);
    }

    // DELETE: Eliminar un detalle de libro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleLibro(@PathVariable Integer id) {
        detalleLibroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}