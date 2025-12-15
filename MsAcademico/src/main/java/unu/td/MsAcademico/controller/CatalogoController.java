package unu.td.msacademico.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unu.td.msacademico.model.request.CatalogoRequest;
import unu.td.msacademico.model.response.CatalogoResponse;
import unu.td.msacademico.service.ICatalogoService;
import unu.td.msacademico.utils.ApiRoutes;
import unu.td.msacademico.utils.Messages;
import unu.td.msacademico.utils.ResponseBase;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.Catalogo.BASE)
public class CatalogoController {

    private ICatalogoService service;

    @PostMapping(ApiRoutes.General.add)
    public ResponseBase<CatalogoResponse> add(@Valid @RequestBody CatalogoRequest request) {
        CatalogoResponse response = service.add(request);
        return ResponseBase.ok(response);
    }

    @GetMapping
    public ResponseBase<List<CatalogoResponse>> getAll(@RequestParam(required = false) String categoria) {
        List<CatalogoResponse> response;
        if (categoria != null && !categoria.isEmpty()) {
            response = service.getAllByCategoria(categoria);
        } else {
            response = service.getAll();
        }
        return ResponseBase.ok(response);
    }

    @GetMapping(ApiRoutes.General.byId)
    public ResponseBase<CatalogoResponse> getById(@PathVariable Integer id) {
        CatalogoResponse response = service.getById(id);
        return ResponseBase.ok(response);
    }

    @PutMapping(ApiRoutes.General.update)
    public ResponseBase<CatalogoResponse> update(@PathVariable Integer id, @Valid @RequestBody CatalogoRequest request) {
        CatalogoResponse response = service.update(id, request);
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