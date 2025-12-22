package Ms_Reingresante.Ms_Reingresante.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Ms_Reingresante.Ms_Reingresante.model.request.MatriculaRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.MatriculaResponse;
import Ms_Reingresante.Ms_Reingresante.service.MatriculaService;
import Ms_Reingresante.Ms_Reingresante.util.MatriculaBase;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
    
    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public MatriculaBase<List<MatriculaResponse>> listar() {
        List<MatriculaResponse> lista = matriculaService.listar();
        return MatriculaBase.ok("Matrículas obtenidas exitosamente", lista);
    }

    @GetMapping("/{id}")
    public MatriculaBase<MatriculaResponse> obtenerPorId(@PathVariable Long id) {
        MatriculaResponse response = matriculaService.obtenerPorId(id);
        return MatriculaBase.ok("Matrícula obtenida exitosamente", response);
    }

    @PostMapping
    public MatriculaBase<MatriculaResponse> guardar(@RequestBody MatriculaRequest request) {
        MatriculaResponse response = matriculaService.guardar(request);
        return MatriculaBase.ok("Matrícula creada exitosamente", response);
    }

    @PutMapping("/{id}")
    public MatriculaBase<MatriculaResponse> actualizar(
            @PathVariable Long id, 
            @RequestBody MatriculaRequest request) {
        MatriculaResponse response = matriculaService.actualizar(id, request);
        return MatriculaBase.ok("Matrícula actualizada exitosamente", response);
    }

    @DeleteMapping("/{id}")
    public MatriculaBase<Void> eliminar(@PathVariable Long id) {
        matriculaService.eliminar(id);
        return MatriculaBase.ok("Matrícula eliminada exitosamente", null);
    }
}