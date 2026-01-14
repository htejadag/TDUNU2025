package com.example.Comedor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Comedor.model.request.turno.TurnoRequest;
import com.example.Comedor.model.request.turno.TurnoUpdateRequest;
import com.example.Comedor.model.response.TurnoResponse;
import com.example.Comedor.service.TurnoService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Turno.BASE_TURNO)
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {

        this.turnoService = turnoService;
    }

    @GetMapping(value = ApiRoutes.Turno.LISTAR_TURNO)
    public ResponseBase<List<TurnoResponse>> listar() {
        List<TurnoResponse> lista = turnoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Turno.OBTENER_POR_ID_TURNO)
    public ResponseBase<TurnoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        TurnoResponse response = turnoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Turno.GUARDAR_TURNO)
    public ResponseBase<TurnoResponse> guardar(@RequestBody TurnoRequest model) {
        TurnoResponse response = turnoService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Turno.MODIFICAR_TURNO)
    public ResponseBase<TurnoResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody TurnoUpdateRequest request) {

        TurnoResponse response = turnoService.modificar(id, request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Turno.ELIMINAR_TURNO)
    public ResponseBase<TurnoResponse> eliminar(@RequestParam(value = "id") Integer id) {

        TurnoResponse response = turnoService.eliminar(id);
        return ResponseBase.ok(response);
    }

}
