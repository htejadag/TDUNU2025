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
import com.example.Comedor.model.request.menuPlato.MenuPlatoRequest;
import com.example.Comedor.model.request.menuPlato.MenuPlatoUpdateRequest;
import com.example.Comedor.model.response.MenuPlatoResponse;
import com.example.Comedor.service.MenuPlatoService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.MenuPlato.BASE_MENU_PLATO)
public class MenuPlatoController {

    @Autowired
    private MenuPlatoService menuPlatoService;


    @GetMapping(ApiRoutes.MenuPlato.LISTAR_MENU_PLATO)
    public ResponseBase<List<MenuPlatoResponse>> listar() {
        List<MenuPlatoResponse> lista = menuPlatoService.listar();
        return ResponseBase.ok(lista);
    }


    @GetMapping(ApiRoutes.MenuPlato.OBTENER_POR_ID_MENU_PLATO)
    public ResponseBase<MenuPlatoResponse> obtenerPorId(@RequestParam("id") Integer id) {
        MenuPlatoResponse response = menuPlatoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }


    @PostMapping(ApiRoutes.MenuPlato.GUARDAR_MENU_PLATO)
    public ResponseBase<MenuPlatoResponse> guardar(@RequestBody MenuPlatoRequest request) {
        MenuPlatoResponse response = menuPlatoService.guardar(request);
        return ResponseBase.ok(response);
    }

   
    @PutMapping(ApiRoutes.MenuPlato.MODIFICAR_MENU_PLATO)
    public ResponseBase<MenuPlatoResponse> modificar(
            @RequestParam("id") Integer id,
            @RequestBody MenuPlatoUpdateRequest request) {

        MenuPlatoResponse response = menuPlatoService.modificar(id, request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(ApiRoutes.MenuPlato.ELIMINAR_MENU_PLATO)
    public ResponseBase<MenuPlatoResponse> eliminar(@RequestParam("id") Integer id) {
        
        MenuPlatoResponse response = menuPlatoService.eliminar(id);
        return ResponseBase.ok(response);


    }
    
}
