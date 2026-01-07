package tdunu.MsAsistencia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdunu.MsAsistencia.model.request.ProgramacionRequest;
import tdunu.MsAsistencia.model.response.ProgramacionResponse;
import tdunu.MsAsistencia.service.ProgramacionService;
import tdunu.MsAsistencia.util.ApiRoutes;
import tdunu.MsAsistencia.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Programacion.BASE)
@Tag(name = "Programación", description = "API de Programación (Cursos, Congresos, Simulacros, etc.)")
public class ProgramacionController {

    @Autowired
    private ProgramacionService programacionService;

    @GetMapping(ApiRoutes.Programacion.LISTAR)
    @Operation(summary = "Listar todas las programaciones")
    public ResponseBase<List<ProgramacionResponse>> listar() {
        List<ProgramacionResponse> lista = programacionService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Programacion.OBTENER_POR_ID)
    @Operation(summary = "Obtener programación por ID")
    public ResponseBase<ProgramacionResponse> obtenerPorId(@RequestParam Integer id) {
        ProgramacionResponse response = programacionService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(ApiRoutes.Programacion.GUARDAR)
    @Operation(summary = "Guardar nueva programación")
    public ResponseBase<ProgramacionResponse> guardar(@RequestBody ProgramacionRequest request) {
        ProgramacionResponse response = programacionService.guardar(request);
        return ResponseBase.ok("Programación creada correctamente", response);
    }

    @PutMapping(ApiRoutes.Programacion.ACTUALIZAR)
    @Operation(summary = "Actualizar programación existente")
    public ResponseBase<ProgramacionResponse> actualizar(@RequestParam Integer id, @RequestBody ProgramacionRequest request) {
        ProgramacionResponse response = programacionService.actualizar(id, request);
        return ResponseBase.ok("Programación actualizada correctamente", response);
    }

    @DeleteMapping(ApiRoutes.Programacion.ELIMINAR)
    @Operation(summary = "Eliminar programación")
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        programacionService.eliminar(id);
        return ResponseBase.ok("Programación eliminada correctamente", null);
    }

    @GetMapping(ApiRoutes.Programacion.BUSCAR_POR_SISTEMA)
    @Operation(summary = "Buscar programaciones por sistema origen")
    public ResponseBase<List<ProgramacionResponse>> buscarPorSistema(@RequestParam String sistemaOrigen) {
        List<ProgramacionResponse> lista = programacionService.buscarPorSistema(sistemaOrigen);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Programacion.BUSCAR_POR_TIPO)
    @Operation(summary = "Buscar programaciones por tipo")
    public ResponseBase<List<ProgramacionResponse>> buscarPorTipo(@RequestParam String tipoProgramacion) {
        List<ProgramacionResponse> lista = programacionService.buscarPorTipo(tipoProgramacion);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Programacion.BUSCAR_POR_CONTEXTO)
    @Operation(summary = "Buscar programaciones por contexto")
    public ResponseBase<List<ProgramacionResponse>> buscarPorContexto(@RequestParam String tipoContexto, @RequestParam Integer contextoId) {
        List<ProgramacionResponse> lista = programacionService.buscarPorContexto(tipoContexto, contextoId);
        return ResponseBase.ok(lista);
    }
}
