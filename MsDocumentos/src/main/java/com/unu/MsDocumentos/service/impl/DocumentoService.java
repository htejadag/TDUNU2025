package com.unu.MsDocumentos.service.impl;

import com.unu.MsDocumentos.model.Archivo;
import com.unu.MsDocumentos.model.Documento;
import com.unu.MsDocumentos.model.request.DocumentoRequest;
import com.unu.MsDocumentos.repository.DocumentoRepository;
import com.unu.MsDocumentos.service.IDocumentoService;
import com.unu.MsDocumentos.utils.Mensajes;
import com.unu.MsDocumentos.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class DocumentoService implements IDocumentoService {

    private final DocumentoRepository repository;

    @Override
    public Documento crearDocumento(Documento documento) {
        log.info("Creando documento: {}", documento.getAsunto());

        if (documento.getArchivos() != null && !documento.getArchivos().isEmpty()) {
            documento.getArchivos().forEach(archivo -> archivo.setVersion(1));
        }

        documento.setEstado(true);
        return repository.save(documento);
    }

    @Override
    public Documento agregarNuevaVersion(String id, List<Archivo> nuevosArchivos) {
        log.info("Agregando versión al documento ID: {}", id);

        Documento docExistente = obtenerPorId(id);

        if (docExistente.getArchivos() == null) {
            docExistente.setArchivos(new ArrayList<>());
        }

        int maxVersionActual = docExistente.getArchivos().stream()
                .mapToInt(Archivo::getVersion)
                .max()
                .orElse(0);

        int nuevaVersion = maxVersionActual + 1;

        for (Archivo archivo : nuevosArchivos) {
            archivo.setVersion(nuevaVersion);
        }

        docExistente.getArchivos().addAll(nuevosArchivos);
        return repository.save(docExistente);
    }

    @Override
    public List<Documento> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Documento obtenerPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Mensajes.buscar + id));
    }

    @Override
    public Documento actualizarMetadatos(String id, DocumentoRequest request) {
        log.info("Actualizando metadatos ID: {}", id);

        Documento docExistente = obtenerPorId(id);

        docExistente.setCorrelativo(request.getCorrelativo());
        docExistente.setFechaEmision(request.getFechaEmision());
        docExistente.setExpedienteId(request.getExpedienteId());
        docExistente.setTipoDocumento(request.getTipoDocumento());
        docExistente.setOficinaOrigen(request.getOficinaOrigen());
        docExistente.setAsunto(request.getAsunto());
        docExistente.setReferencia(request.getReferencia());

        return repository.save(docExistente);
    }

    @Override
    public void eliminar(String id) {
        log.info("Eliminando lógicamente ID: {}", id);
        Documento docExistente = obtenerPorId(id);
        docExistente.setEstado(false);
        repository.save(docExistente);
    }
}