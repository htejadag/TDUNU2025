package tdunu.MsAsistencia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tdunu.MsAsistencia.model.request.AsistenciaAlumnoRequest;
import tdunu.MsAsistencia.model.response.AsistenciaAlumnoResponse;
import tdunu.MsAsistencia.service.AsistenciaAlumnoService;
import tdunu.MsAsistencia.util.ApiRoutes;
import tdunu.MsAsistencia.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.AsistenciaAlumno.BASE)
@Tag(name = "Asistencia Alumno", description = "API de Asistencia de Alumnos")
public class AsistenciaAlumnoController {

    @Autowired
    private AsistenciaAlumnoService asistenciaAlumnoService;

    @GetMapping(ApiRoutes.AsistenciaAlumno.LISTAR)
    @Operation(summary = "Listar todas las asistencias de alumnos")
    public ResponseBase<List<AsistenciaAlumnoResponse>> listar() {
        List<AsistenciaAlumnoResponse> lista = asistenciaAlumnoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.AsistenciaAlumno.OBTENER_POR_ID)
    @Operation(summary = "Obtener asistencia de alumno por ID")
    public ResponseBase<AsistenciaAlumnoResponse> obtenerPorId(@RequestParam Integer id) {
        AsistenciaAlumnoResponse response = asistenciaAlumnoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(ApiRoutes.AsistenciaAlumno.GUARDAR)
    @Operation(summary = "Guardar una nueva asistencia de alumno")
    public ResponseBase<AsistenciaAlumnoResponse> guardar(@RequestBody AsistenciaAlumnoRequest request) {
        AsistenciaAlumnoResponse response = asistenciaAlumnoService.guardar(request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(ApiRoutes.AsistenciaAlumno.ELIMINAR)
    @Operation(summary = "Eliminar una asistencia de alumno")
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        asistenciaAlumnoService.eliminar(id);
        return ResponseBase.ok(null);
    }

    @GetMapping(ApiRoutes.AsistenciaAlumno.BUSCAR_POR_ALUMNO)
    @Operation(summary = "Buscar asistencias por ID de alumno")
    public ResponseBase<List<AsistenciaAlumnoResponse>> buscarPorAlumno(@RequestParam Integer alumnoId) {
        List<AsistenciaAlumnoResponse> lista = asistenciaAlumnoService.buscarPorAlumno(alumnoId);
        return ResponseBase.ok(lista);
    }
}
