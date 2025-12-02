package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Config.ApiRoutes;
import com.example.demo.Entity.AperturaCierreCaja;
import com.example.demo.Service.AperturaCierreCajaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(ApiRoutes.CAJA)
public class AperturaCierreCajaController {
    @Autowired
    private AperturaCierreCajaService ape_cie_cajaService;

    @GetMapping(ApiRoutes.APERTURA_CIERRE_CAJA_R)
    public List<AperturaCierreCaja> listarAperturaCierreCaja() {
        return ape_cie_cajaService.listarApertura();
    }

    @PostMapping(ApiRoutes.APERTURA_CIERRE_CAJA_C)
    public String crearAperturaCierreCaja(
            @RequestParam("fecha") LocalDate fecha,
            @RequestParam("idCajero") int idCajero,
            @RequestParam("montoInicial") double montoInicial,
            @RequestParam("montoFinal") double montoFinal) {

        AperturaCierreCaja apertura = new AperturaCierreCaja();
        apertura.setFecha(fecha);
        apertura.setIdCajero(idCajero);
        apertura.setMontoInicial(montoInicial);
        apertura.setMontoFinal(montoFinal);
        ape_cie_cajaService.guardarApertura(apertura);
        return "Apertura de Caja creada exitosamente para el Cajero: " + idCajero;
    }

    @PutMapping(ApiRoutes.APERTURA_CIERRE_CAJA_U)
    public String actualizarAperturaCierreCaja(
            @RequestParam("id") int id,
            @RequestParam("fecha") LocalDate fecha,
            @RequestParam("idCajero") int idCajero,
            @RequestParam("montoInicial") double montoInicial,
            @RequestParam("montoFinal") double montoFinal) {
        AperturaCierreCaja apertura = ape_cie_cajaService.buscarAperturaPorId(id);
        if (apertura == null) {
            return "No se encontró la apertura con ID: " + id;
        }
        apertura.setFecha(fecha);
        apertura.setIdCajero(idCajero);
        apertura.setMontoInicial(montoInicial);
        apertura.setMontoFinal(montoFinal);
        ape_cie_cajaService.actualizarApertura(apertura);
        return "Apertura Cierre de Caja actualizada correctamente. ID: " + id;
    }

    @DeleteMapping(ApiRoutes.APERTURA_CIERRE_CAJA_D)
    public String eliminarAperturaCierreCaja(@RequestParam("id") int id) {
        ape_cie_cajaService.eliminarApertura(id);
        return "Apertura Cierre de Caja eliminado Correctamente";
    }

}
