package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.service.ConsejoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.Consejo.BASE)
@Tag(name = "Consejo Controller")
public class ConsejoController {

    private ConsejoService consejoService;

    @GetMapping(value = ApiRoutes.Consejo.LISTAR)
    public ResponseBase<List<ConsejoResponse>> listar() {

        log.info("Inicio request: listar consejos");

        List<ConsejoResponse> listaResponse = consejoService.listar();

        log.info("Fin request: listar consejos. Total registros: {}", listaResponse.size());

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

    @PostMapping(value = ApiRoutes.Consejo.GUARDAR)
    public ResponseBase<ConsejoResponse> guardar(@RequestBody ConsejoRequest request) {

        log.info("Inicio request: guardar consejo");
        log.debug("Request guardar consejo: {}", request);

        ConsejoResponse response = consejoService.guardar(request);

        log.info("Fin request: guardar consejo. Id generado: {}", response.getIdConsejo());

        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.Consejo.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: eliminar consejo");
        log.debug("Id consejo a eliminar: {}", id);

        consejoService.eliminar(id);

        log.info("Fin request: eliminar consejo. Id eliminado: {}", id);

        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Consejo.ACTUALIZAR)
    public ResponseBase<ConsejoResponse> actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ConsejoRequest request) {

        log.info("Inicio request: actualizar consejo");
        log.debug("Id consejo a actualizar: {}", id);
        log.debug("Request actualizar consejo: {}", request);

        ConsejoResponse response = consejoService.actualizar(id, request);

        log.info("Fin request: actualizar consejo. Id actualizado: {}", response.getIdConsejo());

        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Consejo.OBTENER_POR_ID)
    public ResponseBase<ConsejoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {

        log.info("Inicio request: obtener consejo por id");
        log.debug("Id consejo: {}", id);

        ConsejoResponse response = consejoService.obtenerPorId(id);

        log.info("Fin request: obtener consejo por id");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Consejo.BUSCAR_POR_NOMBRE)
    public ResponseBase<ConsejoResponse> buscarPorNombre(@RequestParam String nombre) {

        log.info("Inicio request: buscar consejo por nombre");
        log.debug("Nombre consejo: {}", nombre);

        ConsejoResponse response = consejoService.buscarPorNombre(nombre);

        log.info("Fin request: buscar consejo por nombre");

        return ResponseBase.ok(Mensajes.OBTENER_POR_OK, response);
    }

    @GetMapping(value = ApiRoutes.Consejo.BUSCAR_POR_ESTADO)
    public ResponseBase<List<ConsejoResponse>> buscarPorEstado(@RequestParam Integer idEstado) {

        log.info("Inicio request: buscar consejos por estado");
        log.debug("Id estado: {}", idEstado);

        List<ConsejoResponse> listaResponse = consejoService.buscarPorEstado(idEstado);

        log.info(
                "Fin request: buscar consejos por estado. Total registros: {}",
                listaResponse.size()
        );

        return ResponseBase.ok(Mensajes.LISTAR_OK, listaResponse);
    }

}