package com.example.MsCursos.controller;

import com.example.MsCursos.service.CursoService;
import com.example.MsCursos.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Curso.BASE)
public class CursoController {

    @Autowired
    CursoService cursoService;

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
}
