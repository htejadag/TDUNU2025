package Ms_Reingresante.Ms_Reingresante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import Ms_Reingresante.Ms_Reingresante.model.request.SolicitudReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.SolicitudReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.service.SolicitudReingresoService;
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.procesoReingresanteBase;

@RestController
@RequestMapping(ApiRoutes.SolicitudReingreso.BASE)
public class SolicitudReingresoController {

    @Autowired
    private SolicitudReingresoService solicitudReingresoService;

    @GetMapping(ApiRoutes.SolicitudReingreso.LISTAR)
    public procesoReingresanteBase<List<SolicitudReingresoResponse>> listar() {
        List<SolicitudReingresoResponse> lista = solicitudReingresoService.listar();
        return procesoReingresanteBase.ok(lista);
    }

    @GetMapping(ApiRoutes.SolicitudReingreso.OBTENER_POR_ID)
    public procesoReingresanteBase<SolicitudReingresoResponse> obtenerPorId(
            @RequestParam(value = "id") Integer id) {
        SolicitudReingresoResponse response = solicitudReingresoService.obtenerPorId(id);
        return procesoReingresanteBase.ok(response);
    }

    @PostMapping(ApiRoutes.SolicitudReingreso.GUARDAR)
    public procesoReingresanteBase<SolicitudReingresoResponse> guardar(
            @RequestBody SolicitudReingresoRequest request) {
        SolicitudReingresoResponse response = solicitudReingresoService.guardar(request);
        return procesoReingresanteBase.ok(response);
    }

    @DeleteMapping(ApiRoutes.SolicitudReingreso.ELIMINAR)
    public procesoReingresanteBase<Void> eliminar(@RequestParam(value = "id") Integer id) {
        solicitudReingresoService.eliminar(id);
        return procesoReingresanteBase.ok("Solicitud eliminada correctamente", null);
    }
}
