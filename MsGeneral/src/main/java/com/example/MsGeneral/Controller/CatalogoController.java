package com.example.MsGeneral.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsGeneral.Model.Request.CatalogoRequest;
import com.example.MsGeneral.Model.Response.CatalogoResponse;
import com.example.MsGeneral.Service.CatalogoService;
import com.example.MsGeneral.util.ApiRoutes;
import com.example.MsGeneral.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.BASEGENERAL + ApiRoutes.Catalogo.BASE)
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping(value = ApiRoutes.Catalogo.LISTAR)
    public ResponseBase<List<CatalogoResponse>> listar() {
        List<CatalogoResponse> lista = catalogoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Catalogo.OBTENER_POR_ID)
    public ResponseBase<CatalogoResponse> obtenerPorId(@PathVariable("id") String id) {
        CatalogoResponse response = catalogoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Catalogo.CREAR)
    public ResponseBase<CatalogoResponse> crear(@RequestBody CatalogoRequest request) {
        CatalogoResponse response = catalogoService.guardar(request);
        return ResponseBase.ok(response);
    }

    @PutMapping(value = ApiRoutes.Catalogo.ACTUALIZAR)
    public ResponseBase<CatalogoResponse> actualizar (@PathVariable("id") String id ,@RequestBody CatalogoRequest request ) {
        CatalogoResponse response = catalogoService.actualizar(id, request);
        return ResponseBase.ok(response);
    }


    @DeleteMapping(value = ApiRoutes.Catalogo.ELIMINAR)
    public CatalogoResponse eliminar(@PathVariable("id") String id) {
        catalogoService.eliminar(id);
        return null;
    }

    

}
