package com.example.MsCuenta.controller;

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

import com.example.MsCuenta.Util.ApiRoutes;
import com.example.MsCuenta.Util.ResponseBase;
import com.example.MsCuenta.model.request.CuentaUsuario.CuentaUsuarioRequest;
import com.example.MsCuenta.model.request.CuentaUsuario.CuentaUsuarioUpdateRequest;
import com.example.MsCuenta.model.response.CuentaUsuarioResponse;
import com.example.MsCuenta.service.CuentaUsuarioService;

@RestController
@RequestMapping(ApiRoutes.CuentaUsuario.BASE)
public class CuentaUsuarioController {

    @Autowired
    CuentaUsuarioService cuentaService;


    @GetMapping(value = ApiRoutes.CuentaUsuario.LISTAR)
    public ResponseBase<List<CuentaUsuarioResponse>> listar() {
        List<CuentaUsuarioResponse> lista = cuentaService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.CuentaUsuario.OBTENER_POR_ID)
    public ResponseBase<CuentaUsuarioResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        CuentaUsuarioResponse response = cuentaService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }


     @PostMapping(value = ApiRoutes.CuentaUsuario.GUARDAR)
    public ResponseBase<CuentaUsuarioResponse> guardar(@RequestBody CuentaUsuarioRequest model) {
        CuentaUsuarioResponse response = cuentaService.guardar(model);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.CuentaUsuario.MODIFICAR)
    public ResponseBase<CuentaUsuarioResponse> modificar(
            @RequestParam(value = "id") Integer id,
            @RequestBody CuentaUsuarioUpdateRequest request) {
        
        CuentaUsuarioResponse response = cuentaService.modificar(id, request);
        return ResponseBase.ok(response);
    }


     @DeleteMapping(value = ApiRoutes.CuentaUsuario.ELIMINAR)
    public CuentaUsuarioResponse eliminar(@RequestParam(value = "id") Integer id) {
        cuentaService.eliminar(id);
        return null;
    }

    

   
}