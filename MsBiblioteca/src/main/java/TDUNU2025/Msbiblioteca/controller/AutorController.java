<<<<<<< HEAD
package tdunu2025.msbiblioteca.controller;
=======
package TDUNU2025.Msbiblioteca.controller;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import TDUNU2025.Msbiblioteca.model.request.AutorRequest;
import TDUNU2025.Msbiblioteca.model.response.AutorResponse;
import TDUNU2025.Msbiblioteca.service.AutorService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import tdunu2025.msbiblioteca.model.request.AutorRequest;
import tdunu2025.msbiblioteca.model.response.AutorResponse;
import tdunu2025.msbiblioteca.service.AutorService;
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
@RequestMapping(ApiRoutes.RUTA_AUTOR) // "/api/autor"
@RequiredArgsConstructor
public class AutorController {

    private final AutorService service;

    @GetMapping
    public ResponseEntity<ResponseBase<List<AutorResponse>>> listar() {
        List<AutorResponse> lista = service.listar();
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<AutorResponse>> obtener(@PathVariable Long id) {
        AutorResponse response = service.obtener(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, response));
    }

    @PostMapping
    public ResponseEntity<ResponseBase<AutorResponse>> registrar(@RequestBody AutorRequest request) {
        AutorResponse response = service.registrar(request);
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, response), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<AutorResponse>> actualizar(@PathVariable Long id, @RequestBody AutorRequest request) {
        AutorResponse response = service.actualizar(id, request);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}