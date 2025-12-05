package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.model.request.LibroRequest;
import TDUNU2025.model.LibroResponse;
import TDUNU2025.service.LibroService;
import TDUNU2025.util.ApiRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Libro.BASE)
@RequiredArgsConstructor
public class LibroController {

    private final LibroService service;

    // POST -> /api/libro/guardar
    @PostMapping(ApiRoutes.Libro.GUARDAR)
    public LibroResponse registrar(@RequestBody LibroRequest r) {
        return service.registrar(r);
    }

    // GET -> /api/libro/listar
    @GetMapping(ApiRoutes.Libro.LISTAR)
    public List<LibroResponse> listar() {
        return service.listar();
    }

    // GET -> /api/libro/obtener/{id}
    // Asegúrate de que ApiRoutes.Libro.OBTENER_POR_ID termine en "/{id}"
    @GetMapping(ApiRoutes.Libro.OBTENER_POR_ID)
    public LibroResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    // PUT -> /api/libro/actualizar/{id}
    // Asegúrate de que ApiRoutes.Libro.ACTUALIZAR termine en "/{id}"
    @PutMapping(ApiRoutes.Libro.ACTUALIZAR)
    public LibroResponse actualizar(@PathVariable Long id, @RequestBody LibroRequest r) {
        return service.actualizar(id, r);
    }

    // DELETE -> /api/libro/eliminar/{id}
    // Asegúrate de que ApiRoutes.Libro.ELIMINAR termine en "/{id}"
    @DeleteMapping(ApiRoutes.Libro.ELIMINAR)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}