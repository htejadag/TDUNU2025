package tdunu.MsPersona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdunu.MsPersona.model.request.DocenteRequest;
import tdunu.MsPersona.model.response.DocenteResponse;
import tdunu.MsPersona.service.DocenteService;
import tdunu.MsPersona.util.ApiRoutes;
import tdunu.MsPersona.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Docente.BASE)
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping(value = ApiRoutes.Docente.LISTAR)
    public ResponseBase<List<DocenteResponse>> listar() {
        List<DocenteResponse> lista = docenteService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Docente.OBTENER_POR_ID)
    public ResponseBase<DocenteResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        DocenteResponse response = docenteService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Docente.GUARDAR)
    public ResponseBase<DocenteResponse> guardar(@RequestBody DocenteRequest request) {
        DocenteResponse response = docenteService.guardar(request);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Docente.OBTENER_POR_ID)
    public ResponseBase<DocenteResponse> actualizar(
            @RequestParam(value = "id") Integer id, 
            @RequestBody DocenteRequest request) {
        DocenteResponse response = docenteService.actualizar(id, request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Docente.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam(value = "id") Integer id) {
        docenteService.eliminar(id);
        return ResponseBase.ok(null);
    }
}