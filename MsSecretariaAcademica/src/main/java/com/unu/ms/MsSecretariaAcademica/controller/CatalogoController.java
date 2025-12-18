package com.unu.ms.MsSecretariaAcademica.controller;

import java.util.List;

import com.unu.ms.MsSecretariaAcademica.model.request.CatalogoRequest;
import com.unu.ms.MsSecretariaAcademica.model.response.CatalogoResponse;
import com.unu.ms.MsSecretariaAcademica.service.CatalogoService;
import com.unu.ms.MsSecretariaAcademica.util.ApiRoutes;
import com.unu.ms.MsSecretariaAcademica.util.Mensajes;
import com.unu.ms.MsSecretariaAcademica.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.Catalogo.BASE)
@Tag(name = "Catalogo Controller")
public class CatalogoController {

    CatalogoService catalogoService;

    @GetMapping(value = ApiRoutes.Catalogo.LISTAR)
    public ResponseBase<List<CatalogoResponse>> listar() {
        List<CatalogoResponse> listaResponse = catalogoService.listar();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Catalogo.GUARDAR)
    public ResponseBase<CatalogoResponse> guardar(@RequestBody CatalogoRequest request) {
        CatalogoResponse response = catalogoService.guardar(request);
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Catalogo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {
        catalogoService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Catalogo.ACTUALIZAR)
    public ResponseBase<CatalogoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody CatalogoRequest request) {
        CatalogoResponse response = catalogoService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Catalogo.OBTENER_POR_ID)
    public ResponseBase<CatalogoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        CatalogoResponse response = catalogoService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_CATEGORIA_Y_VALOR)
    public ResponseBase<CatalogoResponse> buscarPorCategoriaYValor(@RequestParam String categoria, @RequestParam String valor) {
        CatalogoResponse response = catalogoService.buscarPorCategoriaYValor(categoria, valor);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_CATEGORIA)
    public ResponseBase<List<CatalogoResponse>> buscarPorCategoria(@RequestParam String categoria) {
        List<CatalogoResponse> listaResponse = catalogoService.buscarPorCategoria(categoria);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}