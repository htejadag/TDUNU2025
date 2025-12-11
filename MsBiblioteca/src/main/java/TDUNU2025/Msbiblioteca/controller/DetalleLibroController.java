package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import TDUNU2025.Msbiblioteca.service.DetalleLibroService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.DetalleLibro.BASE)
@RequiredArgsConstructor
public class DetalleLibroController {

    private final DetalleLibroService service;

    // POST -> /api/detalle-libro/guardar
    @PostMapping(ApiRoutes.DetalleLibro.GUARDAR)
    public DetalleLibroResponse registrar(@RequestBody DetalleLibroRequest r) {
        return service.registrar(r);
    }

    // GET -> /api/detalle-libro/listar
    @GetMapping(ApiRoutes.DetalleLibro.LISTAR)
    public List<DetalleLibroResponse> listar() {
        return service.listar();
    }

    // GET -> /api/detalle-libro/obtener/{id}
    @GetMapping(ApiRoutes.DetalleLibro.OBTENER_POR_ID)
    public DetalleLibroResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    // PUT -> /api/detalle-libro/actualizar/{id}
    @PutMapping(ApiRoutes.DetalleLibro.ACTUALIZAR)
    public DetalleLibroResponse actualizar(@PathVariable Long id,
                                           @RequestBody DetalleLibroRequest r) {
        return service.actualizar(id, r);
    }

    // DELETE -> /api/detalle-libro/eliminar/{id}
    @DeleteMapping(ApiRoutes.DetalleLibro.ELIMINAR)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
