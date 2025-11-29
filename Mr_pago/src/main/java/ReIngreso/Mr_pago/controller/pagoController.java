package ReIngreso.Mr_pago.controller;

import org.springframework.web.bind.annotation.RestController;

import ReIngreso.Mr_pago.model.pagoModel;
import ReIngreso.Mr_pago.service.pagoService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/pago")
public class pagoController {
 
    @Autowired
    pagoService pagoService;


    @GetMapping (value = "Listar")
    public List<pagoModel> get() {
        return pagoService.getAll();
    }

}