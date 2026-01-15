package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.util.ResponseBase;
import TDUNU2025.Msbiblioteca.model.request.LibroCategoriaRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroCategoriaResponse;
import TDUNU2025.Msbiblioteca.service.LibroCategoriaService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.LibroCategoria.BASE)
@RequiredArgsConstructor
public class LibroCategoriaController {

    private final LibroCategoriaService service;

    @PostMapping(ApiRoutes.LibroCategoria.GUARDAR)
    public ResponseEntity<ResponseBase<LibroCategoriaResponse>> registrar(
            @RequestBody LibroCategoriaRequest request) {

        LibroCategoriaResponse response = service.registrar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_GUARDADO,
                        response
                )
        );
    }

    @GetMapping(ApiRoutes.LibroCategoria.LISTAR)
    public ResponseEntity<ResponseBase<List<LibroCategoriaResponse>>> listar() {

        List<LibroCategoriaResponse> lista = service.listar();

        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_EXITO,
                        lista
                )
        );
    }

    @GetMapping(ApiRoutes.LibroCategoria.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<LibroCategoriaResponse>> obtener(
            @PathVariable Long id) {

        LibroCategoriaResponse response = service.obtener(id);

        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_EXITO,
                        response
                )
        );
    }


    @PutMapping(ApiRoutes.LibroCategoria.ACTUALIZAR)
    public ResponseEntity<ResponseBase<LibroCategoriaResponse>> actualizar(
            @PathVariable Long id,
            @RequestBody LibroCategoriaRequest request) {

        LibroCategoriaResponse response = service.actualizar(id, request);

        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_ACTUALIZADO,
                        response
                )
        );
    }

    @DeleteMapping(ApiRoutes.LibroCategoria.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_ELIMINADO,
                        null
                )
        );
    }
}
