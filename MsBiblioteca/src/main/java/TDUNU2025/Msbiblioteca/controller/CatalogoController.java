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

    private final CatalogoService catalogoService;


    @GetMapping
    public ResponseEntity<ResponseBase<List<CatalogoResponse>>> listarCatalogos() {
        List<CatalogoResponse> lista = catalogoService.listarCatalogos();

        return ResponseEntity.ok(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, lista)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<CatalogoResponse>> obtenerPorId(@PathVariable Integer id) {

        CatalogoResponse response = catalogoService.obtenerCatalogoPorId(id);

        return ResponseEntity.ok(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, response)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseBase<CatalogoResponse>> guardar(@RequestBody CatalogoRequest request) {
        CatalogoResponse response = catalogoService.guardarCatalogo(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, response)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<CatalogoResponse>> actualizar(
            @PathVariable Integer id,
            @RequestBody CatalogoRequest request) {
            
        CatalogoResponse response = catalogoService.actualizarCatalogo(id, request);

        return ResponseEntity.ok(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, response)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Integer id) {
        catalogoService.eliminarCatalogo(id);

        return ResponseEntity.ok(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null)
        );
    }
}