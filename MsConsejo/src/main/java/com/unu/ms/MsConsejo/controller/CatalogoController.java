package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.Catalogo.BASE)
@Tag(name = "Catalogo Controller")
public class CatalogoController {

    CatalogoService catalogoService;

    @GetMapping(value = ApiRoutes.Catalogo.LISTAR)
    public ResponseBase<List<CatalogoResponse>> listar() {

        log.info("Inicio request: listar catalogos");

        List<CatalogoResponse> listaResponse = catalogoService.listar();

        log.info("Fin request: listar catalogos. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Catalogo.GUARDAR)
    public ResponseBase<CatalogoResponse> guardar(@RequestBody CatalogoRequest request) {

        log.info("Inicio request: guardar catalogo");
        log.debug("Request guardar catalogo: {}", request);

        CatalogoResponse response = catalogoService.guardar(request);

        log.info("Fin request: guardar catalogo. Id generado: {}", response.getIdCatalogo());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Catalogo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar catalogo");
        log.debug("Id catalogo a eliminar: {}", id);

        catalogoService.eliminar(id);

        log.info("Fin request: eliminar catalogo. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Catalogo.ACTUALIZAR)
    public ResponseBase<CatalogoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody CatalogoRequest request) {

        log.info("Inicio request: actualizar catalogo");
        log.debug("Id catalogo a actualizar: {}", id);
        log.debug("Request actualizar catalogo: {}", request);

        CatalogoResponse response = catalogoService.actualizar(id, request);

        log.info("Fin request: actualizar catalogo. Id actualizado: {}", response.getIdCatalogo());

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Catalogo.OBTENER_POR_ID)
    public ResponseBase<CatalogoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener catalogo por id");
        log.debug("Id catalogo: {}", id);

        CatalogoResponse response = catalogoService.obtenerPorId(id);

        log.info("Fin request: obtener catalogo por id");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_CATEGORIA_Y_VALOR)
    public ResponseBase<CatalogoResponse> buscarPorCategoriaYValor(
            @RequestParam String categoria,
            @RequestParam String valor) {

        log.info("Inicio request: buscar catalogo por categoria y valor");
        log.debug("Categoria: {}, Valor: {}", categoria, valor);

        CatalogoResponse response =
                catalogoService.buscarPorCategoriaYValor(categoria, valor);

        log.info("Fin request: buscar catalogo por categoria y valor");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Catalogo.BUSCAR_POR_CATEGORIA)
    public ResponseBase<List<CatalogoResponse>> buscarPorCategoria(@RequestParam String categoria) {

        log.info("Inicio request: buscar catalogos por categoria");
        log.debug("Categoria: {}", categoria);

        List<CatalogoResponse> listaResponse =
                catalogoService.buscarPorCategoria(categoria);

        log.info(
                "Fin request: buscar catalogos por categoria. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}