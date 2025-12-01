package com.unu.MsDocumentos.controller;

import com.unu.MsDocumentos.controller.request.DocumentoRequest;
import com.unu.MsDocumentos.model.Documento;
import com.unu.MsDocumentos.service.IDocumentoService;
import com.unu.MsDocumentos.utils.ApiRoutes;
// import com.unu.MsDocumentos.utils.Mensajes; // Úsalo si retornas mensajes personalizados
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
@RequestMapping(ApiRoutes.DocumentoRoutes.BASE) // Tu ruta base
public class DocumentoController {

    private final IDocumentoService service; // 'final' es buena práctica con @AllArgsConstructor

    // 1. CREAR (POST)
    @PostMapping(ApiRoutes.DocumentoRoutes.add) // Mantuve tu constante aquí
    public ResponseEntity<Documento> add(@RequestBody DocumentoRequest request) {
        log.info("Iniciando registro de documento: {}", request.getAsunto());
        Documento documentoCreado = service.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoCreado);
    }

    // 2. LISTAR TODOS (GET)
    // Reemplaza "/listar" por tu constante ApiRoutes si la tienes
    @GetMapping("/listar")
    public ResponseEntity<List<Documento>> listarTodos() {
        log.info("Listando todos los documentos");
        List<Documento> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // 3. BUSCAR POR ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Documento> buscarPorId(@PathVariable UUID id) {
        log.info("Buscando documento con ID: {}", id);
        Documento documento = service.buscarPorId(id);
        return ResponseEntity.ok(documento);
    }

    // 4. ACTUALIZAR (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Documento> actualizar(@PathVariable UUID id, @RequestBody DocumentoRequest request) {
        log.info("Actualizando documento ID: {}", id);
        Documento documentoActualizado = service.actualizar(id, request);
        return ResponseEntity.ok(documentoActualizado);
    }

    // 5. ELIMINAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        log.info("Eliminando documento ID: {}", id);
        service.eliminar(id);
        // Retornamos 'No Content' (204) porque ya no hay nada que mostrar
        return ResponseEntity.noContent().build();
    }
}