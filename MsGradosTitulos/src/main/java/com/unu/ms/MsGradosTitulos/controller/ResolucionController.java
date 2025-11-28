package com.unu.ms.MsGradosTitulos.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.unu.ms.MsGradosTitulos.util.ApiRoutes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import com.unu.ms.MsGradosTitulos.service.ResolucionService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(ApiRoutes.GradosTitulos.BASE)
@Tag(name = "Resolucion Controller", description = "Endpoints para gestionar las resoluciones")
public class ResolucionController {

    @Autowired
    private ResolucionService resolucionService;

    @GetMapping("/")
    public String getMethodName() {
        return "Hello";
    }
    
}