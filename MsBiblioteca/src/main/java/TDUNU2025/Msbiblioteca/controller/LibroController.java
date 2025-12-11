package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.config.ResponseBase;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.model.request.LibroRequest;
import TDUNU2025.model.LibroResponse;
import TDUNU2025.service.LibroService;
import TDUNU2025.util.ApiRoutes;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiRoutes.Libro.BASE)
@RequiredArgsConstructor
public class LibroController {

    private final LibroService service;
    private final ModelMapper modelMapper;

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
