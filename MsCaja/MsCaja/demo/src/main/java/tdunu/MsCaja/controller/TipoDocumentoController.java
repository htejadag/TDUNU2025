package tdunu.MsCaja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tdunu.MsCaja.model.entity.TipoDocumento;
import tdunu.MsCaja.model.request.TipoDocumentoRequest;
import tdunu.MsCaja.model.response.TipoDocumentoResponse;
import tdunu.MsCaja.service.TipoDocumentoService;

import java.util.List;

@RestController
@RequestMapping("/api/tipodocumento")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public ResponseEntity<List<TipoDocumentoResponse>> getAllTipoDocumentos() {
        List<TipoDocumentoResponse> tipoDocumentos = tipoDocumentoService.getAllTipoDocumentos();
        return ResponseEntity.ok(tipoDocumentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumentoResponse> getTipoDocumentoById(@PathVariable Integer id) {
        TipoDocumentoResponse tipoDocumento = tipoDocumentoService.getTipoDocumentoById(id);
        return ResponseEntity.ok(tipoDocumento);
    }

    @PostMapping
    public ResponseEntity<TipoDocumentoResponse> createTipoDocumento(@RequestBody TipoDocumentoRequest tipoDocumentoRequest) {
        TipoDocumentoResponse createdTipoDocumento = tipoDocumentoService.createTipoDocumento(tipoDocumentoRequest);
        return ResponseEntity.status(201).body(createdTipoDocumento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDocumentoResponse> updateTipoDocumento(@PathVariable Integer id, @RequestBody TipoDocumentoRequest tipoDocumentoRequest) {
        TipoDocumentoResponse updatedTipoDocumento = tipoDocumentoService.updateTipoDocumento(id, tipoDocumentoRequest);
        return ResponseEntity.ok(updatedTipoDocumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoDocumento(@PathVariable Integer id) {
        tipoDocumentoService.deleteTipoDocumento(id);
        return ResponseEntity.noContent().build();
    }
}