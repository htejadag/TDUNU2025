package tdunu2025.Msbiblioteca.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tdunu2025.Msbiblioteca.model.request.CategoriaLibroRequest;
import tdunu2025.Msbiblioteca.model.response.CategoriaLibroResponse;
import tdunu2025.Msbiblioteca.service.CategoriaLibroService;
import tdunu2025.Msbiblioteca.util.ApiRoutes;
import tdunu2025.Msbiblioteca.util.Mensaje;
import tdunu2025.Msbiblioteca.util.ResponseBase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria-libro") // O usa ApiRoutes si lo definiste
@RequiredArgsConstructor
public class CategoriaLibroController {

    private final CategoriaLibroService service;

    @GetMapping
    public ResponseEntity<ResponseBase<List<CategoriaLibroResponse>>> listar() {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<CategoriaLibroResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseBase<CategoriaLibroResponse>> registrar(@Valid @RequestBody CategoriaLibroRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<CategoriaLibroResponse>> actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaLibroRequest request) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}