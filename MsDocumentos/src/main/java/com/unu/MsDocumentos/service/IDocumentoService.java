package com.unu.MsDocumentos.service;

import com.unu.MsDocumentos.model.Archivo;
import com.unu.MsDocumentos.model.Documento;
import com.unu.MsDocumentos.model.request.DocumentoRequest; // Solo si usas actualizarMetadatos

import java.util.List;

public interface IDocumentoService {
    Documento crearDocumento(Documento documento);

    Documento agregarNuevaVersion(String id, List<Archivo> nuevosArchivos);

    List<Documento> listarTodos();

    Documento obtenerPorId(String id);

    Documento actualizarMetadatos(String id, DocumentoRequest request);

    void eliminar(String id);
}
