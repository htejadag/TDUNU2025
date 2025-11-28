package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.service.ConsejoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.Mensajes;
import com.unu.ms.MsConsejo.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.Consejo.BASE)
@Tag(name = "Consejo Controller", description = "Endpoints para gestionar los consejos")
public class ConsejoController {

    @Autowired
    private ConsejoService consejoService;

    @GetMapping(value = ApiRoutes.Consejo.LISTAR)
    public ResponseBase<List<ConsejoResponse>> ListarConsejos() {
        log.info("Solicitud recibida: Listar todos los consejos");
        List<ConsejoResponse> consejos = consejoService.listar();
        log.info("Consejos obtenidos: {}", consejos.size());
        return ResponseBase.ok(Mensajes.LISTAR_OK, consejos);
    }

    @PostMapping(value = ApiRoutes.Consejo.CREAR)
    public ResponseBase<ConsejoResponse> CrearConsejo(@RequestBody ConsejoRequest request) {
        log.info("Solicitud recibida: Crear consejo");
        log.debug("Payload recibido: {}", request);
        ConsejoResponse response = consejoService.crear(request);
        log.info("Consejo creado con ID: {}", response.getIdConsejo());
        return ResponseBase.ok(Mensajes.CREADO_OK,response);
    }

    @DeleteMapping(value = ApiRoutes.Consejo.ELIMINAR)
    public ResponseBase<?> EliminarConsejo(@RequestParam(value = "id") Integer id) {
        log.warn("Solicitud recibida: Eliminar consejo ID {}", id);
        consejoService.eliminar(id);
        log.info("Consejo eliminado: ID {}", id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.Consejo.ACTUALIZAR)
    public ResponseBase<ConsejoResponse> ActualizarMovimiento(
            @RequestParam(value = "id") Integer id,
            @RequestBody ConsejoRequest request) {
        log.info("Solicitud recibida: Actualizar consejo ID {}", id);
        log.debug("Payload de actualización: {}", request);
        ConsejoResponse response = consejoService.actualizar(id, request);
        log.info("Consejo actualizado correctamente: ID {}", id);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.Consejo.OBTENER_POR_ID)
    public ResponseBase<ConsejoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        return ResponseBase.ok(Mensajes.OBTENER_POR_ID_OK, consejoService.obtenerPorId(id));
    }

   
}
