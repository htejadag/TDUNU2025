
package main.java.com.unu.TDUNU2025.Msbiblioteca.controller;

import com.unu.TDUNU2025.Msbiblioteca.model.request.LibroRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.LibroResponse;
import com.unu.TDUNU2025.Msbiblioteca.service.LibroService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libro")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService service;

    @PostMapping
    public LibroResponse registrar(@RequestBody LibroRequest r) {
        return service.registrar(r);
    }

    @GetMapping
    public List<LibroResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public LibroResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PutMapping("/{id}")
    public LibroResponse actualizar(@PathVariable Long id, @RequestBody LibroRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

