package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Documento;
import Postgrado.postgrado.Service.DocumentoService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @PostMapping
    public Documento crear(@RequestBody Documento documento) {
        return service.crear(documento);
    }

    @GetMapping
    public List<Documento> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Documento obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Documento actualizar(@PathVariable Integer id, @RequestBody Documento data) {
        Documento d = service.obtenerPorId(id);
        if (d == null) return null;

        d.setTipoDocumento(data.getTipoDocumento());
        d.setArchivoRuta(data.getArchivoRuta());
        d.setFechaDocumento(data.getFechaDocumento());

        return service.crear(d);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
