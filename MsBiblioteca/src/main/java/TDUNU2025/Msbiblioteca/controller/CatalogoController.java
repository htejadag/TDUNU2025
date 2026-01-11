package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.CatalogoRequest;
import TDUNU2025.Msbiblioteca.model.response.CatalogoResponse;
import TDUNU2025.Msbiblioteca.service.CatalogoService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Catalogo.BASE)
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService service;

    @GetMapping(ApiRoutes.Catalogo.LISTAR)
    public ResponseEntity<ResponseBase<List<CatalogoResponse>>> listar() {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.listar()));
    }

    @GetMapping(ApiRoutes.Catalogo.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<CatalogoResponse>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, service.obtener(id)));
    }

    @PostMapping(ApiRoutes.Catalogo.GUARDAR)
    public ResponseEntity<ResponseBase<CatalogoResponse>> registrar(@RequestBody CatalogoRequest request) {
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, service.registrar(request)), HttpStatus.CREATED);
    }

    @PutMapping(ApiRoutes.Catalogo.ACTUALIZAR)
    public ResponseEntity<ResponseBase<CatalogoResponse>> actualizar(@PathVariable Long id, @RequestBody CatalogoRequest request) {
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, service.actualizar(id, request)));
    }

    @DeleteMapping(ApiRoutes.Catalogo.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null));
    }
}