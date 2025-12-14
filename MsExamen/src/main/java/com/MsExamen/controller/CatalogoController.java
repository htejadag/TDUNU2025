package com.MsExamen.controller;

import com.MsExamen.dto.ApiResponse;
import com.MsExamen.dto.CatalogoDto;
import com.MsExamen.dto.request.CatalogoRequest;
import com.MsExamen.service.ICatalogoService;
import com.MsExamen.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.CATALOGOS_ROUTE)
public class CatalogoController {

    @Autowired
    private ICatalogoService catalogoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CatalogoDto>>> getAllCatalogos() {
        List<CatalogoDto> catalogos = catalogoService.getAllCatalogos();
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.CATALOGOS_OBTENIDOS, catalogos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CatalogoDto>> getCatalogoById(@PathVariable Integer id) {
        CatalogoDto catalogo = catalogoService.getCatalogoById(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.CATALOGO_OBTENIDO, catalogo));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CatalogoDto>> createCatalogo(
            @Valid @RequestBody CatalogoRequest catalogoRequest) {
        CatalogoDto createdCatalogo = catalogoService.createCatalogo(catalogoRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(AppConstants.CATALOGO_CREADO, createdCatalogo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CatalogoDto>> updateCatalogo(@PathVariable Integer id,
            @Valid @RequestBody CatalogoRequest catalogoRequest) {
        CatalogoDto updatedCatalogo = catalogoService.updateCatalogo(id, catalogoRequest);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.CATALOGO_ACTUALIZADO, updatedCatalogo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCatalogo(@PathVariable Integer id) {
        catalogoService.deleteCatalogo(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.CATALOGO_ELIMINADO, null));
    }
}
