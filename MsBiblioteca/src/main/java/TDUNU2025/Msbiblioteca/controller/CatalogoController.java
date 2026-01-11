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

    @GetMapping(ApiRoutes.Catalogo.LISTAR)
    public ResponseEntity<ResponseBase<List<CatalogoResponse>>> listarCatalogos() {
        return ResponseEntity.ok(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, catalogoService.listarCatalogos())
        );
    }

    @GetMapping(ApiRoutes.Catalogo.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<CatalogoResponse>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, catalogoService.obtenerCatalogoPorId(id))
        );
    }

    @PostMapping(ApiRoutes.Catalogo.GUARDAR)
    public ResponseEntity<ResponseBase<CatalogoResponse>> guardar(@RequestBody CatalogoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, catalogoService.guardarCatalogo(request))
        );
    }

    @PutMapping(ApiRoutes.Catalogo.ACTUALIZAR)
    public ResponseEntity<ResponseBase<CatalogoResponse>> actualizar(
            @PathVariable Integer id,
            @RequestBody CatalogoRequest request) {
            
        return ResponseEntity.ok(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ACTUALIZADO, catalogoService.actualizarCatalogo(id, request))
        );
    }

    @DeleteMapping(ApiRoutes.Catalogo.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Integer id) {
        catalogoService.eliminarCatalogo(id);
        return ResponseEntity.ok(
                new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_ELIMINADO, null)
        );
    }
}