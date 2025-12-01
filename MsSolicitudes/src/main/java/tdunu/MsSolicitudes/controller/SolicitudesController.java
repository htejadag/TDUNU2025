package tdunu.MsSolicitudes.controller;

import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tdunu.MsSolicitudes.model.entity.SolicitudesModel;
import tdunu.MsSolicitudes.model.request.DemoRequest;
import tdunu.MsSolicitudes.model.response.DemoResponse;
import tdunu.MsSolicitudes.service.DemoService;
import tdunu.MsSolicitudes.util.ApiRoutes;
import tdunu.MsSolicitudes.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping("/api/procesos-reingreso")
@RequiredArgsConstructor
public class SolicitudesController {


  private final DemoService service;

    @PostMapping
    public ResponseEntity<DemoResponse> crear(@RequestBody DemoRequest request) {
        DemoResponse response = service.crear(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemoResponse> obtenerPorId(@PathVariable Integer id) {
        DemoResponse response = service.obtenerPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DemoResponse>> listarTodos() {
        List<DemoResponse> response = service.listarTodos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemoResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody DemoRequest request) {
        DemoResponse response = service.actualizar(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<List<DemoResponse>> listarPorEstudiante(@PathVariable Integer idEstudiante) {
        List<DemoResponse> response = service.listarPorEstudiante(idEstudiante);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<DemoResponse>> listarPorEstado(@PathVariable String estado) {
        List<DemoResponse> response = service.listarPorEstado(estado);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fase/{fase}")
    public ResponseEntity<List<DemoResponse>> listarPorFase(@PathVariable String fase) {
        List<DemoResponse> response = service.listarPorFase(fase);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<DemoResponse> obtenerPorCodigo(@PathVariable String codigo) {
        DemoResponse response = service.obtenerPorCodigo(codigo);
        return ResponseEntity.ok(response);
    }

}
