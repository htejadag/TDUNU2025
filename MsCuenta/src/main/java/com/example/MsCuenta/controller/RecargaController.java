package com.example.MsCuenta.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsCuenta.Util.ApiRoutes;
import com.example.MsCuenta.Util.ResponseBase;
import com.example.MsCuenta.model.request.Recarga.RecargaRequest;
import com.example.MsCuenta.model.request.Recarga.RecargaUpdateRequest;
import com.example.MsCuenta.model.response.RecargaResponse;
import com.example.MsCuenta.service.RecargaService;

@RestController
@RequestMapping(ApiRoutes.Recarga.BASE_RECARGA)
public class RecargaController {

    private final RecargaService recargaService;

    public RecargaController(RecargaService recargaService) {

        this.recargaService = recargaService;
    }

    @GetMapping(value = ApiRoutes.Recarga.LISTAR_RECARGA)
    public ResponseBase<List<RecargaResponse>> listar() {
        List<RecargaResponse> lista = recargaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Recarga.OBTENER_POR_ID_RECARGA)
    public ResponseBase<RecargaResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        RecargaResponse response = recargaService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Recarga.GUARDAR_RECARGA)
    public ResponseBase<RecargaResponse> guardar(@RequestBody RecargaRequest model) {
        RecargaResponse response = recargaService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Recarga.MODIFICAR_RECARGA)
    public ResponseBase<RecargaResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody RecargaUpdateRequest request) {

        RecargaResponse response = recargaService.modificar(id, request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Recarga.ELIMINAR_RECARGA)
    public ResponseBase<RecargaResponse> eliminar(@RequestParam(value = "id") Integer id) {
        RecargaResponse response = recargaService.eliminar(id);
        return ResponseBase.ok(response);
    }

}
