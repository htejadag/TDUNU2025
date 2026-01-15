package com.pago.controller;

import java.util.List;
<<<<<<< Updated upstream
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pago.model.request.ClasificadorIngresoRequest;
import com.pago.model.response.ClasificadorIngresoResponse;
=======
import org.springframework.beans.factory.annotation.Qualifier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pago.util.ApiRoutes;
import com.pago.util.Mensajes;
import com.pago.model.entity.ClasificadorIngresoModel;
>>>>>>> Stashed changes
import com.pago.service.ClasificadorIngresoService;
import com.pago.util.ApiRoutes;
import com.pago.util.ResponseBase;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.Demo.CLA_INGRESO)
public class ClasificadorIngresoController {
<<<<<<< Updated upstream

    @Autowired
    ClasificadorIngresoService clasificadorIngresoService;

    @GetMapping(value = ApiRoutes.Demo.LISTAR)
    public ResponseBase<List<ClasificadorIngresoResponse>> listar() {
        List<ClasificadorIngresoResponse> resp = clasificadorIngresoService.listar();
        return ResponseBase.ok(resp);
    }

    @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
    public ResponseBase<ClasificadorIngresoResponse> obtenerPorId(@RequestParam("id") Integer id) {
        return ResponseBase.ok(clasificadorIngresoService.obtenerPorId(id));
    }

    @PostMapping(value = ApiRoutes.Demo.GUARDAR)
    public ResponseBase<ClasificadorIngresoResponse> guardar(@RequestBody ClasificadorIngresoRequest request) {
        ClasificadorIngresoResponse resp = clasificadorIngresoService.guardar(request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.EDITAR)
    public ResponseBase<ClasificadorIngresoResponse> editar(@RequestParam("id") Integer id,
            @RequestBody ClasificadorIngresoRequest request) {
        ClasificadorIngresoResponse resp = clasificadorIngresoService.editar(id, request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.DESACTIVAR)
    public ResponseBase<Void> desactivar(@RequestParam("id") Integer id) {
        clasificadorIngresoService.desactivar(id);
        return ResponseBase.ok(null);
    }

    @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam("id") Integer id) {
        clasificadorIngresoService.eliminar(id);
        return ResponseBase.ok(null);
=======
    @Qualifier("clasificadorIngresoServicio")
    private final ClasificadorIngresoService clasificadorIngresoServicio;

    @GetMapping(ApiRoutes.Demo.LISTAR)
    public List<ClasificadorIngresoModel> listar() {
        return clasificadorIngresoServicio.listarClasificadorIngreso();
    }

    @GetMapping(ApiRoutes.Demo.OBTENER_POR_ID)
    public ClasificadorIngresoModel obtener(@RequestParam("id") int id) {
        ClasificadorIngresoModel c = clasificadorIngresoServicio.obtenerClasificadorIngreso(id);
        if (c == null) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        c.setMensaje(Mensajes.ENCONTRADO);
        return c;
    }

    @PostMapping(ApiRoutes.Demo.GUARDAR)
    public ClasificadorIngresoModel crear(@RequestParam("codigo") String codigo,
            @RequestParam("nombre") String nombre,
            @RequestParam("idUsuario") int idUsuario) {
        ClasificadorIngresoModel c = new ClasificadorIngresoModel();
        c.setCodigo(codigo);
        c.setNombre(nombre);
        c.setUsuarioCreacion(idUsuario);
        c.setFechaCreacion(LocalDateTime.now());
        c.setActivo(true);
        c.setEsEliminado(false);
        c = clasificadorIngresoServicio.registrarClasificadorIngreso(c);
        c.setMensaje(Mensajes.GUARDADO_OK);
        return c;
    }

    @PutMapping(ApiRoutes.Demo.EDITAR)
    public ClasificadorIngresoModel editar(@RequestParam("id") int id,
            @RequestParam("codigo") String codigo,
            @RequestParam("nombre") String nombre,
            @RequestParam("idUsuario") int idUsuario) {
        ClasificadorIngresoModel c = clasificadorIngresoServicio.obtenerClasificadorIngreso(id);
        if (c == null) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        c.setCodigo(codigo);
        c.setNombre(nombre);
        c.setUsuarioModificacion(idUsuario);
        c.setFechaModificacion(LocalDateTime.now());
        c = clasificadorIngresoServicio.actualizarClasificadorIngreso(c);
        c.setMensaje(Mensajes.ACTUALIZADO_OK);
        return c;
    }

    @PutMapping(ApiRoutes.Demo.DESACTIVAR)
    public ClasificadorIngresoModel desactivar(@RequestParam("id") int id,
            @RequestParam("idUsuario") int idUsuario) {
        ClasificadorIngresoModel c = clasificadorIngresoServicio.obtenerClasificadorIngreso(id);
        if (c == null) {
            ClasificadorIngresoModel resp = new ClasificadorIngresoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        c.setUsuarioModificacion(idUsuario);
        c.setFechaModificacion(LocalDateTime.now());
        c.setActivo(false);
        c = clasificadorIngresoServicio.actualizarClasificadorIngreso(c);
        c.setMensaje(Mensajes.DESACTIVADO_OK);
        return c;
    }

    @DeleteMapping(ApiRoutes.Demo.ELIMINAR)
    public String eliminar(@RequestParam("id") int id) {
        if (clasificadorIngresoServicio.obtenerClasificadorIngreso(id) == null) {
            return Mensajes.NO_ENCONTRADO;
        }
        clasificadorIngresoServicio.eliminarClasificadorIngreso(id);
        return Mensajes.ELIMINADO_OK;
>>>>>>> Stashed changes
    }
}
