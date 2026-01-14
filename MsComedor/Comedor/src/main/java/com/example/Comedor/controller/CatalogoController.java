package com.example.Comedor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Comedor.model.request.Catalogo.CatalogoRequest;
import com.example.Comedor.model.request.Catalogo.CatalogoUpdateRequest;
import com.example.Comedor.model.response.CatalogoResponse;
import com.example.Comedor.service.CatalogoService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Catalogo.BASE_CATALOGO)
public class CatalogoController {

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService) {

        this.catalogoService = catalogoService;
    }

    @GetMapping(value = ApiRoutes.Catalogo.LISTAR_CATALOGO)
    public ResponseBase<List<CatalogoResponse>> listar() {
        List<CatalogoResponse> lista = catalogoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Catalogo.OBTENER_POR_ID_CATALOGO)
    public ResponseBase<CatalogoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        CatalogoResponse response = catalogoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Catalogo.GUARDAR_CATALOGO)
    public ResponseBase<CatalogoResponse> guardar(@RequestBody CatalogoRequest model) {
        CatalogoResponse response = catalogoService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Catalogo.MODIFICAR_CATALOGO)
    public ResponseBase<CatalogoResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody CatalogoUpdateRequest request) {

        CatalogoResponse response = catalogoService.modificar(id, request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Catalogo.ELIMINAR_CATALOGO)
    public ResponseBase<CatalogoResponse> eliminar(@RequestParam(value = "id") Integer id) {
        catalogoService.eliminar(id);
        CatalogoResponse response = catalogoService.eliminar(id);
        return ResponseBase.ok(response);
    }

}