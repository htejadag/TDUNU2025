package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.AutorRequest;
import TDUNU2025.Msbiblioteca.model.response.AutorResponse;
import TDUNU2025.Msbiblioteca.service.AutorService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Autor.BASE) 
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping(ApiRoutes.Autor.LISTAR)
    public ResponseEntity<ResponseBase<List<AutorResponse>>> listarAutores() {
        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_EXITO,
                        autorService.listarAutores()
                )
        );
    }

    @GetMapping(ApiRoutes.Autor.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<AutorResponse>> obtenerAutorPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_EXITO,
                        autorService.obtenerAutorPorId(id)
                )
        );
    }

    @PostMapping(ApiRoutes.Autor.GUARDAR)
    public ResponseEntity<ResponseBase<AutorResponse>> guardarAutor(@RequestBody AutorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_GUARDADO,
                        autorService.guardarAutor(request)
                )
        );
    }

    @PutMapping(ApiRoutes.Autor.ACTUALIZAR)
    public ResponseEntity<ResponseBase<AutorResponse>> actualizarAutor(
            @PathVariable Integer id,
            @RequestBody AutorRequest request) {
            
        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_ACTUALIZADO,
                        autorService.actualizarAutor(id, request)
                )
        );
    }

    @DeleteMapping(ApiRoutes.Autor.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminarAutor(@PathVariable Integer id) {
        autorService.eliminarAutor(id);
        
        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_ELIMINADO,
                        null
                )
        );
    }
}