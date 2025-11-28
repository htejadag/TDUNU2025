package com.example.demo.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Config.ApiRoutes;
import com.example.demo.Entity.AperturaCierreCaja;
import com.example.demo.Service.AperturaCierreCajaService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(ApiRoutes.CAJA)
public class AperturaCierreCajaController {
    @Autowired
    private AperturaCierreCajaService aperturaCierreCajaService;

    @PostMapping(ApiRoutes.APERTURA_CIERRE_CAJA_C)
    public String crearAperturaCierreCaja(
            @RequestParam("idCajero") int idCajero,
            @RequestParam("montoInicial") double montoInicial,
            @RequestParam("fecha") String fechaStr) {

        AperturaCierreCaja apertura = new AperturaCierreCaja();
        apertura.setIdCajero(idCajero);
        apertura.setMontoInicial(BigDecimal.valueOf(montoInicial));
        try {
            apertura.setFecha(LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE));
        } catch (DateTimeParseException e) {
            return "Error: Formato de fecha inválido. Por favor, use el formato YYYY-MM-DD.";
        }
        apertura.setMontoFinal(null);
        aperturaCierreCajaService.guardarApertura(apertura);
        return "Apertura de Caja creada exitosamente para el Cajero: " + idCajero;
    }

}
