package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.util.ResponseBase;
import TDUNU2025.Msbiblioteca.model.request.LibroAutorRequest;
import TDUNU2025.Msbiblioteca.model.response.LibroAutorResponse;
import TDUNU2025.Msbiblioteca.service.LibroAutorService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.LibroAutor.BASE)
@RequiredArgsConstructor
public class LibroAutorController {

        private final LibroAutorService service;

        @PostMapping(ApiRoutes.LibroAutor.GUARDAR)
        public ResponseEntity<ResponseBase<LibroAutorResponse>> registrar(
                        @RequestBody LibroAutorRequest request) {

                LibroAutorResponse response = service.registrar(request);

                return ResponseEntity.ok(
                                new ResponseBase<>(
                                                Mensaje.CODE_OK,
                                                Mensaje.MENSAJE_EXITO,
                                                response));
        }

        @GetMapping(ApiRoutes.LibroAutor.LISTAR)
        public ResponseEntity<ResponseBase<List<LibroAutorResponse>>> listar() {

                return ResponseEntity.ok(
                                new ResponseBase<>(
                                                Mensaje.CODE_OK,
                                                Mensaje.MENSAJE_EXITO,
                                                service.listar()));
        }

        @GetMapping(ApiRoutes.LibroAutor.OBTENER_POR_ID)
        public ResponseEntity<ResponseBase<LibroAutorResponse>> obtener(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                new ResponseBase<>(
                                                Mensaje.CODE_OK,
                                                Mensaje.MENSAJE_EXITO,
                                                service.obtener(id)));
        }

        @PutMapping(ApiRoutes.LibroAutor.ACTUALIZAR)
        public ResponseEntity<ResponseBase<LibroAutorResponse>> actualizar(
                        @PathVariable Long id,
                        @RequestBody LibroAutorRequest request) {

                return ResponseEntity.ok(
                                new ResponseBase<>(
                                                Mensaje.CODE_OK,
                                                Mensaje.MENSAJE_EXITO,
                                                service.actualizar(id, request)));
        }

        @DeleteMapping(ApiRoutes.LibroAutor.ELIMINAR)
        public ResponseEntity<ResponseBase<Void>> eliminar(
                        @PathVariable Long id) {

                service.eliminar(id);

                return ResponseEntity.ok(
                                new ResponseBase<>(
                                                Mensaje.CODE_OK,
                                                Mensaje.MENSAJE_EXITO,
                                                null));
        }
}