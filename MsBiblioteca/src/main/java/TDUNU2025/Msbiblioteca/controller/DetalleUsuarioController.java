package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.config.ResponseBase; // Ajustado según tu estructura anterior
import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;
import TDUNU2025.Msbiblioteca.service.DetalleUsuarioService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.DetalleUsuario.BASE) 
@RequiredArgsConstructor
public class DetalleUsuarioController {

    private final DetalleUsuarioService service;


    @GetMapping(ApiRoutes.DetalleUsuario.LISTAR)
    public ResponseEntity<ResponseBase<List<DetalleUsuarioResponse>>> listar() {
        try {

            List<DetalleUsuarioResponse> lista = service.listarTodo();

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


    @GetMapping(ApiRoutes.DetalleUsuario.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<DetalleUsuarioResponse>> obtener(@PathVariable Integer id) {
        try {

            DetalleUsuarioResponse response = service.obtenerPorId(id);

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


    @PostMapping(ApiRoutes.DetalleUsuario.GUARDAR)
    public ResponseEntity<ResponseBase<DetalleUsuarioResponse>> guardar(@RequestBody DetalleUsuarioRequest request) {
        try {

            DetalleUsuarioResponse response = service.guardar(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(
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
    
    @DeleteMapping(ApiRoutes.DetalleUsuario.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Integer id) {
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