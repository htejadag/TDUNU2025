package Ms_Reingresante.Ms_Reingresante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import Ms_Reingresante.Ms_Reingresante.message.ProductorMessagePublish;
import Ms_Reingresante.Ms_Reingresante.model.request.SolicitudReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.SolicitudReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.service.SolicitudReingresoService;
import Ms_Reingresante.Ms_Reingresante.util.ApiRoutes;
import Ms_Reingresante.Ms_Reingresante.util.SolicitudReingresoBase;

@RestController
@RequestMapping(ApiRoutes.SolicitudReingreso.BASE)
public class SolicitudReingresoController {

    @Autowired
    private SolicitudReingresoService solicitudReingresoService;

    @Autowired
    ProductorMessagePublish messageEvent;

    @GetMapping(ApiRoutes.SolicitudReingreso.LISTAR)
    public SolicitudReingresoBase<List<SolicitudReingresoResponse>> listar() {
        List<SolicitudReingresoResponse> lista = solicitudReingresoService.listar();
        return SolicitudReingresoBase.ok(lista);
    }

    @GetMapping(ApiRoutes.SolicitudReingreso.OBTENER_POR_ID)
    public SolicitudReingresoBase<SolicitudReingresoResponse> obtenerPorId(
            @RequestParam(value = "id") Integer id) {
        SolicitudReingresoResponse response = solicitudReingresoService.obtenerPorId(id);
        return SolicitudReingresoBase.ok(response);
    }

    @PostMapping(ApiRoutes.SolicitudReingreso.GUARDAR)
       public SolicitudReingresoBase<SolicitudReingresoResponse> guardar(@RequestBody SolicitudReingresoRequest model)
        throws JsonProcessingException {

    SolicitudReingresoResponse response = solicitudReingresoService.guardar(model);

    messageEvent.sendSolicitudReingresoEvent(response);

    return SolicitudReingresoBase.ok(response);
 }


    @DeleteMapping(ApiRoutes.SolicitudReingreso.ELIMINAR)
    public SolicitudReingresoBase<Void> eliminar(@RequestParam(value = "id") Integer id) {
        solicitudReingresoService.eliminar(id);
        return SolicitudReingresoBase.ok("Solicitud eliminada correctamente", null);
    }
}
