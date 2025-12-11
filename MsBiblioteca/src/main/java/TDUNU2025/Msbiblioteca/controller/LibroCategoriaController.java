package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.config.ResponseBase;
import TDUNU2025.Msbiblioteca.model.request.LibroCategoriaRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroCategoriaResponse;
import TDUNU2025.Msbiblioteca.service.LibroCategoriaService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.LibroCategoria.BASE)
@RequiredArgsConstructor
public class LibroCategoriaController {

    private final LibroCategoriaService service;

    // POST -> /api/libro-categoria/guardar
    @PostMapping(ApiRoutes.LibroCategoria.GUARDAR)
    public ResponseEntity<ResponseBase<LibroCategoriaResponse>> registrar(@RequestBody LibroCategoriaRequest request) {
        try {
            LibroCategoriaResponse response = service.registrar(request);

            return ResponseEntity.ok(
                new ResponseBase<>(
                    Mensaje.CODE_OK,
                    Mensaje.MENSAJE_GUARDADO,
                    response
                )
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                new ResponseBase<>(
                    Mensaje.CODE_ERROR,
                    e.getMessage(),
                    null
                )
            );
        }
    }

    // GET -> /api/libro-categoria/listar
    @GetMapping(ApiRoutes.LibroCategoria.LISTAR)
    public ResponseEntity<ResponseBase<List<LibroCategoriaResponse>>> listar() {
        try {
            List<LibroCategoriaResponse> lista = service.listar();

            return ResponseEntity.ok(
                new ResponseBase<>(
                    Mensaje.CODE_OK,
                    Mensaje.MENSAJE_EXITO,
                    lista
                )
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                new ResponseBase<>(
                    Mensaje.CODE_ERROR,
                    e.getMessage(),
                    null
                )
            );
        }
    }

    // GET -> /api/libro-categoria/obtener/{id}
    @GetMapping(ApiRoutes.LibroCategoria.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<LibroCategoriaResponse>> obtener(@PathVariable Long id) {
        try {
            LibroCategoriaResponse response = service.obtener(id);

            return ResponseEntity.ok(
                new ResponseBase<>(
                    Mensaje.CODE_OK,
                    Mensaje.MENSAJE_EXITO,
                    response
                )
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ResponseBase<>(
                    Mensaje.CODE_ERROR,
                    e.getMessage(),
                    null
                )
            );
        }
    }

    // PUT -> /api/libro-categoria/actualizar/{id}
    @PutMapping(ApiRoutes.LibroCategoria.ACTUALIZAR)
    public ResponseEntity<ResponseBase<LibroCategoriaResponse>> actualizar(
            @PathVariable Long id,
            @RequestBody LibroCategoriaRequest request
    ) {
        try {
            LibroCategoriaResponse response = service.actualizar(id, request);

            return ResponseEntity.ok(
                new ResponseBase<>(
                    Mensaje.CODE_OK,
                    Mensaje.MENSAJE_ACTUALIZADO,
                    response
                )
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                new ResponseBase<>(
                    Mensaje.CODE_ERROR,
                    e.getMessage(),
                    null
                )
            );
        }
    }

    // DELETE -> /api/libro-categoria/eliminar/{id}
    @DeleteMapping(ApiRoutes.LibroCategoria.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);

            return ResponseEntity.ok(
                new ResponseBase<>(
                    Mensaje.CODE_OK,
                    Mensaje.MENSAJE_ELIMINADO,
                    null
                )
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ResponseBase<>(
                    Mensaje.CODE_ERROR,
                    e.getMessage(),
                    null
                )
            );
        }
    }
}
