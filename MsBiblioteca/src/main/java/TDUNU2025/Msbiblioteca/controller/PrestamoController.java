package tdunu2025.Msbiblioteca.controller;

import lombok.RequiredArgsConstructor;
import tdunu2025.Msbiblioteca.model.request.PrestamoRequest;
import tdunu2025.Msbiblioteca.model.response.PrestamoResponse;
import tdunu2025.Msbiblioteca.service.PrestamoService;
import tdunu2025.Msbiblioteca.util.ApiRoutes;
import tdunu2025.Msbiblioteca.util.Mensaje;
import tdunu2025.Msbiblioteca.util.ResponseBase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Prestamo.BASE)
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService service;

    @GetMapping(ApiRoutes.Prestamo.LISTAR)
    public ResponseEntity<ResponseBase<List<PrestamoResponse>>> listar() {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping(ApiRoutes.Prestamo.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<PrestamoResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping(ApiRoutes.Prestamo.GUARDAR)
    public ResponseEntity<ResponseBase<PrestamoResponse>> registrar(@RequestBody PrestamoRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping(ApiRoutes.Prestamo.ACTUALIZAR)
    public ResponseEntity<ResponseBase<PrestamoResponse>> actualizar(@PathVariable Long id, @RequestBody PrestamoRequest request) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping(ApiRoutes.Prestamo.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}