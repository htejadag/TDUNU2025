package tdunu.MsPersona.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tdunu.MsPersona.model.request.EstudianteRequest;
import tdunu.MsPersona.model.response.EstudianteResponse;
import tdunu.MsPersona.service.EstudianteService;
import tdunu.MsPersona.util.ApiRoutes;
import tdunu.MsPersona.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Estudiante.BASE)  // Cambiado a Estudiante.BASE
public class EstudianteController {  // Nombre correcto de la clase

    @Autowired
    EstudianteService estudianteService;  // Correcto: servicio de Estudiante

    @GetMapping(value = ApiRoutes.Estudiante.LISTAR)  // Cambiado a Estudiante.LISTAR
    public ResponseBase<List<EstudianteResponse>> listar() {  // Cambiado a EstudianteResponse
        List<EstudianteResponse> lista = estudianteService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Estudiante.OBTENER_POR_ID)  // Cambiado
    public ResponseBase<EstudianteResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {  // Cambiado
        EstudianteResponse response = estudianteService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Estudiante.GUARDAR)  // Cambiado
    public ResponseBase<EstudianteResponse> guardar(@RequestBody EstudianteRequest request) {  // Cambiado
        EstudianteResponse response = estudianteService.guardar(request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Estudiante.ELIMINAR)  // Cambiado
    public ResponseBase<Void> eliminar(@RequestParam(value = "id") Integer id) {  // Cambiado tipo de retorno
        estudianteService.eliminar(id);
        return ResponseBase.ok(null);
    }
}