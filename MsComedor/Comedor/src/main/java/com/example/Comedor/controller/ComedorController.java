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

import com.example.Comedor.model.request.ComedorRequest;
import com.example.Comedor.model.response.ComedorResponse;
import com.example.Comedor.service.ComedorService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Comedor.BASE)
public class ComedorController {

    @Autowired
    ComedorService comedorService;

    @GetMapping(value = ApiRoutes.Comedor.LISTAR)
    public ResponseBase<List<ComedorResponse>> listar() {
        List<ComedorResponse> lista = comedorService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Comedor.OBTENER_POR_ID)
    public ResponseBase<ComedorResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        ComedorResponse response = comedorService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }


     @PostMapping(value = ApiRoutes.Comedor.GUARDAR)
    public ResponseBase<ComedorResponse> guardar(@RequestBody ComedorRequest model) {
        ComedorResponse response = comedorService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Comedor.MODIFICAR)
    public ResponseBase<ComedorResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody ComedorRequest request) {
        
        ComedorResponse response = comedorService.modificar(id, request);
        return ResponseBase.ok(response);
    }


     @DeleteMapping(value = ApiRoutes.Comedor.ELIMINAR)
    public ComedorResponse eliminar(@RequestParam(value = "id") Integer id) {
        comedorService.eliminar(id);
        return null;
    }






    
}
