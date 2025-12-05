package com.unu.MsDocumentos.controller;

import com.unu.MsDocumentos.model.Documento;
import com.unu.MsDocumentos.model.request.DocumentoRequest;
import com.unu.MsDocumentos.service.IDocumentoService;
import com.unu.MsDocumentos.utils.ApiRoutes;
import com.unu.MsDocumentos.utils.Mensajes;
import com.unu.MsDocumentos.utils.ResponseBase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.DocumentoRoutes.BASE)
public class DocumentoController {

    private final IDocumentoService service;

    @PostMapping(ApiRoutes.DocumentoRoutes.add)
    public ResponseBase<Documento> add(@RequestBody DocumentoRequest request) {
        log.info("Iniciando registro de documento: {}", request.getAsunto());
        Documento documentoCreado = service.registrar(request);
        return ResponseBase.ok(documentoCreado);
    }

    @GetMapping(ApiRoutes.DocumentoRoutes.listar)
    public ResponseBase<List<Documento>> listarTodos() {
        log.info("Listando todos los documentos");
        List<Documento> lista = service.listarTodos();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.DocumentoRoutes.listarId)
    public ResponseEntity<Documento> buscarPorId(@PathVariable UUID id) {
        log.info("Buscando documento con ID: {}", id);
        Documento documento = service.buscarPorId(id);
        return ResponseEntity.ok(documento);
    }

    @PutMapping(ApiRoutes.DocumentoRoutes.update)
    public ResponseBase<Documento> actualizar(@PathVariable UUID id, @RequestBody DocumentoRequest request) {
        log.info("Actualizando documento ID: {}", id);
        Documento documentoActualizado = service.actualizar(id, request);
        return ResponseBase.ok(documentoActualizado);
    }

    @DeleteMapping(ApiRoutes.DocumentoRoutes.delete)
    public ResponseBase<Void> eliminar(@PathVariable UUID id) {
        log.info("Eliminando documento ID: {}", id);
        service.eliminar(id);
        return ResponseBase.ok(Mensajes.deleteExitoso, null);
    }
}