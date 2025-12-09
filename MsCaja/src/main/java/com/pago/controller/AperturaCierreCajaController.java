package com.pago.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pago.util.ApiRoutes;
import com.pago.util.Mensajes;
import com.pago.model.entity.AperturaCierreCajaModel;
import com.pago.service.AperturaCierreCajaService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(ApiRoutes.Demo.APE_CIE_CAJA)
public class AperturaCierreCajaController {
    @Autowired
    @Qualifier("ape_cie_cajaServicio")
    private AperturaCierreCajaService ape_cie_cajaServicio;

    @GetMapping(ApiRoutes.Demo.LISTAR)
    public List<AperturaCierreCajaModel> listar() {
        return ape_cie_cajaServicio.listarApeCieCaja();
    }

    @GetMapping(ApiRoutes.Demo.OBTENER_POR_ID)
    public AperturaCierreCajaModel obtener(@RequestParam("id") int id) {
        AperturaCierreCajaModel a = ape_cie_cajaServicio.obtenerApeCieCaja(id);
        if (a == null) {
            AperturaCierreCajaModel resp = new AperturaCierreCajaModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        a.setMensaje(Mensajes.ENCONTRADO);
        return a;
    }

    @PostMapping(ApiRoutes.Demo.GUARDAR)
    public AperturaCierreCajaModel crear(@RequestParam("id_usuario") int id_usuario) {
        AperturaCierreCajaModel a = new AperturaCierreCajaModel();
        a.setFecha_apertura(LocalDateTime.now());
        a.setUsuario_creacion(id_usuario);
        a.setFecha_creacion(LocalDateTime.now());
        a.setActivo(true);
        a.setEs_eliminado(false);
        a = ape_cie_cajaServicio.registrarApeCieCaja(a);
        a.setMensaje(Mensajes.GUARDADO_OK);
        return a;
    }
/*
    @PutMapping(ApiRoutes.Demo.EDITAR)
    public String aditar(@RequestParam("id") int id, @RequestBody AperturaCierreCajaModel apeCieCaja) {
        if(ape_cie_cajaServicio.obtenerApeCieCaja(id) == null){
            return "No se encontró una AperturaCierreCaja con ese ID proposcionado";
        }
        ape_cie_cajaServicio.actualizarApeCieCaja(apeCieCaja);
        return "Encontrado exitosamente";
    }
*/
    @PutMapping(ApiRoutes.Demo.DESACTIVAR)
    public AperturaCierreCajaModel desactivar(@RequestParam("id") int id,
                                              @RequestParam("id_usuario") int id_usuario) {
        AperturaCierreCajaModel a = ape_cie_cajaServicio.obtenerApeCieCaja(id);
        if(a == null){
            AperturaCierreCajaModel resp = new AperturaCierreCajaModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        a.setFecha_cierre(LocalDateTime.now());
        a.setUsuario_modificacion(id_usuario);
        a.setFecha_modificacion(LocalDateTime.now());
        a.setActivo(false);
        a = ape_cie_cajaServicio.actualizarApeCieCaja(a);
        a.setMensaje(Mensajes.DESACTIVADO_OK);
        return a;
    }
    
    @DeleteMapping(ApiRoutes.Demo.ELIMINAR)
    public String eliminar(@RequestParam("id") int id) {
        if (ape_cie_cajaServicio.obtenerApeCieCaja(id) == null) {
            return Mensajes.NO_ENCONTRADO;
        }
        ape_cie_cajaServicio.eliminarApeCieCaja(id);
        return Mensajes.ELIMINADO_OK;
    }
    
}
