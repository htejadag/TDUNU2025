package com.unu.MsDocumentos.service;

import com.unu.MsDocumentos.controller.request.DocumentoRequest;
import com.unu.MsDocumentos.model.Documento;

import java.util.List;
import java.util.UUID;

public interface IDocumentoService {

    Documento registrar(DocumentoRequest request);

    List<Documento> listarTodos();

    Documento buscarPorId(UUID id);

    Documento actualizar(UUID id, DocumentoRequest request);

    void eliminar(UUID id);
}