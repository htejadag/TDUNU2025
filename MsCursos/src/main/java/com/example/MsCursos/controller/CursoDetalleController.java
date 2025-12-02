package com.example.MsCursos.controller;

@RestController
@RequestMapping(ApiRoutes.CursoDetalle.BASE)

public class CursoDetalleController {
    @Autowired
    CursoDetalleService cursoDetalleService;

    @GetMapping(value = ApiRoutes.CursoDetalle.LISTAR)
    public ResponseBase<List<CursoDetalleResponse>> listar() {
        List<CursoDetalleResponse> lista = cursoDetalleService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.CursoDetalle.OBTENER_POR_ID)
    public ResponseBase<CursoDetalleResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        CursoDetalleResponse response = cursoDetalleService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.CursoDetalle.GUARDAR)
    public ResponseBase<CursoDetalleResponse> guardar(@RequestBody CursoDetalleRequest model) {
        CursoDetalleResponse response = cursoDetalleService.guardar(model);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.CursoDetalle.ELIMINAR)
    public CursoDetalleResponse eliminar(@RequestParam(value = "id") Integer id) {
        cursoDetalleService.eliminar(id);
        return null;
    }

}
