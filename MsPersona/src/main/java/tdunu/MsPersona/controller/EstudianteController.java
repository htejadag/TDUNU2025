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
@RequestMapping(ApiRoutes.Estudiante.BASE)  
public class EstudianteController {  

    @Autowired
    EstudianteService estudianteService;  

    @GetMapping(value = ApiRoutes.Estudiante.LISTAR)  
    public ResponseBase<List<EstudianteResponse>> listar() { 
        List<EstudianteResponse> lista = estudianteService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Estudiante.OBTENER_POR_ID)  
    public ResponseBase<EstudianteResponse> obtenerPorId(@RequestParam(value = "id") Integer id) { 
        EstudianteResponse response = estudianteService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Estudiante.GUARDAR)  
    public ResponseBase<EstudianteResponse> guardar(@RequestBody EstudianteRequest request) {  
        EstudianteResponse response = estudianteService.guardar(request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Estudiante.ELIMINAR) 
    public ResponseBase<Void> eliminar(@RequestParam(value = "id") Integer id) {  
        estudianteService.eliminar(id);
        return ResponseBase.ok(null);
    }
}