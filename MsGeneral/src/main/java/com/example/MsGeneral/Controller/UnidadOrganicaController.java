package com.example.MsGeneral.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsGeneral.Model.Request.UnidadOrganicaRequest;
import com.example.MsGeneral.Model.Response.UnidadOrganicaResponse;
import com.example.MsGeneral.Service.UnidadOrganicaServicio;
import com.example.MsGeneral.util.ApiRoutes;
import com.example.MsGeneral.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.BASEGENERAL + ApiRoutes.UnidadOrganica.BASE)
public class UnidadOrganicaController {

    @Autowired
    private UnidadOrganicaServicio unidadOrganicaservice;

    @GetMapping(value = ApiRoutes.UnidadOrganica.LISTAR)
    public ResponseBase<List<UnidadOrganicaResponse>> listar() {
        List<UnidadOrganicaResponse> lista = unidadOrganicaservice.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.UnidadOrganica.OBTENER_POR_ID)
    public ResponseBase<UnidadOrganicaResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        UnidadOrganicaResponse response = unidadOrganicaservice.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.UnidadOrganica.GUARDAR)
    public ResponseBase<UnidadOrganicaResponse> guardar(@RequestBody UnidadOrganicaRequest request) {
        UnidadOrganicaResponse response = unidadOrganicaservice.guardar(request);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.UnidadOrganica.ELIMINAR)
    public UnidadOrganicaResponse eliminar(@RequestParam(value = "id") Integer id) {
        unidadOrganicaservice.eliminar(id);
        return null;
    }

}
