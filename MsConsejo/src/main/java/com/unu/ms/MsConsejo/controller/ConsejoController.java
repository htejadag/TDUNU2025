package com.unu.ms.MsConsejo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unu.ms.MsConsejo.service.ConsejoService;

import com.unu.ms.MsConsejo.util.ApiRoutes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(ApiRoutes.Consejo.BASE)
@Tag(name = "Consejo Controller", description = "Endpoints para gestionar los consejos")
public class ConsejoController {

    @Autowired
    private ConsejoService consejoService;

    @GetMapping("/")
    public String getMethodName(@RequestParam String param) {
        return "Hello";
    }
    
}
