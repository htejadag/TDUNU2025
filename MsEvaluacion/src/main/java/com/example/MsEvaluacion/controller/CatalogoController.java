package com.example.MsEvaluacion.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsEvaluacion.model.entity.CatalogoModel;
import com.example.MsEvaluacion.model.response.CatalogoResponse;
import com.example.MsEvaluacion.services.ICatalogoService;
import com.example.MsEvaluacion.util.Routes; 

@RestController
@RequestMapping(Routes.catalogo.CATALOGO) 
public class CatalogoController {

    @Autowired
    private ICatalogoService catalogoService;

    @GetMapping(Routes.catalogo.LISTAR)
    public ResponseEntity<List<CatalogoResponse>> listar() {
        return ResponseEntity.ok(catalogoService.listarCatalogos());
    }
    

    @GetMapping(Routes.catalogo.OBTENER_POR_CODIGO)
    public ResponseEntity<CatalogoResponse> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(catalogoService.obtenerCatalogoPorCodigo(codigo));
    }


    @PostMapping(Routes.catalogo.GUARDAR)
    public ResponseEntity<CatalogoResponse> guardar(@RequestBody CatalogoModel catalogo) {
        return ResponseEntity.ok(catalogoService.guardarCatalogo(catalogo));
    }
}