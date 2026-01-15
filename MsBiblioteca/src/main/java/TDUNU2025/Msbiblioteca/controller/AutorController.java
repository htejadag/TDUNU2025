package tdunu2025.msbiblioteca.controller;

import tdunu2025.msbiblioteca.model.request.AutorRequest;
import tdunu2025.msbiblioteca.model.response.AutorResponse;
import tdunu2025.msbiblioteca.service.AutorService;
import tdunu2025.msbiblioteca.util.ApiRoutes;
import tdunu2025.msbiblioteca.util.Mensaje;
import tdunu2025.msbiblioteca.util.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Autor.BASE)
@RequiredArgsConstructor
public class AutorController {

    private final AutorService service;

    @GetMapping(ApiRoutes.Autor.LISTAR) // <--- Corregido
    public ResponseEntity<ResponseBase<List<AutorResponse>>> listar() {
        // Inferencia de tipos <>
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping(ApiRoutes.Autor.OBTENER_POR_ID) // <--- Corregido
    public ResponseEntity<ResponseBase<AutorResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping(ApiRoutes.Autor.GUARDAR) // <--- Corregido
    public ResponseEntity<ResponseBase<AutorResponse>> registrar(@RequestBody AutorRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping(ApiRoutes.Autor.ACTUALIZAR) // <--- Corregido
    public ResponseEntity<ResponseBase<AutorResponse>> actualizar(@PathVariable Long id, @RequestBody AutorRequest request) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping(ApiRoutes.Autor.ELIMINAR) // <--- Corregido
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}