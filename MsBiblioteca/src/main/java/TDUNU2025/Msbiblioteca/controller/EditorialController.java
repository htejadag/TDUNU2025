package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.EditorialRequest;
import TDUNU2025.Msbiblioteca.model.response.EditorialResponse;
import TDUNU2025.Msbiblioteca.service.EditorialService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Editorial.BASE) // /api/editorial
@RequiredArgsConstructor
public class EditorialController {

    private final EditorialService service;

    @GetMapping(ApiRoutes.Editorial.LISTAR) // Usa "/listar"
    public ResponseEntity<ResponseBase<List<EditorialResponse>>> listar() {
        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_EXITO,
                        service.listar()
                )
        );
    }

    @GetMapping(ApiRoutes.Editorial.OBTENER_POR_ID) // Usa "/obtener/{id}"
    public ResponseEntity<ResponseBase<EditorialResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_EXITO,
                        service.buscarPorId(id)
                )
        );
    }

    @PostMapping(ApiRoutes.Editorial.GUARDAR) // Usa "/guardar"
    public ResponseEntity<ResponseBase<EditorialResponse>> guardar(@RequestBody EditorialRequest request) {
        EditorialResponse response = service.guardar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_GUARDADO,
                        response
                )
        );
    }

    @PutMapping(ApiRoutes.Editorial.ACTUALIZAR) // Usa "/actualizar/{id}"
    public ResponseEntity<ResponseBase<EditorialResponse>> actualizar(
            @PathVariable Long id,
            @RequestBody EditorialRequest request) {
            
        EditorialResponse response = service.actualizar(id, request);

        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_ACTUALIZADO,
                        response
                )
        );
    }

    @DeleteMapping(ApiRoutes.Editorial.ELIMINAR) // Usa "/eliminar/{id}"
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