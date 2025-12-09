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

import com.example.Comedor.model.request.MenuDiaRequest;
import com.example.Comedor.model.response.MenuDiaResponse;
import com.example.Comedor.model.response.MenuSemanaResponse;
import com.example.Comedor.service.MenuDiaService;
import com.example.Comedor.util.ApiRoutes;
import com.example.Comedor.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Comedor.BASE_MENU_DIA)
public class MenuDiaController {

    @Autowired
    MenuDiaService menuDiaService;

    @GetMapping(value = ApiRoutes.Comedor.LISTAR_MENU_DIA)
    public ResponseBase<List<MenuDiaResponse>> listar() {
        List<MenuDiaResponse> lista = menuDiaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Comedor.OBTENER_POR_ID_MENU_DIA)
    public ResponseBase<MenuDiaResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        MenuDiaResponse response = menuDiaService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }


     @PostMapping(value = ApiRoutes.Comedor.GUARDAR_MENU_DIA)
    public ResponseBase<MenuDiaResponse> guardar(@RequestBody MenuDiaRequest model) {
        MenuDiaResponse response = menuDiaService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Comedor.MODIFICAR_MENU_DIA)
    public ResponseBase<MenuDiaResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody MenuDiaRequest request) {
        
        MenuDiaResponse response = menuDiaService.modificar(id, request);
        return ResponseBase.ok(response);
    }


     @DeleteMapping(value = ApiRoutes.Comedor.ELIMINAR_MENU_DIA)
    public MenuSemanaResponse eliminar(@RequestParam(value = "id") Integer id) {
        menuDiaService.eliminar(id);
        return null;
    }


    
}
