package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Revision;
import Postgrado.postgrado.Service.RevisionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/revisiones")
public class RevisionController {

    private final RevisionService service;

    public RevisionController(RevisionService service) {
        this.service = service;
    }

    @PostMapping
    public Revision crear(@RequestBody Revision revision) {
        return service.crear(revision);
    }

    @GetMapping
    public List<Revision> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Revision obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Revision actualizar(@PathVariable Integer id, @RequestBody Revision data) {
        Revision r = service.obtenerPorId(id);
        if (r == null) return null;

        r.setTesis(data.getTesis());
        r.setJurado(data.getJurado());
        r.setTipoRevision(data.getTipoRevision());
        r.setComentario(data.getComentario());
        r.setDictamen(data.getDictamen());
        r.setFechaRevision(data.getFechaRevision());

        return service.crear(r);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}