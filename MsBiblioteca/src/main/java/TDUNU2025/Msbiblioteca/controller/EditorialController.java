package tdunu2025.msbiblioteca.controller;

import tdunu2025.msbiblioteca.model.request.EditorialRequest;
import tdunu2025.msbiblioteca.model.response.EditorialResponse;
import tdunu2025.msbiblioteca.service.EditorialService;
import tdunu2025.msbiblioteca.util.Mensaje;
import tdunu2025.msbiblioteca.util.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editorial") // Ajusta con ApiRoutes si lo tienes
@RequiredArgsConstructor
public class EditorialController {

    private final EditorialService service;

    @GetMapping
    public ResponseEntity<ResponseBase<List<EditorialResponse>>> listar() {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<EditorialResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseBase<EditorialResponse>> registrar(@RequestBody EditorialRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<EditorialResponse>> actualizar(@PathVariable Long id, @RequestBody EditorialRequest request) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}