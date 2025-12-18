package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.CatalogoRequest;
import com.unu.ms.MsConsejo.model.response.CatalogoResponse;
import com.unu.ms.MsConsejo.service.CatalogoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.Catalogo.BASE)
@Tag(name = "Catalogo Controller")
public class CatalogoController {

    private CatalogoService catalogoService;

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

    @GetMapping(value = ApiRoutes.Catalogo.OBTENER_POR_ID)
    public ResponseBase<CatalogoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        CatalogoResponse response = catalogoService.obtenerPorId(id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @PutMapping(value = ApiRoutes.Catalogo.ACTUALIZAR)
    public ResponseBase<CatalogoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody CatalogoRequest request) {
        CatalogoResponse response = catalogoService.actualizar(id, request);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Catalogo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {
        catalogoService.eliminar(id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_DESCRIPCION)
    public ResponseBase<List<CatalogoResponse>> buscarPorDescripcion(@RequestParam String descripcion) {
        List<CatalogoResponse> listaResponse = catalogoService.buscarPorDescripcion(descripcion);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_ABREVIATURA)
    public ResponseBase<List<CatalogoResponse>> buscarPorAbreviatura(@RequestParam String abreviatura) {
        List<CatalogoResponse> listaResponse = catalogoService.buscarPorAbreviatura(abreviatura);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_ESTADO)
    public ResponseBase<List<CatalogoResponse>> buscarPorEstado(@RequestParam String estado) {
        List<CatalogoResponse> listaResponse = catalogoService.buscarPorEstado(estado);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_HIJOS_POR_PADRE)
    public ResponseBase<List<CatalogoResponse>> buscarHijosPorPadre(@RequestParam Integer idPadre) {
        List<CatalogoResponse> listaResponse = catalogoService.buscarHijosPorPadre(idPadre);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_CATALOGOS_RAIZ)
    public ResponseBase<List<CatalogoResponse>> buscarCatalogosRaiz() {
        List<CatalogoResponse> listaResponse = catalogoService.buscarCatalogosRaiz();
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_PADRE_Y_ESTADO)
    public ResponseBase<List<CatalogoResponse>> buscarPorPadreYEstado(
            @RequestParam Integer idPadre,
            @RequestParam String estado) {
        List<CatalogoResponse> listaResponse = catalogoService.buscarPorPadreYEstado(idPadre, estado);
        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}
