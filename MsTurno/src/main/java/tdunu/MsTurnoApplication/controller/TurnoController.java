package tdunu.msturno.controller;

import tdunu.msturno.entity.Turno;
import tdunu.msturno.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos") 
public class TurnoController {

    @Autowired
    private TurnoService turnoService; 

    // --- C R E A T E ---
    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        if (turno.getActivo() == null) {
            turno.setActivo(true);
        }
        Turno nuevoTurno = turnoService.guardar(turno);
        return new ResponseEntity<>(nuevoTurno, HttpStatus.CREATED); 
    }

    // --- R E A D (Todos) ---
    @GetMapping
    public List<Turno> obtenerTodos() {
        return turnoService.listarTodos();
    }

    // --- R E A D (Por ID) ---
    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerPorId(@PathVariable Long id) {
        Turno turno = turnoService.obtenerPorId(id);
        if (turno != null) {
            return ResponseEntity.ok(turno);
        }
        return ResponseEntity.notFound().build();
    }

    // --- U P D A T E ---
    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizarTurno(@PathVariable Long id, @RequestBody Turno detallesTurno) {
        Turno turnoExistente = turnoService.obtenerPorId(id);
        
        if (turnoExistente != null) {
            turnoExistente.setDescripcion(detallesTurno.getDescripcion());
            turnoExistente.setHoraApertura(detallesTurno.getHoraApertura());
            turnoExistente.setHoraCierre(detallesTurno.getHoraCierre());
            if (detallesTurno.getActivo() != null) {
                turnoExistente.setActivo(detallesTurno.getActivo());
            }
            return ResponseEntity.ok(turnoService.guardar(turnoExistente));
        }
        return ResponseEntity.notFound().build();
    }

    // --- D E L E T E LÓGICO ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        Turno turno = turnoService.obtenerPorId(id);
        if (turno != null) {
            turnoService.eliminar(id); 
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.notFound().build(); 
    }
}