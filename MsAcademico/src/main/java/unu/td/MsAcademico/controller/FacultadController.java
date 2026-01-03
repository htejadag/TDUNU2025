package unu.td.msacademico.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unu.td.msacademico.model.request.FacultadRequest;
import unu.td.msacademico.model.response.FacultadResponse;
import unu.td.msacademico.service.IFacultadService;
import unu.td.msacademico.utils.ApiRoutes;
import unu.td.msacademico.utils.Messages;
import unu.td.msacademico.utils.ResponseBase;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.Facultad.BASE)
public class FacultadController {

    private IFacultadService service;

    @PostMapping(ApiRoutes.General.add)
    public ResponseBase<FacultadResponse> add(@Valid @RequestBody FacultadRequest request) {
        FacultadResponse response = service.add(request);
        return ResponseBase.ok(response);
    }

    @GetMapping
    public ResponseBase<List<FacultadResponse>> getAll() {
        List<FacultadResponse> response = service.getAll();
        return ResponseBase.ok(response);
    }

    @GetMapping(ApiRoutes.General.byId)
    public ResponseBase<FacultadResponse> getById(@PathVariable Integer id) {
        FacultadResponse response = service.getById(id);
        return ResponseBase.ok(response);
    }

    @PutMapping(ApiRoutes.General.update)
    public ResponseBase<FacultadResponse> update(@PathVariable Integer id, @Valid @RequestBody FacultadRequest request) {
        FacultadResponse response = service.update(id, request);
        return ResponseBase.ok(response);
    }

    @PatchMapping(ApiRoutes.General.delete)
    public ResponseBase<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseBase.ok(Messages.ELIMINACION_EXITOSA, null);
    }

    @PatchMapping(ApiRoutes.General.activate)
    public ResponseBase<Void> activate(@PathVariable Integer id) {
        service.activate(id);
        return ResponseBase.ok(Messages.ACTIVACION_EXITOSA, null);
    }

    @PatchMapping(ApiRoutes.General.deactivate)
    public ResponseBase<Void> deactivate(@PathVariable Integer id) {
        service.deactivate(id);
        return ResponseBase.ok(Messages.DESACTIVACION_EXITOSA, null);
    }
}
