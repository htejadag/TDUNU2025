package tdunu2025.msbiblioteca.controller;

import lombok.RequiredArgsConstructor;
import tdunu2025.msbiblioteca.model.request.LibroCategoriaRequest;
import tdunu2025.msbiblioteca.model.response.LibroCategoriaResponse;
import tdunu2025.msbiblioteca.service.LibroCategoriaService;
import tdunu2025.msbiblioteca.util.ApiRoutes;
import tdunu2025.msbiblioteca.util.Mensaje;
import tdunu2025.msbiblioteca.util.ResponseBase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.LibroCategoria.BASE)
@RequiredArgsConstructor
public class LibroCategoriaController {

    private final LibroCategoriaService service;

    // 1. LISTAR
    @GetMapping(ApiRoutes.LibroCategoria.LISTAR)
    public ResponseEntity<ResponseBase<List<LibroCategoriaResponse>>> listar() {
        List<LibroCategoriaResponse> lista = service.listar();
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, lista));
    }

    // 2. OBTENER POR ID
    @GetMapping(ApiRoutes.LibroCategoria.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<LibroCategoriaResponse>> obtener(@PathVariable Long id) {
        LibroCategoriaResponse response = service.obtener(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, response));
    }

    // 3. REGISTRAR (Asignar una categoría a un libro)
    @PostMapping(ApiRoutes.LibroCategoria.GUARDAR)
    public ResponseEntity<ResponseBase<LibroCategoriaResponse>> registrar(@RequestBody LibroCategoriaRequest request) {
        LibroCategoriaResponse response = service.registrar(request);
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, response), HttpStatus.CREATED);
    }

    // 4. ACTUALIZAR
    @PutMapping(ApiRoutes.LibroCategoria.ACTUALIZAR)
    public ResponseEntity<ResponseBase<LibroCategoriaResponse>> actualizar(@PathVariable Long id, @RequestBody LibroCategoriaRequest request) {
        LibroCategoriaResponse response = service.actualizar(id, request);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, response));
    }

    // 5. ELIMINAR (Desasignar una categoría)
    @DeleteMapping(ApiRoutes.LibroCategoria.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}