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

// import com.example.Comedor.model.request.ComedorRequest;
import com.example.Comedor.model.request.MenuSemanaRequest;
// import com.example.Comedor.model.response.ComedorResponse;
import com.example.Comedor.model.response.MenuSemanaResponse;
// import com.example.Comedor.service.ComedorService;
import com.example.Comedor.service.MenuSemanaService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Comedor.BASE)
public class MenuSemanaController {

    @Autowired
    MenuSemanaService menuSemanaService;
    //pruebita pa que funcione el de patrick

    @GetMapping(value = ApiRoutes.Comedor.LISTAR)
    public ResponseBase<List<MenuSemanaResponse>> listar() {
        List<MenuSemanaResponse> lista = menuSemanaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Comedor.OBTENER_POR_ID)
    public ResponseBase<MenuSemanaResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        MenuSemanaResponse response = menuSemanaService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }


     @PostMapping(value = ApiRoutes.Comedor.GUARDAR)
    public ResponseBase<MenuSemanaResponse> guardar(@RequestBody MenuSemanaRequest model) {
        MenuSemanaResponse response = menuSemanaService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Comedor.MODIFICAR)
    public ResponseBase<MenuSemanaResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody MenuSemanaRequest request) {
        
        MenuSemanaResponse response = menuSemanaService.modificar(id, request);
        return ResponseBase.ok(response);
    }


     @DeleteMapping(value = ApiRoutes.Comedor.ELIMINAR)
    public MenuSemanaResponse eliminar(@RequestParam(value = "id") Integer id) {
        menuSemanaService.eliminar(id);
        return null;
    }
  
}
