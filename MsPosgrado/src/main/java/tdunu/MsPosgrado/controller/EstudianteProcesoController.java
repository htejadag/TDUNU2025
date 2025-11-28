package tdunu.MsPosgrado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tdunu.MsPosgrado.model.EstudianteProceso;
import tdunu.MsPosgrado.service.EstudianteProcesoService;
// Importamos tu nueva clase de constantes (Asegúrate que ApiConstants esté en este paquete)
import tdunu.MsPosgrado.constant.ApiConstants;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiConstants.ESTUDIANTE_PROCESO_URI) // <--- CAMBIO 1: Ruta base centralizada
public class EstudianteProcesoController {

    @Autowired
    private EstudianteProcesoService service;

    // 1. Listar todos
    @GetMapping
    public ResponseEntity<List<EstudianteProceso>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // 2. Buscar por ID
    @GetMapping(ApiConstants.ID_PARAM) // <--- CAMBIO 2: Usando constante para "/{id}"
    public ResponseEntity<EstudianteProceso> buscarPorId(@PathVariable Long id) {
        Optional<EstudianteProceso> proceso = service.buscarPorId(id);
        // Si existe devuelve OK (200), si no, devuelve Not Found (404)
        return proceso.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Guardar (Crear)
    @PostMapping
    public ResponseEntity<EstudianteProceso> guardar(@RequestBody EstudianteProceso proceso) {
        EstudianteProceso nuevoProceso = service.guardar(proceso);
        return ResponseEntity.ok(nuevoProceso);
    }

    // 4. Actualizar
    @PutMapping(ApiConstants.ID_PARAM) // <--- CAMBIO 3: Usando constante
    public ResponseEntity<EstudianteProceso> actualizar(@PathVariable Long id, @RequestBody EstudianteProceso proceso) {
        // Primero verificamos si existe
        Optional<EstudianteProceso> existente = service.buscarPorId(id);
        
        if (existente.isPresent()) {
            proceso.setId(id); // Aseguramos que el ID sea el de la URL
            return ResponseEntity.ok(service.guardar(proceso));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Eliminar
    @DeleteMapping(ApiConstants.ID_PARAM) // <--- CAMBIO 4: Usando constante
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<EstudianteProceso> existente = service.buscarPorId(id);
        if (existente.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}