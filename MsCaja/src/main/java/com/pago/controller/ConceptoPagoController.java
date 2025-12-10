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
import com.pago.model.entity.ClasificadorIngresoModel;
import com.pago.model.entity.ConceptoPagoModel;
import com.pago.service.ConceptoPagoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
    public ConceptoPagoModel crear(@RequestParam("clasificadorid") int clasificadorid,
                                   @RequestParam("nombre_concepto") String nombre_concepto,
                                   @RequestParam("precio_base") float precio_base,
                                   @RequestParam("id_usuario") int id_usuario) {
        ConceptoPagoModel c = new ConceptoPagoModel();
        ClasificadorIngresoModel clasificador = new ClasificadorIngresoModel();
        clasificador.setClasificador_ingresoid(clasificadorid);
        c.setClasificador_ingreso(clasificador);
        c.setNombre_concepto(nombre_concepto);
        c.setPrecio_base(precio_base);
        c.setUsuario_creacion(id_usuario);
        c.setFecha_creacion(LocalDateTime.now());
        c.setActivo(true);
        c.setEs_eliminado(false);
        c = concepto_pagoServicio.registrarConceptoPago(c);
        c.setMensaje(Mensajes.GUARDADO_OK);
        return c;
    }

    @PutMapping(ApiRoutes.Demo.EDITAR)
    public ConceptoPagoModel editar(@RequestParam("id") int id,
                                    @RequestParam("clasificadorid") int clasificadorid,
                                    @RequestParam("nombre_concepto") String nombre_concepto,
                                    @RequestParam("precio_base") float precio_base,
                                    @RequestParam("id_usuario") int id_usuario) {
        ConceptoPagoModel c = concepto_pagoServicio.obtenerConceptoPago(id);
        if (c == null) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        ClasificadorIngresoModel clasificador = new ClasificadorIngresoModel();
        clasificador.setClasificador_ingresoid(clasificadorid);
        c.setClasificador_ingreso(clasificador);
        c.setNombre_concepto(nombre_concepto);
        c.setPrecio_base(precio_base);
        c.setUsuario_modificacion(id_usuario);
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
