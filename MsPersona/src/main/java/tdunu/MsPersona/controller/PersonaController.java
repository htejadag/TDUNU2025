package tdunu.MsPersona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tdunu.MsPersona.model.request.PersonaRequest;
import tdunu.MsPersona.model.response.PersonaResponse;
import tdunu.MsPersona.service.PersonaService;
import tdunu.MsPersona.util.ApiRoutes;
import tdunu.MsPersona.util.ResponseBase;

@RestController
@RequestMapping(ApiRoutes.Persona.BASE)
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping(ApiRoutes.Persona.LISTAR)
    public ResponseBase<List<PersonaResponse>> listar() {
        return ResponseBase.ok(personaService.listar());
    }

    @GetMapping(ApiRoutes.Persona.OBTENER_POR_ID)
    public ResponseBase<PersonaResponse> obtenerPorId(@RequestParam Integer id) {
        return ResponseBase.ok(personaService.obtenerPorId(id));
    }

    @PostMapping(ApiRoutes.Persona.GUARDAR)
    public ResponseBase<PersonaResponse> guardar(@RequestBody PersonaRequest request) {
        return ResponseBase.ok(personaService.guardar(request));
    }

    @DeleteMapping(ApiRoutes.Persona.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        personaService.eliminar(id);
        return ResponseBase.ok(null);
    }
}
