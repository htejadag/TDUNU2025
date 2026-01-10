package tdunu2025.Msbiblioteca.controller;

import lombok.RequiredArgsConstructor;
import tdunu2025.Msbiblioteca.model.request.MultaRequest;
import tdunu2025.Msbiblioteca.model.response.MultaResponse;
import tdunu2025.Msbiblioteca.service.MultaService;
import tdunu2025.Msbiblioteca.util.ApiRoutes;
import tdunu2025.Msbiblioteca.util.Mensaje;
import tdunu2025.Msbiblioteca.util.ResponseBase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Multa.BASE)
@RequiredArgsConstructor
public class MultaController {

    private final MultaService service;

    @GetMapping(ApiRoutes.Multa.LISTAR)
    public ResponseEntity<ResponseBase<List<MultaResponse>>> listar() {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping(ApiRoutes.Multa.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<MultaResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping(ApiRoutes.Multa.GUARDAR)
    public ResponseEntity<ResponseBase<MultaResponse>> registrar(@RequestBody MultaRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping(ApiRoutes.Multa.ACTUALIZAR)
    public ResponseEntity<ResponseBase<MultaResponse>> actualizar(@PathVariable Long id, @RequestBody MultaRequest request) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping(ApiRoutes.Multa.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}