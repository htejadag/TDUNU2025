package tdunu.MsCatalogo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdunu.MsCatalogo.model.request.CatalogoRequest;
import tdunu.MsCatalogo.model.response.CatalogoResponse;
import tdunu.MsCatalogo.service.CatalogoService;
import tdunu.MsCatalogo.util.ApiRoutes;
import tdunu.MsCatalogo.util.ResponseBase;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Catalogo.BASE)
@Tag(name = "Catálogo", description = "API de Catálogos con Redis Cache y Kafka")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping(ApiRoutes.Catalogo.LISTAR)
    @Operation(summary = "Listar todos los catálogos (con cache Redis)")
    public ResponseBase<List<CatalogoResponse>> listar() {
        List<CatalogoResponse> lista = catalogoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Catalogo.OBTENER_POR_ID)
    @Operation(summary = "Obtener catálogo por ID (con cache Redis)")
    public ResponseBase<CatalogoResponse> obtenerPorId(@RequestParam Integer id) {
        CatalogoResponse response = catalogoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(ApiRoutes.Catalogo.GUARDAR)
    @Operation(summary = "Guardar nuevo catálogo (limpia cache + envía Kafka)")
    public ResponseBase<CatalogoResponse> guardar(@RequestBody CatalogoRequest request) {
        CatalogoResponse response = catalogoService.guardar(request);
        return ResponseBase.ok("Catálogo creado correctamente", response);
    }

    @PutMapping(ApiRoutes.Catalogo.ACTUALIZAR)
    @Operation(summary = "Actualizar catálogo (limpia cache + envía Kafka)")
    public ResponseBase<CatalogoResponse> actualizar(@RequestParam Integer id, @RequestBody CatalogoRequest request) {
        CatalogoResponse response = catalogoService.actualizar(id, request);
        return ResponseBase.ok("Catálogo actualizado correctamente", response);
    }

    @DeleteMapping(ApiRoutes.Catalogo.ELIMINAR)
    @Operation(summary = "Eliminar catálogo (limpia cache + envía Kafka)")
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        catalogoService.eliminar(id);
        return ResponseBase.ok("Catálogo eliminado correctamente", null);
    }

    @GetMapping(ApiRoutes.Catalogo.BUSCAR_POR_TIPO)
    @Operation(summary = "Buscar catálogos por tipo (con cache Redis)")
    public ResponseBase<List<CatalogoResponse>> buscarPorTipo(@RequestParam Integer tipoCatalogoId) {
        List<CatalogoResponse> lista = catalogoService.buscarPorTipo(tipoCatalogoId);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.Catalogo.BUSCAR_POR_CODIGO)
    @Operation(summary = "Buscar catálogo por tipo y código (con cache Redis)")
    public ResponseBase<CatalogoResponse> buscarPorCodigo(
            @RequestParam Integer tipoCatalogoId, 
            @RequestParam String codigo) {
        CatalogoResponse response = catalogoService.buscarPorCodigo(tipoCatalogoId, codigo);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(ApiRoutes.Catalogo.LIMPIAR_CACHE)
    @Operation(summary = "Limpiar todo el cache de catálogos manualmente")
    public ResponseBase<Void> limpiarCache() {
        catalogoService.limpiarCache();
        return ResponseBase.ok("Cache de catálogos limpiado correctamente", null);
    }
}
