package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Revision;
import Postgrado.postgrado.Service.RevisionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/revisiones")
public class RevisionController {

    private final RevisionService service;

    public RevisionController(RevisionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Revision> crear(@Valid @RequestBody Revision revision) {
        Revision nuevaRevision = service.crear(revision);
        return new ResponseEntity<>(nuevaRevision, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Revision>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Revision> obtener(@PathVariable Integer id) {
        Revision revision = service.obtenerPorId(id);
        if (revision == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(revision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Revision> actualizar(@PathVariable Integer id, @Valid @RequestBody Revision data) {
        Revision r = service.obtenerPorId(id);
        if (r == null) {
            return ResponseEntity.notFound().build();
        }

        r.setTesis(data.getTesis());
        r.setJurado(data.getJurado());
        r.setTipoRevision(data.getTipoRevision());
        r.setComentario(data.getComentario());
        r.setDictamen(data.getDictamen());
        // fechaRevision eliminada, se controla por auditoría

        Revision actualizada = service.crear(r);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Revision r = service.obtenerPorId(id);
        if (r == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
