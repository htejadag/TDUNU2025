package com.example.Comedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Comedor.model.request.plato.PlatoRequest;
import com.example.Comedor.model.request.plato.PlatoUpdateRequest;
import com.example.Comedor.model.response.PlatoResponse;
import com.example.Comedor.service.PlatoService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Comedor.BASE_PLATO)
public class PlatoController {

    @Autowired
    PlatoService platoService;

    @GetMapping(value = ApiRoutes.Comedor.LISTAR_PLATO)
    public ResponseBase<List<PlatoResponse>> listar() {
        List<PlatoResponse> lista = platoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Comedor.OBTENER_POR_ID_PLATO)
    public ResponseBase<PlatoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        PlatoResponse response = platoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }


     @PostMapping(value = ApiRoutes.Comedor.GUARDAR_PLATO)
    public ResponseBase<PlatoResponse> guardar(@RequestBody PlatoRequest model) {
        PlatoResponse response = platoService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Comedor.MODIFICAR_PLATO)
    public ResponseBase<PlatoResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody PlatoUpdateRequest request) {
        
        PlatoResponse response = platoService.modificar(id, request);
        return ResponseBase.ok(response);
    }


     @DeleteMapping(value = ApiRoutes.Comedor.ELIMINAR_PLATO)
    public ResponseBase<PlatoResponse> eliminar(@RequestParam(value = "id") Integer id) {
        
        PlatoResponse response=platoService.eliminar(id);
        return ResponseBase.ok(response);
    }


    
}
