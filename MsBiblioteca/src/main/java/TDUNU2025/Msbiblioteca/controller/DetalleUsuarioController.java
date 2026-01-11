package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;
import TDUNU2025.Msbiblioteca.service.DetalleUsuarioService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase; 

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
        return ResponseEntity.ok(new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_EXITO,
                service.listarTodo()
        ));
    }

    @GetMapping(ApiRoutes.DetalleUsuario.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<DetalleUsuarioResponse>> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_EXITO,
                service.obtenerPorIdUsuario(id)
        ));
    }

    @PostMapping(ApiRoutes.DetalleUsuario.GUARDAR)
    public ResponseEntity<ResponseBase<DetalleUsuarioResponse>> guardar(@RequestBody DetalleUsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_GUARDADO,
                service.guardar(request)
        ));
    }

    @DeleteMapping(ApiRoutes.DetalleUsuario.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_ELIMINADO,
                null
        ));
    }
}