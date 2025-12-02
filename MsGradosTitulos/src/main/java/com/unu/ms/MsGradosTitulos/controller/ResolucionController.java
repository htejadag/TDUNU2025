package com.unu.ms.MsGradosTitulos.controller;

import java.util.List;

import com.unu.ms.MsGradosTitulos.model.request.ResolucionRequest;
import com.unu.ms.MsGradosTitulos.model.response.ResolucionResponse;
import com.unu.ms.MsGradosTitulos.service.ResolucionService;
import com.unu.ms.MsGradosTitulos.util.ApiRoutes;
import com.unu.ms.MsGradosTitulos.util.Mensajes;
import com.unu.ms.MsGradosTitulos.util.ResponseBase;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ApiRoutes.GradosTitulos.BASE)
@Tag(name = "Resolucion Controller")
public class ResolucionController {

    @Autowired
    ResolucionService resolucionService;

    @GetMapping(value = ApiRoutes.GradosTitulos.LISTAR)
    public ResponseBase<List<ResolucionResponse>> Listar() {
        log.info("Iniciando proceso: Listar resoluciones");
        List<ResolucionResponse> resoluciones = resolucionService.listar();
        log.info("Proceso finalizado: {} resoluciones encontradas", resoluciones.size());
        return ResponseBase.ok(Mensajes.LISTAR_OK, resoluciones);
    }

    @PostMapping(value = ApiRoutes.GradosTitulos.CREAR)
    public ResponseBase<ResolucionResponse> Crear(@RequestBody ResolucionRequest request) {
        log.info("Iniciando proceso: Crear nueva resolución");
        log.debug("Datos recibidos para creación: {}", request);
        ResolucionResponse response = resolucionService.crear(request);
        log.info("Resolución creada exitosamente. ID generado: {}", response.getIdResolucion());
        return ResponseBase.ok(Mensajes.CREADO_OK, response);
    }

    @DeleteMapping(value = ApiRoutes.GradosTitulos.ELIMINAR)
    public ResponseBase<?> Eliminar(@RequestParam(value = "id") Integer id) {
        log.warn("Solicitud recibida: Eliminación de resolución. ID={}", id);
        resolucionService.eliminar(id);
        log.info("Resolución eliminada exitosamente. ID={}", id);
        return ResponseBase.ok(Mensajes.ELIMINADO_OK);
    }

    @PutMapping(value = ApiRoutes.GradosTitulos.ACTUALIZAR)
    public ResponseBase<ResolucionResponse> Actualizar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ResolucionRequest request) {
        log.info("Iniciando proceso: Actualización de resolución. ID={}", id);
        log.debug("Datos recibidos para actualización: {}", request);
        ResolucionResponse response = resolucionService.actualizar(id, request);
        log.info("Resolución actualizada correctamente. ID={}", id);
        return ResponseBase.ok(Mensajes.ACTUALIZADO_OK, response);
    }

    @GetMapping(value = ApiRoutes.GradosTitulos.OBTENER_POR_ID)
    public ResponseBase<ResolucionResponse> ObtenerPorId(@RequestParam(value = "id") Integer id) {
        log.info("Iniciando proceso: Obtener resolución por ID. ID={}", id);
        ResolucionResponse resp = resolucionService.obtenerPorId(id);
        log.info("Resolución obtenida correctamente. ID={}", id);
        return ResponseBase.ok(Mensajes.OBTENER_POR_ID_OK, resp);
    }

}
