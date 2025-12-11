package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.LibroCategoriaRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroCategoriaResponse;
import TDUNU2025.Msbiblioteca.service.LibroCategoriaService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.LibroCategoria.BASE)
@RequiredArgsConstructor
public class LibroCategoriaController {

    private final LibroCategoriaService service;

    @PostMapping(ApiRoutes.LibroCategoria.GUARDAR)
    public LibroCategoriaResponse registrar(@RequestBody LibroCategoriaRequest r) {
        return service.registrar(r);
    }

    @GetMapping(ApiRoutes.LibroCategoria.LISTAR)
    public List<LibroCategoriaResponse> listar() {
        return service.listar();
    }

    @GetMapping(ApiRoutes.LibroCategoria.OBTENER_POR_ID)
    public LibroCategoriaResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PutMapping(ApiRoutes.LibroCategoria.ACTUALIZAR)
    public LibroCategoriaResponse actualizar(@PathVariable Long id, @RequestBody LibroCategoriaRequest r) {
        return service.actualizar(id, r);
    }

    @DeleteMapping(ApiRoutes.LibroCategoria.ELIMINAR)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
