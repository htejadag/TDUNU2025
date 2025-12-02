package unu.td.MsAcademico.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unu.td.MsAcademico.model.request.EscuelaProfesionalRequest;
import unu.td.MsAcademico.model.response.EscuelaProfesionalResponse;
import unu.td.MsAcademico.service.IEscuelaProfesionalService;
import unu.td.MsAcademico.utils.ApiRoutes;
import unu.td.MsAcademico.utils.Messages;
import unu.td.MsAcademico.utils.ResponseBase;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.EscuelaProfesional.BASE)
public class EscuelaProfesionalController {

    private IEscuelaProfesionalService service;

    @PostMapping(ApiRoutes.General.add)
    public ResponseBase<EscuelaProfesionalResponse> add(@Valid @RequestBody EscuelaProfesionalRequest request) {
        EscuelaProfesionalResponse response = service.add(request);
        return ResponseBase.ok(response);
    }

    @GetMapping
    public ResponseBase<List<EscuelaProfesionalResponse>> getAll() {
        List<EscuelaProfesionalResponse> response = service.getAll();
        return ResponseBase.ok(response);
    }

    @GetMapping(ApiRoutes.General.byId)
    public ResponseBase<EscuelaProfesionalResponse> getById(@PathVariable Integer id) {
        EscuelaProfesionalResponse response = service.getById(id);
        return ResponseBase.ok(response);
    }

    @PutMapping(ApiRoutes.General.update)
    public ResponseBase<EscuelaProfesionalResponse> update(@PathVariable Integer id, @Valid @RequestBody EscuelaProfesionalRequest request) {
        EscuelaProfesionalResponse response = service.update(id, request);
        return ResponseBase.ok(response);
    }


    @DeleteMapping(ApiRoutes.General.delete)
    public ResponseBase<EscuelaProfesionalResponse> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseBase.ok(Messages.ELIMINACION_EXITOSA, null);
    }

    @PatchMapping(ApiRoutes.General.deactivate)
    public ResponseBase<EscuelaProfesionalResponse> deactivate(@PathVariable Integer id) {
        service.deactivate(id);
        return ResponseBase.ok(Messages.DESACTIVACION_EXITOSA, null);
    }
}
