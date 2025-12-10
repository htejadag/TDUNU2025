package com.example.Comedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Comedor.model.request.ConsumoRacionRequest;
// import com.example.Comedor.model.request.MenuDiaRequest;
import com.example.Comedor.model.response.ConsumoRacionResponse;
// import com.example.Comedor.model.response.MenuDiaResponse;
// import com.example.Comedor.model.response.MenuSemanaResponse;
import com.example.Comedor.service.ConsumoRacionService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Comedor.BASE_CONSUMO)
public class ConsumoRacionController {

    @Autowired
    private ConsumoRacionService consumoRacionService;

    @GetMapping(value = ApiRoutes.Comedor.LISTAR_CONSUMO)
    public List<ConsumoRacionResponse> listar() {
        return consumoRacionService.listar();
    }

    @GetMapping(value = ApiRoutes.Comedor.OBTENER_POR_ID_CONSUMO)
    public ResponseBase<ConsumoRacionResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        ConsumoRacionResponse response = consumoRacionService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

     @PostMapping(value = ApiRoutes.Comedor.GUARDAR_CONSUMO)
    public ResponseBase<ConsumoRacionResponse> guardar(@RequestBody ConsumoRacionRequest model) {
        ConsumoRacionResponse response = consumoRacionService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Comedor.MODIFICAR_CONSUMO)
    public ResponseBase<ConsumoRacionResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ConsumoRacionRequest request) {
        
        ConsumoRacionResponse response = consumoRacionService.modificar(id, request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Comedor.ELIMINAR_CONSUMO)
    public ConsumoRacionResponse eliminar(@RequestParam(value = "id") Integer id) {
        consumoRacionService.eliminar(id);
        return null;
    }
}