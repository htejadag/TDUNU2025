<<<<<<< HEAD
package tdunu2025.msbiblioteca.controller;
=======
package TDUNU2025.Msbiblioteca.controller;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import TDUNU2025.Msbiblioteca.model.request.CategoriaLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.CategoriaLibroResponse;
import TDUNU2025.Msbiblioteca.service.CategoriaLibroService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes; // Asegúrate de tener esta ruta definida
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import tdunu2025.msbiblioteca.model.request.CategoriaLibroRequest;
import tdunu2025.msbiblioteca.model.response.CategoriaLibroResponse;
import tdunu2025.msbiblioteca.service.CategoriaLibroService;
import tdunu2025.msbiblioteca.util.ApiRoutes;
import tdunu2025.msbiblioteca.util.Mensaje;
import tdunu2025.msbiblioteca.util.ResponseBase;

=======
>>>>>>> parent of 852a09b (Actualizacion de ruta)
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