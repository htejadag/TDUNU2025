package com.pago.controller;

import java.util.List;
<<<<<<< Updated upstream
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pago.model.request.ConceptoPagoRequest;
import com.pago.model.response.ConceptoPagoResponse;
=======
import org.springframework.beans.factory.annotation.Qualifier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pago.util.ApiRoutes;
import com.pago.util.Mensajes;
import com.pago.model.entity.ClasificadorIngresoModel;
import com.pago.model.entity.ConceptoPagoModel;
>>>>>>> Stashed changes
import com.pago.service.ConceptoPagoService;
import com.pago.util.ApiRoutes;
import com.pago.util.ResponseBase;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.Demo.CONCEPTO_PAGO)
public class ConceptoPagoController {
<<<<<<< Updated upstream

    @Autowired
    ConceptoPagoService conceptoPagoService;

    @GetMapping(value = ApiRoutes.Demo.LISTAR)
    public ResponseBase<List<ConceptoPagoResponse>> listar() {
        List<ConceptoPagoResponse> resp = conceptoPagoService.listar();
        return ResponseBase.ok(resp);
    }

    @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
    public ResponseBase<ConceptoPagoResponse> obtenerPorId(@RequestParam("id") Integer id) {
        return ResponseBase.ok(conceptoPagoService.obtenerPorId(id));
    }

    @PostMapping(value = ApiRoutes.Demo.GUARDAR)
    public ResponseBase<ConceptoPagoResponse> guardar(@RequestBody ConceptoPagoRequest request) {
        ConceptoPagoResponse resp = conceptoPagoService.guardar(request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.EDITAR)
    public ResponseBase<ConceptoPagoResponse> editar(@RequestParam("id") Integer id,
            @RequestBody ConceptoPagoRequest request) {
        ConceptoPagoResponse resp = conceptoPagoService.editar(id, request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.DESACTIVAR)
    public ResponseBase<Void> desactivar(@RequestParam("id") Integer id) {
        conceptoPagoService.desactivar(id);
        return ResponseBase.ok(null);
    }

    @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam("id") Integer id) {
        conceptoPagoService.eliminar(id);
        return ResponseBase.ok(null);
=======
    @Qualifier("conceptoPagoServicio")
    private final ConceptoPagoService conceptoPagoServicio;

    @GetMapping(ApiRoutes.Demo.LISTAR)
    public List<ConceptoPagoModel> listar() {
        return conceptoPagoServicio.listarConceptoPago();
    }

    @GetMapping(ApiRoutes.Demo.OBTENER_POR_ID)
    public ConceptoPagoModel obtener(@RequestParam("id") int id) {
        ConceptoPagoModel c = conceptoPagoServicio.obtenerConceptoPago(id);
        if (c == null) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        c.setMensaje(Mensajes.ENCONTRADO);
        return c;
    }

    @PostMapping(ApiRoutes.Demo.GUARDAR)
    public ConceptoPagoModel crear(@RequestParam("clasificadorId") int clasificadorId,
            @RequestParam("nombreConcepto") String nombreConcepto,
            @RequestParam("precioBase") float precioBase,
            @RequestParam("idUsuario") int idUsuario) {
        ConceptoPagoModel c = new ConceptoPagoModel();
        ClasificadorIngresoModel clasificador = new ClasificadorIngresoModel();
        clasificador.setClasificadorIngresoId(clasificadorId);
        c.setClasificadorIngreso(clasificador);
        c.setNombreConcepto(nombreConcepto);
        c.setPrecioBase(precioBase);
        c.setUsuarioCreacion(idUsuario);
        c.setFechaCreacion(LocalDateTime.now());
        c.setActivo(true);
        c.setEsEliminado(false);
        c = conceptoPagoServicio.registrarConceptoPago(c);
        c.setMensaje(Mensajes.GUARDADO_OK);
        return c;
    }

    @PutMapping(ApiRoutes.Demo.EDITAR)
    public ConceptoPagoModel editar(@RequestParam("id") int id,
            @RequestParam("clasificadorId") int clasificadorId,
            @RequestParam("nombreConcepto") String nombreConcepto,
            @RequestParam("precioBase") float precioBase,
            @RequestParam("idUsuario") int idUsuario) {
        ConceptoPagoModel c = conceptoPagoServicio.obtenerConceptoPago(id);
        if (c == null) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        ClasificadorIngresoModel clasificador = new ClasificadorIngresoModel();
        clasificador.setClasificadorIngresoId(clasificadorId);
        c.setClasificadorIngreso(clasificador);
        c.setNombreConcepto(nombreConcepto);
        c.setPrecioBase(precioBase);
        c.setUsuarioModificacion(idUsuario);
        c.setFechaModificacion(LocalDateTime.now());
        c = conceptoPagoServicio.actualizarConceptoPago(c);
        c.setMensaje(Mensajes.ACTUALIZADO_OK);
        return c;
    }

    @PutMapping(ApiRoutes.Demo.DESACTIVAR)
    public ConceptoPagoModel desactivar(@RequestParam("id") int id,
            @RequestParam("idUsuario") int idUsuario) {
        ConceptoPagoModel c = conceptoPagoServicio.obtenerConceptoPago(id);
        if (c == null) {
            ConceptoPagoModel resp = new ConceptoPagoModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        c.setUsuarioModificacion(idUsuario);
        c.setFechaModificacion(LocalDateTime.now());
        c.setActivo(false);
        c = conceptoPagoServicio.actualizarConceptoPago(c);
        c.setMensaje(Mensajes.DESACTIVADO_OK);
        return c;
    }

    @DeleteMapping(ApiRoutes.Demo.ELIMINAR)
    public String eliminar(@RequestParam("id") int id) {
        if (conceptoPagoServicio.obtenerConceptoPago(id) == null) {
            return Mensajes.NO_ENCONTRADO;
        }
        conceptoPagoServicio.eliminarConceptoPago(id);
        return Mensajes.ELIMINADO_OK;
>>>>>>> Stashed changes
    }
}
