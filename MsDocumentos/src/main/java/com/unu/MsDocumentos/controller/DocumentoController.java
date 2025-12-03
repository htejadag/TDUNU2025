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

    // 1. CREAR DOCUMENTO (JSON + ARCHIVOS)
    @PostMapping(value = ApiRoutes.DocumentoRoutes.add, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseBase<Documento> add(
            @RequestPart("data") DocumentoRequest request, // El JSON
            @RequestPart(value = "files", required = false) List<MultipartFile> files // Los archivos físicos
    ) throws IOException {

        log.info("Iniciando registro de documento: {}", request.getAsunto());

        // A. Convertimos el Request a Entidad
        Documento documento = mapToEntity(request);

        // B. Procesamos los archivos si existen
        if (files != null && !files.isEmpty()) {
            // Nota: request.getArchivos() trae los metadatos (tipoArchivo) que envió el
            // usuario
            List<Archivo> listaArchivos = procesarArchivos(files, request.getArchivos());
            documento.setArchivos(listaArchivos);
        }

        // C. Guardamos usando el servicio (que asignará versión 1 automáticamente)
        Documento documentoCreado = service.crearDocumento(documento);

        return ResponseBase.ok(documentoCreado);
    }

    // 2. NUEVA VERSIÓN (Agregar anexos a un documento existente)
    // Nota: Agrega esta ruta en tu clase ApiRoutes o úsala directo aquí
    @PutMapping(value = "/{id}/version", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseBase<Documento> agregarVersion(
            @PathVariable String id,
            @RequestPart(value = "files") List<MultipartFile> files,
            @RequestPart(value = "metadatos", required = false) List<ArchivoRequest> metadatos) throws IOException {

        log.info("Agregando nueva versión al documento ID: {}", id);

        // Procesamos los nuevos archivos
        List<Archivo> nuevosArchivos = procesarArchivos(files, metadatos);

        // El servicio calcula la versión (ej: V1 -> V2) y guarda
        Documento actualizado = service.agregarNuevaVersion(id, nuevosArchivos);

        return ResponseBase.ok(actualizado);
    }

    // 3. LISTAR TODOS
    @GetMapping(ApiRoutes.DocumentoRoutes.listar)
    public ResponseBase<List<Documento>> listarTodos() {
        log.info("Listando todos los documentos");
        List<Documento> lista = service.listarTodos(); // Asegúrate de tener este método en tu Service
        return ResponseBase.ok(lista);
    }

    // 4. BUSCAR POR ID (Cambiado a String)
    @GetMapping(ApiRoutes.DocumentoRoutes.listarId)
    public ResponseEntity<Documento> buscarPorId(@PathVariable String id) {
        log.info("Buscando documento con ID: {}", id);
        Documento documento = service.obtenerPorId(id); // Cambié el nombre para coincidir con el estándar Service
        return ResponseEntity.ok(documento);
    }

    // 5. ACTUALIZAR METADATOS (Sin archivos, solo datos)
    @PutMapping(ApiRoutes.DocumentoRoutes.update)
    public ResponseBase<Documento> actualizar(@PathVariable String id, @RequestBody DocumentoRequest request) {
        log.info("Actualizando metadatos del documento ID: {}", id);
        // Aquí deberías implementar un método en el servicio que solo actualice campos
        // como asunto/referencia
        // sin tocar la lista de archivos.
        Documento documentoActualizado = service.actualizarMetadatos(id, request);
        return ResponseBase.ok(documentoActualizado);
    }

    // 6. ELIMINAR (Cambiado a String)
    @DeleteMapping(ApiRoutes.DocumentoRoutes.delete)
    public ResponseBase<Void> eliminar(@PathVariable String id) {
        log.info("Eliminando documento ID: {}", id);
        service.eliminar(id); // Asegúrate de tener este método en tu Service
        return ResponseBase.ok(Mensajes.deleteExitoso, null);
    }

    // --- MÉTODOS PRIVADOS DE AYUDA (Helpers) ---

    private List<Archivo> procesarArchivos(List<MultipartFile> files, List<ArchivoRequest> metadatos) {
        List<Archivo> resultado = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            // Intentamos obtener el metadato correspondiente, si no hay, asumimos "ANEXO"
            // por defecto
            ArchivoRequest meta = (metadatos != null && i < metadatos.size()) ? metadatos.get(i)
                    : new ArchivoRequest("ANEXO");

            Archivo archivo = new Archivo();
            // Datos Técnicos
            archivo.setNombreOriginal(file.getOriginalFilename());
            archivo.setMimeType(file.getContentType());
            archivo.setPeso(file.getSize());
            // AQUÍ: Lógica para guardar en disco/FTP y obtener la URL final
            archivo.setRutaAcceso("ruta/simulada/" + file.getOriginalFilename());

            // Datos de Negocio
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
        doc.setTipoDocumentoId(req.getTipoDocumentoId());
        doc.setOficinaOrigenId(req.getOficinaOrigenId());
        doc.setAsunto(req.getAsunto());
        doc.setReferencia(req.getReferencia());
        return doc;
    }
}