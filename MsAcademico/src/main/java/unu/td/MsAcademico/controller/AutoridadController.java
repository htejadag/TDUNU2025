package unu.td.MsAcademico.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unu.td.MsAcademico.model.request.AutoridadRequest;
import unu.td.MsAcademico.model.response.AutoridadResponse;
import unu.td.MsAcademico.service.IAutoridadService;
import unu.td.MsAcademico.utils.ApiRoutes;
import unu.td.MsAcademico.utils.Messages;
import unu.td.MsAcademico.utils.ResponseBase;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.Autoridad.BASE)
public class AutoridadController {

    private IAutoridadService service;

    @PostMapping(ApiRoutes.General.add)
    public ResponseBase<AutoridadResponse> add(@Valid @RequestBody AutoridadRequest request) {
        AutoridadResponse response = service.add(request);
        return ResponseBase.ok(response);
    }

    @GetMapping
    public ResponseBase<List<AutoridadResponse>> getAll() {
        List<AutoridadResponse> response = service.getAll();
        return ResponseBase.ok(response);
    }

    @GetMapping(ApiRoutes.General.byId)
    public ResponseBase<AutoridadResponse> getById(@PathVariable Integer id) {
        AutoridadResponse response = service.getById(id);
        return ResponseBase.ok(response);
    }

    @PutMapping(ApiRoutes.General.update)
    public ResponseBase<AutoridadResponse> update(@PathVariable Integer id, @Valid @RequestBody AutoridadRequest request) {
        AutoridadResponse response = service.update(id, request);
        return ResponseBase.ok(response);
    }

    @PatchMapping(ApiRoutes.General.delete)
    public ResponseBase<AutoridadResponse> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseBase.ok(Messages.ELIMINACION_EXITOSA, null);
    }

    @PatchMapping(ApiRoutes.General.activate)
    public ResponseBase<AutoridadResponse> activate(@PathVariable Integer id) {
        service.activate(id);
        return ResponseBase.ok(Messages.ACTIVACION_EXITOSA, null);
    }

    @PatchMapping(ApiRoutes.General.deactivate)
    public ResponseBase<AutoridadResponse> deactivate(@PathVariable Integer id) {
        service.deactivate(id);
        return ResponseBase.ok(Messages.DESACTIVACION_EXITOSA, null);
    }

    @GetMapping(ApiRoutes.Autoridad.byIdEntidad)
    public ResponseBase<List<AutoridadResponse>> byIdEntidad(@PathVariable Integer idTipoEntidad, @PathVariable Integer idEntidad) {
        List<AutoridadResponse> response = service.getByEntidad(idTipoEntidad, idEntidad);
        return ResponseBase.ok(response);
    }

    @GetMapping(ApiRoutes.Autoridad.byIdEntidadAndFecha)
    public ResponseBase<AutoridadResponse> getAll(@PathVariable Integer idTipoEntidad, @PathVariable Integer idEntidad, @PathVariable LocalDate fecha) {
        AutoridadResponse response = service.getByEntidadAndFecha(idTipoEntidad, idEntidad, fecha);
        return ResponseBase.ok(response);
    }
}
