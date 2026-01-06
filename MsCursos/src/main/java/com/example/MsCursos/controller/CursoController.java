package com.example.mscursos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mscursos.model.request.CursoRequest;
import com.example.mscursos.model.response.CursoResponse;
import com.example.mscursos.service.CursoService;
import com.example.mscursos.util.ApiRoutes;
import com.example.mscursos.util.ResponseBase;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(ApiRoutes.Curso.BASE)
@RequiredArgsConstructor
public class CursoController {

    // @Autowired
    private final CursoService cursoService;

    @GetMapping(value = ApiRoutes.Curso.LISTAR)
    public ResponseBase<List<CursoResponse>> listar() {
        List<CursoResponse> lista = cursoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Curso.OBTENER_POR_ID)
    public ResponseBase<CursoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        CursoResponse response = cursoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Curso.GUARDAR)
    public ResponseBase<CursoResponse> guardar(@RequestBody CursoRequest model) {
        CursoResponse response = cursoService.guardar(model);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Curso.ELIMINAR)
    public CursoResponse eliminar(@RequestParam(value = "id") Integer id) {
        cursoService.eliminar(id);
        return null;
    }

    @GetMapping("/buscar")
    public List<CursoResponse> buscar(
            @RequestParam(required = false) Integer carrera,
            @RequestParam(required = false) Boolean estado) {
        if (carrera != null && estado != null) {
            return cursoService.listarPorCarreraYEstado(carrera, estado);
        }
        if (carrera != null) {
            return cursoService.listarPorCarrera(carrera);
        }
        if (estado != null) {
            return cursoService.listarPorEstado(estado);
        }
        return cursoService.listar();
    }
}
