package tdunu2025.msbiblioteca.controller;

import tdunu2025.msbiblioteca.model.request.CategoriaLibroRequest;
import tdunu2025.msbiblioteca.model.response.CategoriaLibroResponse;
import tdunu2025.msbiblioteca.service.CategoriaLibroService;
import tdunu2025.msbiblioteca.util.ApiRoutes;
import tdunu2025.msbiblioteca.util.Mensaje;
import tdunu2025.msbiblioteca.util.ResponseBase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.CategoriaLibro.BASE)
@RequiredArgsConstructor
public class CategoriaLibroController {

    private final CategoriaLibroService service;

    @GetMapping(ApiRoutes.CategoriaLibro.LISTAR) // <--- Corregido
    public ResponseEntity<ResponseBase<List<CategoriaLibroResponse>>> listar() {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping(ApiRoutes.CategoriaLibro.OBTENER_POR_ID) // <--- Corregido
    public ResponseEntity<ResponseBase<CategoriaLibroResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping(ApiRoutes.CategoriaLibro.GUARDAR) // <--- Corregido
    public ResponseEntity<ResponseBase<CategoriaLibroResponse>> registrar(@Valid @RequestBody CategoriaLibroRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping(ApiRoutes.CategoriaLibro.ACTUALIZAR) // <--- Corregido
    public ResponseEntity<ResponseBase<CategoriaLibroResponse>> actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaLibroRequest request) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping(ApiRoutes.CategoriaLibro.ELIMINAR) // <--- Corregido
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}