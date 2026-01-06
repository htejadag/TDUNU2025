package Postgrado.postgrado.Controllers;

import Postgrado.postgrado.Model.Jurado;
import Postgrado.postgrado.Service.JuradoService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/jurados")
public class JuradoController {

    private final JuradoService service;

    public JuradoController(JuradoService service) {
        this.service = service;
    }

    @PostMapping
    public Jurado crear(@RequestBody Jurado jurado) {
        return service.crear(jurado);
    }

    @GetMapping
    public List<Jurado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Jurado obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Jurado actualizar(@PathVariable Integer id, @RequestBody Jurado data) {
        Jurado j = service.obtenerPorId(id);
        if (j == null) return null;

        j.setNombres(data.getNombres());
        j.setApellidos(data.getApellidos());
        j.setEspecialidad(data.getEspecialidad());

        return service.crear(j);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
