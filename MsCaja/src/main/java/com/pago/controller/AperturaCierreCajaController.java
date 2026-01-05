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
import org.springframework.web.bind.annotation.RequestBody;

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
    public AperturaCierreCajaModel crear(@RequestBody AperturaCierreCajaModel apeCieCaja) {
        if (apeCieCaja.getUsuario_creacion() == null) {
            AperturaCierreCajaModel resp = new AperturaCierreCajaModel();
            resp.setMensaje("usuario_creacion es requerido");
            return resp;
        }
        AperturaCierreCajaModel a = new AperturaCierreCajaModel();
        a.setFecha_apertura(LocalDateTime.now());
        a.setUsuario_creacion(apeCieCaja.getUsuario_creacion());
        a.setFecha_creacion(LocalDateTime.now());
        a.setActivo(true);
        a.setEs_eliminado(false);
        a = ape_cie_cajaServicio.registrarApeCieCaja(a);
        a.setMensaje(Mensajes.GUARDADO_OK);
        return a;
    }

    @PutMapping(ApiRoutes.Demo.EDITAR)
    public AperturaCierreCajaModel editar(@RequestBody AperturaCierreCajaModel apeCieCaja) {
        if (apeCieCaja.getAperturacierrecajaid() == null) {
            AperturaCierreCajaModel resp = new AperturaCierreCajaModel();
            resp.setMensaje("aperturacierrecajaid es requerido");
            return resp;
        }
        if (apeCieCaja.getUsuario_modificacion() == null) {
            AperturaCierreCajaModel resp = new AperturaCierreCajaModel();
            resp.setMensaje("usuario_modificacion es requerido");
            return resp;
        }
        AperturaCierreCajaModel a = ape_cie_cajaServicio.obtenerApeCieCaja(apeCieCaja.getAperturacierrecajaid());
        if (a == null) {
            AperturaCierreCajaModel resp = new AperturaCierreCajaModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        if (apeCieCaja.getFecha_apertura() != null) a.setFecha_apertura(apeCieCaja.getFecha_apertura());
        if (apeCieCaja.getFecha_cierre() != null) a.setFecha_cierre(apeCieCaja.getFecha_cierre());
        a.setActivo(apeCieCaja.getActivo());
        a.setUsuario_modificacion(apeCieCaja.getUsuario_modificacion());
        a.setFecha_modificacion(LocalDateTime.now());
        a = ape_cie_cajaServicio.actualizarApeCieCaja(a);
        a.setMensaje(Mensajes.ACTUALIZADO_OK);
        return a;
    }
    
    @PutMapping(ApiRoutes.Demo.DESACTIVAR)
    public AperturaCierreCajaModel desactivar(@RequestParam("id") int id,
                                            @RequestParam("id_usuario") int id_usuario) {
        AperturaCierreCajaModel a = ape_cie_cajaServicio.obtenerApeCieCaja(id);
        if (a == null) {
            AperturaCierreCajaModel resp = new AperturaCierreCajaModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        LocalDateTime now = LocalDateTime.now();
        a.setFecha_cierre(now);
        a.setUsuario_modificacion(id_usuario);
        a.setFecha_modificacion(now);
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
