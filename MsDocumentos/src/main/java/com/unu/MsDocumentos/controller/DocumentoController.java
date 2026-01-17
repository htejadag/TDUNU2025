package com.unu.MsDocumentos.controller;

import com.unu.MsDocumentos.model.Archivo;
import com.unu.MsDocumentos.model.Documento;
import com.unu.MsDocumentos.model.request.ArchivoRequest;
import com.unu.MsDocumentos.model.request.DocumentoRequest;
import com.unu.MsDocumentos.service.IDocumentoService;
import com.unu.MsDocumentos.utils.ApiRoutes;
import com.unu.MsDocumentos.utils.Mensajes;
import com.unu.MsDocumentos.utils.ResponseBase;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping(ApiRoutes.DocumentoRoutes.BASE)
public class DocumentoController {

    private final IDocumentoService service;

    // ===================== CREAR DOCUMENTO (MULTIPART + JSON)
    // =====================
    @PostMapping(value = ApiRoutes.DocumentoRoutes.add, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseBase<Documento> add(

            @RequestPart("data") DocumentoRequest request,

            @RequestPart(value = "files", required = false) List<MultipartFile> files

    ) throws IOException {

        log.info("Iniciando registro de documento: {}", request.getAsunto());

        Documento documento = mapToEntity(request);

        if (files != null && !files.isEmpty()) {
            List<Archivo> listaArchivos = procesarArchivos(files, request.getArchivos());
            documento.setArchivos(listaArchivos);
        }

        Documento documentoCreado = service.crearDocumento(documento);
        return ResponseBase.ok(documentoCreado);
    }

    // ===================== AGREGAR NUEVA VERSIÓN =====================
    @PutMapping(value = "/{id}/version", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseBase<Documento> agregarVersion(

            @PathVariable String id,

            @RequestPart("files") List<MultipartFile> files,

            @RequestPart(value = "metadatos", required = false) List<ArchivoRequest> metadatos

    ) throws IOException {

        log.info("Agregando nueva versión al documento ID: {}", id);

        List<Archivo> nuevosArchivos = procesarArchivos(files, metadatos);
        Documento actualizado = service.agregarNuevaVersion(id, nuevosArchivos);

        return ResponseBase.ok(actualizado);
    }

    // ===================== LISTAR =====================
    @GetMapping(ApiRoutes.DocumentoRoutes.listar)
    public ResponseBase<List<Documento>> listarTodos() {
        return ResponseBase.ok(service.listarTodos());
    }

    // ===================== OBTENER POR ID =====================
    @GetMapping(ApiRoutes.DocumentoRoutes.listarId)
    public ResponseEntity<Documento> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    // ===================== ACTUALIZAR METADATOS (JSON) =====================
    @PutMapping(value = ApiRoutes.DocumentoRoutes.update, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBase<Documento> actualizar(
            @PathVariable String id,
            @RequestBody DocumentoRequest request) {
        log.info("Actualizando metadatos del documento ID: {}", id);
        return ResponseBase.ok(service.actualizarMetadatos(id, request));
    }

    // ===================== ELIMINAR =====================
    @DeleteMapping(ApiRoutes.DocumentoRoutes.delete)
    public ResponseBase<Void> eliminar(@PathVariable String id) {
        log.info("Eliminando documento ID: {}", id);
        service.eliminar(id);
        return ResponseBase.ok(Mensajes.deleteExitoso, null);
    }

    // ===================== MÉTODOS PRIVADOS =====================

    private List<Archivo> procesarArchivos(List<MultipartFile> files, List<ArchivoRequest> metadatos) {
        List<Archivo> resultado = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            ArchivoRequest meta = (metadatos != null && i < metadatos.size())
                    ? metadatos.get(i)
                    : new ArchivoRequest("ANEXO");

            Archivo archivo = new Archivo();
            archivo.setNombreOriginal(file.getOriginalFilename());
            archivo.setMimeType(file.getContentType());
            archivo.setPeso(file.getSize());
            archivo.setRutaAcceso("ruta/simulada/" + file.getOriginalFilename());
            archivo.setTipoArchivo(meta.getTipoArchivo());

            resultado.add(archivo);
        }
        return resultado;
    }

    private Documento mapToEntity(DocumentoRequest req) {
        Documento doc = new Documento();
        doc.setCorrelativo(req.getCorrelativo());
        doc.setFechaEmision(req.getFechaEmision());
        doc.setExpedienteId(req.getExpedienteId());
        doc.setTipoDocumento(req.getTipoDocumento());
        doc.setOficinaOrigen(req.getOficinaOrigen());
        doc.setAsunto(req.getAsunto());
        doc.setReferencia(req.getReferencia());
        return doc;
    }
}
