package com.unu.TDUNU2025.Msbiblioteca.controller;

import com.unu.TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.MultaResponse;
import com.unu.TDUNU2025.Msbiblioteca.service.MultaService;
import com.unu.TDUNU2025.Msbiblioteca.util.ApiRoutes;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Multa.BASE)
@RequiredArgsConstructor
public class MultaController {

    private final MultaService service;

    // ✅ POST -> /api/multa/guardar
    @PostMapping(ApiRoutes.Multa.GUARDAR)
    public MultaResponse registrar(@RequestBody MultaRequest r) {
        return service.registrar(r);
    }

    // ✅ GET -> /api/multa/listar
    @GetMapping(ApiRoutes.Multa.LISTAR)
    public List<MultaResponse> listar() {
        return service.listar();
    }

    // ✅ GET -> /api/multa/obtener/{id}
    @GetMapping(ApiRoutes.Multa.OBTENER_POR_ID)
    public MultaResponse obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    // ✅ PUT -> /api/multa/actualizar/{id}
    @PutMapping(ApiRoutes.Multa.ACTUALIZAR)
    public MultaResponse actualizar(@PathVariable Integer id,
                                     @RequestBody MultaRequest r) {
        return service.actualizar(id, r);
    }

    // ✅ DELETE -> /api/multa/eliminar/{id}
    @DeleteMapping(ApiRoutes.Multa.ELIMINAR)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
