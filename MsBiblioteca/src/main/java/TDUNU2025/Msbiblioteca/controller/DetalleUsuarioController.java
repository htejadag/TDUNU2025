package tdunu2025.msbiblioteca.controller;

import tdunu2025.msbiblioteca.model.request.DetalleUsuarioRequest;
import tdunu2025.msbiblioteca.model.response.DetalleUsuarioResponse;
import tdunu2025.msbiblioteca.service.DetalleUsuarioService;
import tdunu2025.msbiblioteca.util.ApiRoutes;
import tdunu2025.msbiblioteca.util.Mensaje;
import tdunu2025.msbiblioteca.util.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.DetalleUsuario.BASE)
@RequiredArgsConstructor
public class DetalleUsuarioController {

    private final DetalleUsuarioService service;

    @GetMapping(ApiRoutes.DetalleUsuario.LISTAR)
    public ResponseEntity<ResponseBase<List<DetalleUsuarioResponse>>> listar() {
        // Se especifica el tipo <List<DetalleUsuarioResponse>>
        return ResponseEntity.ok(new ResponseBase<List<DetalleUsuarioResponse>>(
            Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping(ApiRoutes.DetalleUsuario.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<DetalleUsuarioResponse>> obtener(@PathVariable Long id) {
        // Se especifica el tipo <DetalleUsuarioResponse>
        return ResponseEntity.ok(new ResponseBase<DetalleUsuarioResponse>(
            Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping(ApiRoutes.DetalleUsuario.GUARDAR)
    public ResponseEntity<ResponseBase<DetalleUsuarioResponse>> registrar(@RequestBody DetalleUsuarioRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(
            Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping(ApiRoutes.DetalleUsuario.ACTUALIZAR)
    public ResponseEntity<ResponseBase<DetalleUsuarioResponse>> actualizar(@PathVariable Long id, @RequestBody DetalleUsuarioRequest request) {
        // ERROR PREVIO SOLUCIONADO: Inferencia explícita para Java 25
        return ResponseEntity.ok(new ResponseBase<DetalleUsuarioResponse>(
            Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping(ApiRoutes.DetalleUsuario.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<Void>(
            Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}