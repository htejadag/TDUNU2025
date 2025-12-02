package com.unu.MsDocumentos.service.impl;

import com.unu.MsDocumentos.model.Documento;
import com.unu.MsDocumentos.model.request.DocumentoRequest;
import com.unu.MsDocumentos.repository.IDocumentoRepository;
import com.unu.MsDocumentos.service.IDocumentoService;
import com.unu.MsDocumentos.utils.Mensajes;
import com.unu.MsDocumentos.utils.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@AllArgsConstructor
public class DocumentoService implements IDocumentoService {

    private final IDocumentoRepository repository;

    @Override
    @Transactional
    public Documento registrar(DocumentoRequest request) {
        log.info("Iniciando registro de documento con asunto: {}", request.getAsunto());

        Documento documento = new Documento();

        documento.setCorrelativo(request.getCorrelativo());
        documento.setFechaEmision(request.getFechaEmision());
        documento.setExpedienteId(request.getExpedienteId());
        documento.setTipoDocumentoId(request.getTipoDocumentoId());
        documento.setOficinaOrigenId(request.getOficinaOrigenId());
        documento.setAsunto(request.getAsunto());
        documento.setReferencia(request.getReferencia());

        return repository.save(documento);
    }

    @Override
    public List<Documento> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Documento buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Mensajes.buscar + id));
    }

    @Override
    @Transactional
    public Documento actualizar(UUID id, DocumentoRequest request) {
        log.info("Actualizando documento ID: {}", id);

        Documento documentoExistente = buscarPorId(id);

        documentoExistente.setCorrelativo(request.getCorrelativo());
        documentoExistente.setFechaEmision(request.getFechaEmision());
        documentoExistente.setExpedienteId(request.getExpedienteId());
        documentoExistente.setTipoDocumentoId(request.getTipoDocumentoId());
        documentoExistente.setOficinaOrigenId(request.getOficinaOrigenId());
        documentoExistente.setAsunto(request.getAsunto());
        documentoExistente.setReferencia(request.getReferencia());

        return repository.save(documentoExistente);
    }

    @Override
    @Transactional
    public void eliminar(UUID id) {
        log.info("Eliminando documento ID: {}", id);

        if (!repository.existsById(id)) {
            throw new NotFoundException(Mensajes.delete + id);
        }

        repository.deleteById(id);
    }
}