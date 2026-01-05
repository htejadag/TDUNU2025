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
import com.pago.service.ClasificadorIngresoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(ApiRoutes.Demo.CLA_INGRESO)
public class ClasificadorIngresoController {
    @Autowired
    @Qualifier("clasificador_ingresoServicio")
    private ClasificadorIngresoService clasificador_ingresoServicio;

    @GetMapping(ApiRoutes.Demo.LISTAR)
    public List<ClasificadorIngresoModel> listar() {
        return clasificador_ingresoServicio.listarClasificadorIngreso();
    }

    @GetMapping(ApiRoutes.Demo.OBTENER_POR_ID)
    public ClasificadorIngresoModel obtener(@RequestParam("id") int id) {
        ClasificadorIngresoModel c = clasificador_ingresoServicio.obtenerClasificadorIngreso(id);
        if (c == null) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        c.setMensaje(Mensajes.ENCONTRADO);
        return c;
    }

    @PostMapping(ApiRoutes.Demo.GUARDAR)
    public ClasificadorIngresoModel crear(@RequestBody ClasificadorIngresoModel req) {
        if (req.getCodigo() == null || req.getCodigo().trim().isEmpty()) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje("codigo es requerido");
            return resp;
        }
        if (req.getNombre() == null || req.getNombre().trim().isEmpty()) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje("nombre es requerido");
            return resp;
        }
        if (req.getUsuario_creacion() == null) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje("usuario_creacion es requerido");
            return resp;
        }
        ClasificadorIngresoModel c = new ClasificadorIngresoModel();
        c.setCodigo(req.getCodigo().trim());
        c.setNombre(req.getNombre().trim());
        c.setUsuario_creacion(req.getUsuario_creacion());
        c.setFecha_creacion(LocalDateTime.now());
        c.setActivo(true);
        c.setEs_eliminado(false);
        c = clasificador_ingresoServicio.registrarClasificadorIngreso(c);
        c.setMensaje(Mensajes.GUARDADO_OK);
        return c;
    }

    @PutMapping(ApiRoutes.Demo.EDITAR)
    public ClasificadorIngresoModel editar(@RequestBody ClasificadorIngresoModel req) {
        if (req.getClasificadoringresoid() == null) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje("clasificadoringresoid es requerido");
            return resp;
        }
        if (req.getUsuario_modificacion() == null) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje("usuario_modificacion es requerido");
            return resp;
        }
        ClasificadorIngresoModel c = clasificador_ingresoServicio.obtenerClasificadorIngreso(req.getClasificadoringresoid());
        if (c == null) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        if (req.getCodigo() != null) c.setCodigo(req.getCodigo().trim());
        if (req.getNombre() != null) c.setNombre(req.getNombre().trim());
        c.setActivo(req.getActivo());
        c.setUsuario_modificacion(req.getUsuario_modificacion());
        c.setFecha_modificacion(LocalDateTime.now());
        c = clasificador_ingresoServicio.actualizarClasificadorIngreso(c);
        c.setMensaje(Mensajes.ACTUALIZADO_OK);
        return c;
    }

    @PutMapping(ApiRoutes.Demo.DESACTIVAR)
    public ClasificadorIngresoModel desactivar(@RequestParam("id") int id,
                                            @RequestParam("id_usuario") int id_usuario) {
        ClasificadorIngresoModel c = clasificador_ingresoServicio.obtenerClasificadorIngreso(id);
        if (c == null) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        c.setUsuario_modificacion(id_usuario);
        c.setFecha_modificacion(LocalDateTime.now());
        c.setActivo(false);
        c = clasificador_ingresoServicio.actualizarClasificadorIngreso(c);
        c.setMensaje(Mensajes.DESACTIVADO_OK);
        return c;
    }

    @DeleteMapping(ApiRoutes.Demo.ELIMINAR)
    public String eliminar(@RequestParam("id") int id) {
        if (clasificador_ingresoServicio.obtenerClasificadorIngreso(id) == null) {
            return Mensajes.NO_ENCONTRADO;
        }
        clasificador_ingresoServicio.eliminarClasificadorIngreso(id);
        return Mensajes.ELIMINADO_OK;
    }

}
