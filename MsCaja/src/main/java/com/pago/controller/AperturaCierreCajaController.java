package com.pago.controller;

import java.util.List;
<<<<<<< Updated upstream
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pago.model.request.AperturaCierreCajaRequest;
import com.pago.model.response.AperturaCierreCajaResponse;
import com.pago.service.AperturaCierreCajaService;
import com.pago.util.ApiRoutes;
import com.pago.util.ResponseBase;
=======
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pago.util.ApiRoutes;
import com.pago.util.Mensajes;
import com.pago.model.entity.AperturaCierreCajaModel;
import com.pago.service.AperturaCierreCajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
>>>>>>> Stashed changes

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.Demo.APE_CIE_CAJA)
public class AperturaCierreCajaController {
<<<<<<< Updated upstream

    @Autowired
    AperturaCierreCajaService aperturaCierreCajaService;

    @GetMapping(value = ApiRoutes.Demo.LISTAR)
    public ResponseBase<List<AperturaCierreCajaResponse>> listar() {
        List<AperturaCierreCajaResponse> resp = aperturaCierreCajaService.listar();
        return ResponseBase.ok(resp);
    }

    @GetMapping(value = ApiRoutes.Demo.OBTENER_POR_ID)
    public ResponseBase<AperturaCierreCajaResponse> obtenerPorId(@RequestParam("id") Integer id) {
        return ResponseBase.ok(aperturaCierreCajaService.obtenerPorId(id));
    }

    @PostMapping(value = ApiRoutes.Demo.GUARDAR)
    public ResponseBase<AperturaCierreCajaResponse> guardar(@RequestBody AperturaCierreCajaRequest request) {
        AperturaCierreCajaResponse resp = aperturaCierreCajaService.guardar(request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.EDITAR)
    public ResponseBase<AperturaCierreCajaResponse> editar(@RequestParam("id") Integer id,
            @RequestBody AperturaCierreCajaRequest request) {
        AperturaCierreCajaResponse resp = aperturaCierreCajaService.editar(id, request);
        return ResponseBase.ok(resp);
    }

    @PutMapping(value = ApiRoutes.Demo.DESACTIVAR)
    public ResponseBase<Void> desactivar(@RequestParam("id") Integer id) {
        aperturaCierreCajaService.desactivar(id);
        return ResponseBase.ok(null);
    }

    @DeleteMapping(value = ApiRoutes.Demo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam("id") Integer id) {
        aperturaCierreCajaService.eliminar(id);
        return ResponseBase.ok(null);
    }
=======
    @Qualifier("aperturaCierreCajaServicio")
    private final AperturaCierreCajaService apeCieCajaServicio;

    @GetMapping(ApiRoutes.Demo.LISTAR)
    public List<AperturaCierreCajaModel> listar() {
        return apeCieCajaServicio.listarApeCieCaja();
    }

    @GetMapping(ApiRoutes.Demo.OBTENER_POR_ID)
    public AperturaCierreCajaModel obtener(@RequestParam("id") int id) {
        AperturaCierreCajaModel a = apeCieCajaServicio.obtenerApeCieCaja(id);
        if (a == null) {
            AperturaCierreCajaModel resp = new AperturaCierreCajaModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        a.setMensaje(Mensajes.ENCONTRADO);
        return a;
    }

    @PostMapping(ApiRoutes.Demo.GUARDAR)
    public AperturaCierreCajaModel crear(@RequestParam("idUsuario") int idUsuario) {
        AperturaCierreCajaModel a = new AperturaCierreCajaModel();
        a.setFechaApertura(LocalDateTime.now());
        a.setUsuarioCreacion(idUsuario);
        a.setFechaCreacion(LocalDateTime.now());
        a.setActivo(true);
        a.setEsEliminado(false);
        a = apeCieCajaServicio.registrarApeCieCaja(a);
        a.setMensaje(Mensajes.GUARDADO_OK);
        return a;
    }

    @PutMapping(ApiRoutes.Demo.DESACTIVAR)
    public AperturaCierreCajaModel desactivar(@RequestParam("id") int id,
            @RequestParam("idUsuario") int idUsuario) {
        AperturaCierreCajaModel a = apeCieCajaServicio.obtenerApeCieCaja(id);
        if (a == null) {
            AperturaCierreCajaModel resp = new AperturaCierreCajaModel();
            resp.setMensaje(Mensajes.NO_ENCONTRADO);
            return resp;
        }
        a.setFechaCierre(LocalDateTime.now());
        a.setUsuarioModificacion(idUsuario);
        a.setFechaModificacion(LocalDateTime.now());
        a.setActivo(false);
        a = apeCieCajaServicio.actualizarApeCieCaja(a);
        a.setMensaje(Mensajes.DESACTIVADO_OK);
        return a;
    }

    @DeleteMapping(ApiRoutes.Demo.ELIMINAR)
    public String eliminar(@RequestParam("id") int id) {
        if (apeCieCajaServicio.obtenerApeCieCaja(id) == null) {
            return Mensajes.NO_ENCONTRADO;
        }
        apeCieCajaServicio.eliminarApeCieCaja(id);
        return Mensajes.ELIMINADO_OK;
    }

>>>>>>> Stashed changes
}
