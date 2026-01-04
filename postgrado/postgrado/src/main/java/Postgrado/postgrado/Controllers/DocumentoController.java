package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Documento;
import Postgrado.postgrado.Service.DocumentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Documento> crear(@Valid @RequestBody Documento documento) {
        Documento nuevoDocumento = service.crear(documento);
        return new ResponseEntity<>(nuevoDocumento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Documento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> obtener(@PathVariable Integer id) {
        Documento documento = service.obtenerPorId(id);
        if (documento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(documento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> actualizar(@PathVariable Integer id, @Valid @RequestBody Documento data) {
        Documento d = service.obtenerPorId(id);
        if (d == null) {
            return ResponseEntity.notFound().build();
        }

        d.setTipoDocumento(data.getTipoDocumento());
        d.setArchivoRuta(data.getArchivoRuta());
        // Fecha y auditoría se mantienen

        Documento actualizado = service.crear(d);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Documento d = service.obtenerPorId(id);
        if (d == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
