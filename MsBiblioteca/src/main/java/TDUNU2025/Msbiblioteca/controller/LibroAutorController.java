package TDUNU2025.Msbiblioteca.controller;  

import TDUNU2025.Msbiblioteca.model.request.LibroAutorRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroAutorResponse;
import TDUNU2025.Msbiblioteca.service.LibroAutorService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.LibroAutor.BASE)
@RequiredArgsConstructor
public class LibroAutorController {

    private final LibroAutorService service;

    // POST -> /api/libro-autor/guardar
    @PostMapping(ApiRoutes.LibroAutor.GUARDAR)
    public LibroAutorResponse registrar(@RequestBody LibroAutorRequest r) {
        return service.registrar(r);
    }

    // GET -> /api/libro-autor/listar
    @GetMapping(ApiRoutes.LibroAutor.LISTAR)
    public List<LibroAutorResponse> listar() {
        return service.listar();
    }

    // GET -> /api/libro-autor/obtener/{id}
    @GetMapping(ApiRoutes.LibroAutor.OBTENER_POR_ID)
    public LibroAutorResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    // PUT -> /api/libro-autor/actualizar/{id}
    @PutMapping(ApiRoutes.LibroAutor.ACTUALIZAR)
    public LibroAutorResponse actualizar(@PathVariable Long id, @RequestBody LibroAutorRequest r) {
        return service.actualizar(id, r);
    }

    // DELETE -> /api/libro-autor/eliminar/{id}
    @DeleteMapping(ApiRoutes.LibroAutor.ELIMINAR)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
