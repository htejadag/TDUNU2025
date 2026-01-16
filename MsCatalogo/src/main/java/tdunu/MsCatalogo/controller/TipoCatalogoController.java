package tdunu.MsCatalogo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdunu.MsCatalogo.model.request.TipoCatalogoRequest;
import tdunu.MsCatalogo.model.response.TipoCatalogoResponse;
import tdunu.MsCatalogo.service.TipoCatalogoService;
import tdunu.MsCatalogo.util.ApiRoutes;
import tdunu.MsCatalogo.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.TipoCatalogo.BASE)
@Tag(name = "Tipo Catálogo", description = "API de Tipos de Catálogo")
public class TipoCatalogoController {

    @Autowired
    private TipoCatalogoService tipoCatalogoService;

    @GetMapping(ApiRoutes.TipoCatalogo.LISTAR)
    @Operation(summary = "Listar todos los tipos de catálogo (con cache Redis)")
    public ResponseBase<List<TipoCatalogoResponse>> listar() {
        List<TipoCatalogoResponse> lista = tipoCatalogoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.TipoCatalogo.OBTENER_POR_ID)
    @Operation(summary = "Obtener tipo de catálogo por ID (con cache Redis)")
    public ResponseBase<TipoCatalogoResponse> obtenerPorId(@RequestParam Integer id) {
        TipoCatalogoResponse response = tipoCatalogoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(ApiRoutes.TipoCatalogo.GUARDAR)
    @Operation(summary = "Guardar nuevo tipo de catálogo (limpia cache)")
    public ResponseBase<TipoCatalogoResponse> guardar(@RequestBody TipoCatalogoRequest request) {
        TipoCatalogoResponse response = tipoCatalogoService.guardar(request);
        return ResponseBase.ok("Tipo de catálogo creado correctamente", response);
    }

    @PutMapping(ApiRoutes.TipoCatalogo.ACTUALIZAR)
    @Operation(summary = "Actualizar tipo de catálogo (limpia cache)")
    public ResponseBase<TipoCatalogoResponse> actualizar(@RequestParam Integer id, @RequestBody TipoCatalogoRequest request) {
        TipoCatalogoResponse response = tipoCatalogoService.actualizar(id, request);
        return ResponseBase.ok("Tipo de catálogo actualizado correctamente", response);
    }

    @DeleteMapping(ApiRoutes.TipoCatalogo.ELIMINAR)
    @Operation(summary = "Eliminar tipo de catálogo (limpia cache)")
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        tipoCatalogoService.eliminar(id);
        return ResponseBase.ok("Tipo de catálogo eliminado correctamente", null);
    }
}
