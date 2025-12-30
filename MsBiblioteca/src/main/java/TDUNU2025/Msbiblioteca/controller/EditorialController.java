package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import TDUNU2025.Msbiblioteca.service.EditorialService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.RUTA_AUTOR)
@RequiredArgsConstructor
public class AutorController {

    private final AutorService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<AutorResponse> listar() {
        return service.listarAutores().stream()
                .map(a -> mapper.map(a, AutorResponse.class))
                .toList();
    }

    @GetMapping("/{id}")
    public AutorResponse obtener(@PathVariable Integer id) {
        return mapper.map(service.obtenerAutorPorId(id)
                .orElseThrow(() -> new RuntimeException("No encontrado")), AutorResponse.class);
    }

    @PostMapping
    public AutorResponse guardar(@RequestBody AutorRequest request) {
        Autor entity = mapper.map(request, Autor.class);
        return mapper.map(service.guardarAutor(entity), AutorResponse.class);
    }

    @PutMapping("/{id}")
    public AutorResponse actualizar(@PathVariable Integer id, @RequestBody AutorRequest request) {
        Autor entity = mapper.map(request, Autor.class);
        entity.setIdAutor(id);
        return mapper.map(service.guardarAutor(entity), AutorResponse.class);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminarAutor(id);
    }
}

