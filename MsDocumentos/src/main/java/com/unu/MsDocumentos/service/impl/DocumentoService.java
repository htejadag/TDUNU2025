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

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class DocumentoService implements IDocumentoService {

    private final DocumentoRepository repository;

    // 1. CREAR DOCUMENTO
    @Override
    public Documento crearDocumento(Documento documento) {
        log.info("Iniciando registro de documento: {}", documento.getAsunto());

        // Al crear, la versión inicial de los archivos es 1
        if (documento.getArchivos() != null) {
            documento.getArchivos().forEach(archivo -> archivo.setVersion(1));
        }

        // El estado nace en true por defecto (definido en AuditoriaEstado),
        // pero podemos asegurarlo aquí si quieres:
        documento.setEstado(true);

        return repository.save(documento);
    }

    // 2. AGREGAR NUEVA VERSIÓN
    @Override
    public Documento agregarNuevaVersion(String id, List<Archivo> nuevosArchivos) {
        log.info("Agregando nueva versión al documento ID: {}", id);

        Documento docExistente = obtenerPorId(id);

        // Calculamos la versión siguiente
        int maxVersionActual = docExistente.getArchivos().stream()
                .mapToInt(Archivo::getVersion)
                .max()
                .orElse(0);

        int nuevaVersion = maxVersionActual + 1;

        // Asignamos la nueva versión a los archivos entrantes
        for (Archivo archivo : nuevosArchivos) {
            archivo.setVersion(nuevaVersion);
        }

        // Agregamos a la lista y guardamos
        docExistente.getArchivos().addAll(nuevosArchivos);

        return repository.save(docExistente);
    }

    // 3. LISTAR TODOS
    @Override
    public List<Documento> listarTodos() {
        // Nota: Esto traerá activos e inactivos.
        // Más adelante podrías querer filtrar solo los de estado = true.
        return repository.findAll();
    }

    // 4. BUSCAR POR ID
    @Override
    public Documento obtenerPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Mensajes.buscar + id));
    }

    // 5. ACTUALIZAR METADATOS
    @Override
    public Documento actualizarMetadatos(String id, DocumentoRequest request) {
        log.info("Actualizando metadatos documento ID: {}", id);

        Documento docExistente = obtenerPorId(id);

        docExistente.setCorrelativo(request.getCorrelativo());
        docExistente.setFechaEmision(request.getFechaEmision());
        docExistente.setExpedienteId(request.getExpedienteId());
        docExistente.setTipoDocumentoId(request.getTipoDocumentoId());
        docExistente.setOficinaOrigenId(request.getOficinaOrigenId());
        docExistente.setAsunto(request.getAsunto());
        docExistente.setReferencia(request.getReferencia());

        return repository.save(docExistente);
    }

    // 6. ELIMINAR (BORRADO LÓGICO)
    @Override
    public void eliminar(String id) {
        log.info("Eliminando (lógicamente) documento ID: {}", id);

        // 1. Buscamos el documento (si no existe, obtenerPorId lanza la excepción)
        Documento docExistente = obtenerPorId(id);

        // 2. Cambiamos el estado a false (Inactivo)
        docExistente.setEstado(false);

        // 3. Guardamos los cambios
        repository.save(docExistente);
    }
}