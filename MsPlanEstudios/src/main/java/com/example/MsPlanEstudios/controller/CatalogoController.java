package com.example.MsPlanEstudios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsPlanEstudios.model.request.CatalogoRequest;
import com.example.MsPlanEstudios.model.response.CatalogoResponse;
import com.example.MsPlanEstudios.service.CatalogoService;



@RestController
@RequestMapping("/catalogo")
public class CatalogoController {
    @Autowired
    private CatalogoService service;

    @PostMapping
    public ResponseEntity<CatalogoResponse> crear(
            @RequestBody CatalogoRequest request) {
        return ResponseEntity.ok(service.guardar(request));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<CatalogoResponse>> listar(
            @PathVariable String categoria) {
        return ResponseEntity.ok(service.listarPorCategoria(categoria));
    }
}
