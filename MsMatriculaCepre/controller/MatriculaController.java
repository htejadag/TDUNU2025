package unu.MsMatriculaCepre.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unu.MsMatriculaCepre.model.request.MatriculaRequest;
import unu.MsMatriculaCepre.model.response.MatriculaResponse;
import unu.MsMatriculaCepre.service.MatriculaService;
import unu.MsMatriculaCepre.util.ApiRoutes;
import unu.MsMatriculaCepre.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Matricula.BASE)
@Tag(name = "Matrícula", description = "Gestión de matrículas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping(ApiRoutes.Matricula.LISTAR)
    @Operation(summary = "Listar todas las matrículas")
    public ResponseBase<List<MatriculaResponse>> listar() {
        List<MatriculaResponse> lista = matriculaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Matricula.OBTENER_POR_ID)
    @Operation(summary = "Obtener matrícula por ID")
    public ResponseBase<MatriculaResponse> obtenerPorId(@RequestParam("id") Integer id) {
        MatriculaResponse response = matriculaService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @GetMapping(ApiRoutes.Matricula.LISTAR_POR_ESTUDIANTE)
    @Operation(summary = "Listar matrículas por estudiante")
    public ResponseBase<List<MatriculaResponse>> listarPorEstudiante(
            @RequestParam("estudianteId") Integer estudianteId) {
        List<MatriculaResponse> lista = matriculaService.listarPorEstudiante(estudianteId);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Matricula.LISTAR_POR_GRUPO)
    @Operation(summary = "Listar matrículas por grupo")
    public ResponseBase<List<MatriculaResponse>> listarPorGrupo(
            @RequestParam("grupoId") Integer grupoId) {
        List<MatriculaResponse> lista = matriculaService.listarPorGrupo(grupoId);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Matricula.VERIFICAR)
    @Operation(summary = "Verificar si existe matrícula de estudiante en grupo")
    public ResponseBase<Boolean> verificarMatricula(
            @RequestParam("estudianteId") Integer estudianteId,
            @RequestParam("grupoId") Integer grupoId) {
        boolean existe = matriculaService.existeMatricula(estudianteId, grupoId);
        return ResponseBase.ok(existe);
    }

    @PostMapping(ApiRoutes.Matricula.GUARDAR)
    @Operation(summary = "Registrar nueva matrícula")
    public ResponseBase<MatriculaResponse> guardar(@Valid @RequestBody MatriculaRequest request) {
        MatriculaResponse response = matriculaService.guardar(request);
        return ResponseBase.ok("Matrícula registrada correctamente", response);
    }

    @PutMapping(ApiRoutes.Matricula.ACTUALIZAR)
    @Operation(summary = "Actualizar matrícula existente")
    public ResponseBase<MatriculaResponse> actualizar(
            @RequestParam("id") Integer id,
            @Valid @RequestBody MatriculaRequest request) {
        MatriculaResponse response = matriculaService.actualizar(id, request);
        return ResponseBase.ok("Matrícula actualizada correctamente", response);
    }

    @PutMapping(ApiRoutes.Matricula.CONFIRMAR)
    @Operation(summary = "Confirmar matrícula")
    public ResponseBase<MatriculaResponse> confirmar(@RequestParam("id") Integer id) {
        MatriculaResponse response = matriculaService.confirmar(id);
        return ResponseBase.ok("Matrícula confirmada", response);
    }

    @PutMapping(ApiRoutes.Matricula.ANULAR)
    @Operation(summary = "Anular matrícula")
    public ResponseBase<MatriculaResponse> anular(
            @RequestParam("id") Integer id,
            @RequestParam(value = "motivo", required = false) String motivo) {
        MatriculaResponse response = matriculaService.anular(id, motivo);
        return ResponseBase.ok("Matrícula anulada", response);
    }

    @DeleteMapping(ApiRoutes.Matricula.ELIMINAR)
    @Operation(summary = "Eliminar matrícula")
    public ResponseBase<Void> eliminar(@RequestParam("id") Integer id) {
        matriculaService.eliminar(id);
        return ResponseBase.ok("Matrícula eliminada", null);
    }
}
