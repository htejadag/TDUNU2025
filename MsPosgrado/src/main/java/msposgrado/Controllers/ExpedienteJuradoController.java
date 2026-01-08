package msposgrado.Controllers;

import msposgrado.Model.ExpedienteJurado;
import msposgrado.Service.ExpedienteJuradoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expediente-jurado")
public class ExpedienteJuradoController {

    private final ExpedienteJuradoService service;

    public ExpedienteJuradoController(ExpedienteJuradoService service) {
        this.service = service;
    }

    @PostMapping
    public ExpedienteJurado crear(@RequestBody ExpedienteJurado ej) {
        return service.crear(ej);
    }

    @GetMapping
    public List<ExpedienteJurado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ExpedienteJurado obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ExpedienteJurado actualizar(@PathVariable Integer id, @RequestBody ExpedienteJurado data) {
        ExpedienteJurado ej = service.obtenerPorId(id);
        if (ej == null) return null;

        ej.setExpediente(data.getExpediente());
        ej.setJurado(data.getJurado());
        ej.setRol(data.getRol());
        ej.setFechaDesignacion(data.getFechaDesignacion());

        return service.crear(ej);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
