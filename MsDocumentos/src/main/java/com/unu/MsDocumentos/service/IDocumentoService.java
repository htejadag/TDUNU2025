package com.unu.MsDocumentos.service;

import com.unu.MsDocumentos.model.Documento;
import com.unu.MsDocumentos.model.request.DocumentoRequest;

import java.util.List;
import java.util.UUID;

public interface IDocumentoService {

    Documento registrar(DocumentoRequest request);

    List<Documento> listarTodos();

    Documento buscarPorId(UUID id);

    Documento actualizar(UUID id, DocumentoRequest request);

    void eliminar(UUID id);
}