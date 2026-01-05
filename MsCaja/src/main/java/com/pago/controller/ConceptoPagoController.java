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
import com.pago.model.entity.ConceptoPagoModel;
import com.pago.service.ConceptoPagoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(ApiRoutes.Demo.CONCEPTO_PAGO)
public class ConceptoPagoController {
    @Autowired
    @Qualifier("concepto_pagoServicio")
    private ConceptoPagoService concepto_pagoServicio;

    @GetMapping(ApiRoutes.Demo.LISTAR)
    public List<ConceptoPagoModel> listar() {
        return concepto_pagoServicio.listarConceptoPago();
    }

    @GetMapping(ApiRoutes.Demo.OBTENER_POR_ID)
    public ConceptoPagoModel obtener(@RequestParam("id") int id) {
        ConceptoPagoModel c = concepto_pagoServicio.obtenerConceptoPago(id);
        if (c == null) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        c.setMensaje(Mensajes.ENCONTRADO);
        return c;
    }

    @PostMapping(ApiRoutes.Demo.GUARDAR)
    public ConceptoPagoModel crear(@RequestBody ConceptoPagoModel req) {
        if (req.getClasificadoringresoid() == null) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje("clasificadoringresoid es requerido");
            return resp;
        }
        if (req.getNombre_concepto() == null || req.getNombre_concepto().trim().isEmpty()) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje("nombre_concepto es requerido");
            return resp;
        }
        if (req.getPrecio_base() <= 0) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje("precio_base debe ser mayor a 0");
            return resp;
        }
        ConceptoPagoModel c = new ConceptoPagoModel();
        c.setClasificadoringresoid(req.getClasificadoringresoid());
        c.setNombre_concepto(req.getNombre_concepto().trim());
        c.setPrecio_base(req.getPrecio_base());
        c.setUsuario_creacion(req.getUsuario_creacion());
        c.setFecha_creacion(LocalDateTime.now());
        c.setActivo(true);
        c.setEs_eliminado(false);
        c = concepto_pagoServicio.registrarConceptoPago(c);
        c.setMensaje(Mensajes.GUARDADO_OK);
        return c;
    }

    @PutMapping(ApiRoutes.Demo.EDITAR)
    public ConceptoPagoModel editar(@RequestBody ConceptoPagoModel req) {
        if (req.getConceptopagoid() == null) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje("conceptopagoid es requerido");
            return resp;
        }
        ConceptoPagoModel c = concepto_pagoServicio.obtenerConceptoPago(req.getConceptopagoid());
        if (c == null) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        if (req.getClasificadoringresoid() != null) {
            c.setClasificadoringresoid(req.getClasificadoringresoid());
        }
        if (req.getNombre_concepto() != null && !req.getNombre_concepto().trim().isEmpty()) {
            c.setNombre_concepto(req.getNombre_concepto().trim());
        }
        if (req.getPrecio_base() > 0) {
            c.setPrecio_base(req.getPrecio_base());
        }
        c.setActivo(req.getActivo());
        c.setUsuario_modificacion(req.getUsuario_modificacion());
        c.setFecha_modificacion(LocalDateTime.now());
        c = concepto_pagoServicio.actualizarConceptoPago(c);
        c.setMensaje(Mensajes.ACTUALIZADO_OK);
        return c;
    }

    @PutMapping(ApiRoutes.Demo.DESACTIVAR)
    public ConceptoPagoModel desactivar(@RequestParam("id") int id,
                                        @RequestParam("id_usuario") int id_usuario) {
        ConceptoPagoModel c = concepto_pagoServicio.obtenerConceptoPago(id);
        if (c == null) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        c.setUsuario_modificacion(id_usuario);
        c.setFecha_modificacion(LocalDateTime.now());
        c.setActivo(false);
        c = concepto_pagoServicio.actualizarConceptoPago(c);
        c.setMensaje(Mensajes.DESACTIVADO_OK);
        return c;
    }

    @DeleteMapping(ApiRoutes.Demo.ELIMINAR)
    public String eliminar(@RequestParam("id") int id) {
        if (concepto_pagoServicio.obtenerConceptoPago(id) == null) {
            return Mensajes.NO_ENCONTRADO;
        }
        concepto_pagoServicio.eliminarConceptoPago(id);
        return Mensajes.ELIMINADO_OK;
    }

}
