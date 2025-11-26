package unu.td.MsAcademico.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unu.td.MsAcademico.model.request.FacultadRequest;
import unu.td.MsAcademico.model.response.FacultadResponse;
import unu.td.MsAcademico.service.IFacultadService;
import unu.td.MsAcademico.utils.ApiRoutes;
import unu.td.MsAcademico.utils.ResponseBase;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.Facultad.BASE)
public class FacultadController {

    private IFacultadService service;

    @PostMapping(ApiRoutes.Facultad.add)
    public ResponseBase<FacultadResponse> add(@Valid @RequestBody FacultadRequest request) {
        FacultadResponse response = service.add(request);
        return ResponseBase.ok(response);
    }
}
