package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.config.ResponseBase;
import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import TDUNU2025.Msbiblioteca.service.DetalleLibroService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.DetalleLibro.BASE)
@RequiredArgsConstructor
public class DetalleLibroController {

    private final DetalleLibroService service;

    // POST -> /api/detalle-libro/guardar
    @PostMapping(ApiRoutes.DetalleLibro.GUARDAR)
    public ResponseEntity<ResponseBase<DetalleLibroResponse>> registrar(@RequestBody DetalleLibroRequest request) {
        try {
            DetalleLibroResponse response = service.registrar(request);

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

    // GET -> /api/detalle-libro/listar
    @GetMapping(ApiRoutes.DetalleLibro.LISTAR)
    public ResponseEntity<ResponseBase<List<DetalleLibroResponse>>> listar() {
        try {
            List<DetalleLibroResponse> lista = service.listar();

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

    // GET -> /api/detalle-libro/obtener/{id}
    @GetMapping(ApiRoutes.DetalleLibro.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<DetalleLibroResponse>> obtener(@PathVariable Long id) {
        try {
            DetalleLibroResponse response = service.obtener(id);

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

    // PUT -> /api/detalle-libro/actualizar/{id}
    @PutMapping(ApiRoutes.DetalleLibro.ACTUALIZAR)
    public ResponseEntity<ResponseBase<DetalleLibroResponse>> actualizar(
            @PathVariable Long id,
            @RequestBody DetalleLibroRequest request
    ) {
        try {
            DetalleLibroResponse response = service.actualizar(id, request);

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

    // DELETE -> /api/detalle-libro/eliminar/{id}
    @DeleteMapping(ApiRoutes.DetalleLibro.ELIMINAR)
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
