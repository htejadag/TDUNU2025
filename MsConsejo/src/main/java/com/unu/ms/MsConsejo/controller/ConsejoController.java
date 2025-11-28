package com.unu.ms.MsConsejo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.service.ConsejoService;

import com.unu.ms.MsConsejo.util.ApiRoutes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
    

@RestController
@RequestMapping(ApiRoutes.Consejo.BASE)
@Tag(name = "Consejo Controller", description = "Endpoints para gestionar los consejos")
public class ConsejoController {

    @Autowired
    private ConsejoService consejoService;

    @GetMapping(value = ApiRoutes.Consejo.LISTAR)
    public ResponseEntity<List<ConsejoModel>> ListarConsejos() {
        List<ConsejoModel> consejos = (List<ConsejoModel>) consejoService.listar();
        return ResponseEntity.ok(consejos);



    }
    
}
