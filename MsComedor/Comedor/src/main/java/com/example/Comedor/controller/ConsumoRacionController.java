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
import com.example.Comedor.model.request.consumoRacion.ConsumoRacionRequest;
import com.example.Comedor.model.request.consumoRacion.ConsumoRacionUpdateRequest;
import com.example.Comedor.model.response.ConsumoRacionResponse;
import com.example.Comedor.service.ConsumoRacionService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.ConsumoRacion.BASE_CONSUMO)
public class ConsumoRacionController {

    @Autowired
    private ConsumoRacionService consumoRacionService;


    @GetMapping(value = ApiRoutes.ConsumoRacion.LISTAR_CONSUMO)
    public List<ConsumoRacionResponse> listar() {
        return consumoRacionService.listar();
    }

    @GetMapping(value = ApiRoutes.ConsumoRacion.OBTENER_POR_ID_CONSUMO)
    public ResponseBase<ConsumoRacionResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        ConsumoRacionResponse response = consumoRacionService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

     @PostMapping(value = ApiRoutes.ConsumoRacion.GUARDAR_CONSUMO)
    public ResponseBase<ConsumoRacionResponse> guardar(@RequestBody ConsumoRacionRequest model) {
        ConsumoRacionResponse response = consumoRacionService.guardar(model);

        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.ConsumoRacion.MODIFICAR_CONSUMO)
    public ResponseBase<ConsumoRacionResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ConsumoRacionUpdateRequest request) {
        
        ConsumoRacionResponse response = consumoRacionService.modificar(id, request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.ConsumoRacion.ELIMINAR_CONSUMO)
    public ResponseBase<ConsumoRacionResponse> eliminar(@RequestParam(value = "id") Integer id) {
        ConsumoRacionResponse response = consumoRacionService.eliminar(id);
        return ResponseBase.ok(response);
       
    }
}