package tdunu.MsSeguridad.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tdunu.MsSeguridad.model.request.SistemaRequest;
import tdunu.MsSeguridad.model.response.SistemaResponse;
import tdunu.MsSeguridad.service.SistemaService;
import tdunu.MsSeguridad.util.ApiRoutes;
import tdunu.MsSeguridad.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.SISTEMA.BASE)
public class SistemaController {

    @Autowired
    private SistemaService sistemaService;

    @GetMapping(value = ApiRoutes.SISTEMA.LISTAR)
    public ResponseBase<List<SistemaResponse>> listar() {
        List<SistemaResponse> lista = sistemaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.SISTEMA.OBTENER_POR_ID)
    public ResponseBase<SistemaResponse> obtenerPorId(@RequestParam("id") Long id) {
        SistemaResponse response = sistemaService.buscar(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.SISTEMA.GUARDAR)
    public ResponseBase<SistemaResponse> guardar(@RequestBody SistemaRequest request) {
        SistemaResponse response = sistemaService.guardar(request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.SISTEMA.ELIMINAR)
    public ResponseBase<String> eliminar(@RequestParam("id") Long id) {
        sistemaService.eliminar(id);
        return ResponseBase.ok("Eliminado correctamente");
    }
}