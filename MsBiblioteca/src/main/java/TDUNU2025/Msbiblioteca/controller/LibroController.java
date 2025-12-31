package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.LibroRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroResponse;
import TDUNU2025.Msbiblioteca.service.LibroService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Libro.BASE)
@RequiredArgsConstructor
public class LibroController {

    private final LibroService service;

    // POST -> /api/libro/guardar
    @PostMapping(ApiRoutes.Libro.GUARDAR)
    public ResponseEntity<ResponseBase<LibroResponse>> registrar(@RequestBody LibroRequest request) {
        try {
            LibroResponse response = service.registrar(request);

            return ResponseEntity.ok(
                    new ResponseBase<>(
                            Mensaje.CODE_OK,
                            Mensaje.MENSAJE_EXITO,
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

    // POST -> /api/libro/guardar
    @PostMapping(ApiRoutes.Libro.GUARDAR)
    public LibroResponse registrar(@RequestBody LibroRequest r) {
        return service.registrar(r);
    }

    // GET -> /api/libro/listar
    @GetMapping(ApiRoutes.Libro.LISTAR)
    public ResponseEntity<ResponseBase<List<LibroResponse>>> listar() {
        try {
            List<LibroResponse> lista = service.listar();

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

    // GET -> /api/libro/obtener/{id}
    @GetMapping(ApiRoutes.Libro.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<LibroResponse>> obtener(@PathVariable Long id) {
        try {
            LibroResponse response = service.obtener(id);

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

    // PUT -> /api/libro/actualizar/{id}
    @PutMapping(ApiRoutes.Libro.ACTUALIZAR)
    public ResponseEntity<ResponseBase<LibroResponse>> actualizar(@PathVariable Long id,
                                                                  @RequestBody LibroRequest request) {
        try {
            LibroResponse response = service.actualizar(id, request);

            return ResponseEntity.ok(
                    new ResponseBase<>(
                            Mensaje.CODE_OK,
                            Mensaje.MENSAJE_EXITO,
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

    // DELETE -> /api/libro/eliminar/{id}
    @DeleteMapping(ApiRoutes.Libro.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);

            return ResponseEntity.ok(
                    new ResponseBase<>(
                            Mensaje.CODE_OK,
                            Mensaje.MENSAJE_EXITO,
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
