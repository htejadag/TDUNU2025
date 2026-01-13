package com.example.MsCuenta.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.MsCuenta.Util.ApiRoutes;
import com.example.MsCuenta.Util.ResponseBase;
import com.example.MsCuenta.model.request.Movimiento.MovimientoUpdateRequest;
import com.example.MsCuenta.model.response.MovimientoResponse;
import com.example.MsCuenta.service.MovimientoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(ApiRoutes.Movimiento.BASE_MOVIMIENTO)
public class MovimientoController {
    
    @Autowired
    MovimientoService movimientoService;

    @GetMapping(value = ApiRoutes.Movimiento.LISTAR_MOVIMIENTO)
    public ResponseBase<List<MovimientoResponse>> listar() {
        List<MovimientoResponse> lista = movimientoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Movimiento.OBTENER_POR_ID_MOVIMIENTO)
    public ResponseBase<MovimientoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        MovimientoResponse response = movimientoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @GetMapping(value = ApiRoutes.Movimiento.LISTAR_MOVIMIENTO_POR_USUARIO)
    public ResponseBase<List<MovimientoResponse>> listarPorUsuario(@RequestParam(value = "id") Integer id) {
        List<MovimientoResponse> response = movimientoService.listarMovimientoPorUsuario(id);
        return ResponseBase.ok(response);
    }


    @PutMapping(value = ApiRoutes.Movimiento.MODIFICAR_MOVIMIENTO)
    public ResponseBase<MovimientoResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody MovimientoUpdateRequest request) {

        MovimientoResponse response = movimientoService.modificar(id, request);
        return ResponseBase.ok(response);
    }

     @DeleteMapping(value = ApiRoutes.Movimiento.ELIMINAR_MOVIMIENTO)
    public ResponseBase<MovimientoResponse> eliminar(@RequestParam(value = "id") Integer id) {
         MovimientoResponse response = movimientoService.eliminar(id);
        return ResponseBase.ok(response);
    }
}
