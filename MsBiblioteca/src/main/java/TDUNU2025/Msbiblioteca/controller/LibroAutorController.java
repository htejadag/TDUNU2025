package tdunu2025.msbiblioteca.controller;

import lombok.RequiredArgsConstructor;
import tdunu2025.msbiblioteca.model.request.LibroAutorRequest;
import tdunu2025.msbiblioteca.model.response.LibroAutorResponse;
import tdunu2025.msbiblioteca.service.LibroAutorService;
import tdunu2025.msbiblioteca.util.ApiRoutes;
import tdunu2025.msbiblioteca.util.Mensaje;
import tdunu2025.msbiblioteca.util.ResponseBase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.LibroAutor.BASE)
@RequiredArgsConstructor
public class LibroAutorController {

    private final LibroAutorService service;

    // 1. LISTAR
    @GetMapping(ApiRoutes.LibroAutor.LISTAR)
    public ResponseEntity<ResponseBase<List<LibroAutorResponse>>> listar() {
        List<LibroAutorResponse> lista = service.listar();
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, lista));
    }

    // 2. OBTENER POR ID
    @GetMapping(ApiRoutes.LibroAutor.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<LibroAutorResponse>> obtener(@PathVariable Long id) {
        LibroAutorResponse response = service.obtener(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, response));
    }

    // 3. REGISTRAR
    @PostMapping(ApiRoutes.LibroAutor.GUARDAR)
    public ResponseEntity<ResponseBase<LibroAutorResponse>> registrar(@RequestBody LibroAutorRequest request) {
        LibroAutorResponse response = service.registrar(request);
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, response), HttpStatus.CREATED);
    }

    // 4. ACTUALIZAR
    @PutMapping(ApiRoutes.LibroAutor.ACTUALIZAR)
    public ResponseEntity<ResponseBase<LibroAutorResponse>> actualizar(@PathVariable Long id, @RequestBody LibroAutorRequest request) {
        LibroAutorResponse response = service.actualizar(id, request);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, response));
    }

    // 5. ELIMINAR
    @DeleteMapping(ApiRoutes.LibroAutor.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}