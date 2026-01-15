package tdunu2025.msbiblioteca.controller;

import tdunu2025.msbiblioteca.model.request.EditorialRequest;
import tdunu2025.msbiblioteca.model.response.EditorialResponse;
import tdunu2025.msbiblioteca.service.EditorialService;
import tdunu2025.msbiblioteca.util.ApiRoutes; // Importación necesaria
import tdunu2025.msbiblioteca.util.Mensaje;
import tdunu2025.msbiblioteca.util.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Editorial.BASE) // <--- CORREGIDO: Usando la constante
@RequiredArgsConstructor
public class EditorialController {

    private final EditorialService service;

    @GetMapping(ApiRoutes.Editorial.LISTAR) // Sugerencia: Usa las constantes también aquí para consistencia
    public ResponseEntity<ResponseBase<List<EditorialResponse>>> listar() {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping(ApiRoutes.Editorial.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<EditorialResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping(ApiRoutes.Editorial.GUARDAR)
    public ResponseEntity<ResponseBase<EditorialResponse>> registrar(@RequestBody EditorialRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping(ApiRoutes.Editorial.ACTUALIZAR)
    public ResponseEntity<ResponseBase<EditorialResponse>> actualizar(@PathVariable Long id, @RequestBody EditorialRequest request) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping(ApiRoutes.Editorial.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}