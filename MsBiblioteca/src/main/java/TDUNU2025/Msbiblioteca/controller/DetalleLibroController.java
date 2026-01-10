package tdunu2025.Msbiblioteca.controller;

import lombok.RequiredArgsConstructor;
import tdunu2025.Msbiblioteca.model.request.DetalleLibroRequest;
import tdunu2025.Msbiblioteca.model.response.DetalleLibroResponse;
import tdunu2025.Msbiblioteca.service.DetalleLibroService;
import tdunu2025.Msbiblioteca.util.ApiRoutes;
import tdunu2025.Msbiblioteca.util.Mensaje;
import tdunu2025.Msbiblioteca.util.ResponseBase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.DetalleLibro.BASE)
@RequiredArgsConstructor
public class DetalleLibroController {

    private final DetalleLibroService service;

    @GetMapping(ApiRoutes.DetalleLibro.LISTAR)
    public ResponseEntity<ResponseBase<List<DetalleLibroResponse>>> listar() {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping(ApiRoutes.DetalleLibro.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<DetalleLibroResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping(ApiRoutes.DetalleLibro.GUARDAR)
    public ResponseEntity<ResponseBase<DetalleLibroResponse>> registrar(@RequestBody DetalleLibroRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping(ApiRoutes.DetalleLibro.ACTUALIZAR)
    public ResponseEntity<ResponseBase<DetalleLibroResponse>> actualizar(@PathVariable Long id, @RequestBody DetalleLibroRequest request) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping(ApiRoutes.DetalleLibro.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}