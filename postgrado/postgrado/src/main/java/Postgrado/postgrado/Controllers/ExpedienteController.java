package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Expediente;
import Postgrado.postgrado.Service.ExpedienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expedientes")
public class ExpedienteController {

    private final ExpedienteService service;

    public ExpedienteController(ExpedienteService service) {
        this.service = service;
    }

    @PostMapping
    public Expediente crear(@RequestBody Expediente expediente) {
        return service.crear(expediente);
    }

    @GetMapping
    public List<Expediente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Expediente obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Expediente actualizar(@PathVariable Integer id, @RequestBody Expediente data) {
        Expediente exp = service.obtenerPorId(id);
        if (exp == null) return null;

        exp.setIdEstudiante(data.getIdEstudiante());
        exp.setEstadoExpediente(data.getEstadoExpediente());
        exp.setFechaApertura(data.getFechaApertura());
        exp.setFechaCierre(data.getFechaCierre());
        exp.setObservaciones(data.getObservaciones());
        exp.setAsesor(data.getAsesor());

        return service.crear(exp);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
