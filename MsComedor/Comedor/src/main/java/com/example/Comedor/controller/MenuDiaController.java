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

import com.example.Comedor.model.request.menuDia.MenuDiaRequest;
import com.example.Comedor.model.request.menuDia.MenuDiaUpdateRequest;
import com.example.Comedor.model.response.MenuDiaResponse;
import com.example.Comedor.service.MenuDiaService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.MenuDia.BASE_MENU_DIA)
public class MenuDiaController {

    @Autowired
    MenuDiaService menuDiaService;

    @GetMapping(value = ApiRoutes.MenuDia.LISTAR_MENU_DIA)
    public ResponseBase<List<MenuDiaResponse>> listar() {
        List<MenuDiaResponse> lista = menuDiaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.MenuDia.OBTENER_POR_ID_MENU_DIA)
    public ResponseBase<MenuDiaResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        MenuDiaResponse response = menuDiaService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }


     @PostMapping(value = ApiRoutes.MenuDia.GUARDAR_MENU_DIA)
    public ResponseBase<MenuDiaResponse> guardar(@RequestBody MenuDiaRequest model) {
        MenuDiaResponse response = menuDiaService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.MenuDia.MODIFICAR_MENU_DIA)
    public ResponseBase<MenuDiaResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody MenuDiaUpdateRequest request) {
        
        MenuDiaResponse response = menuDiaService.modificar(id, request);
        return ResponseBase.ok(response);
    }


    @DeleteMapping(value = ApiRoutes.MenuDia.ELIMINAR_MENU_DIA)
    public ResponseBase<MenuDiaResponse>eliminar(@RequestParam(value = "id") Integer id) {
        
        MenuDiaResponse response = menuDiaService.eliminar(id);
        return ResponseBase.ok(response);
    }


    
}
