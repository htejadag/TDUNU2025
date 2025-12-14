package com.MsExamen.controller;

import com.MsExamen.dto.ApiResponse;
import com.MsExamen.dto.CatalogoDetalleDto;
import com.MsExamen.dto.request.CatalogoDetalleRequest;
import com.MsExamen.service.ICatalogoDetalleService;
import com.MsExamen.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.CATALOGO_DETALLES_ROUTE)
public class CatalogoDetalleController {

    @Autowired
    private ICatalogoDetalleService catalogoDetalleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CatalogoDetalleDto>>> getAllCatalogoDetalles() {
        List<CatalogoDetalleDto> detalles = catalogoDetalleService.getAllCatalogoDetalles();
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.CATALOGO_DETALLES_OBTENIDOS, detalles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CatalogoDetalleDto>> getCatalogoDetalleById(@PathVariable Integer id) {
        CatalogoDetalleDto detalle = catalogoDetalleService.getCatalogoDetalleById(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.CATALOGO_DETALLE_OBTENIDO, detalle));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CatalogoDetalleDto>> createCatalogoDetalle(
            @Valid @RequestBody CatalogoDetalleRequest request) {
        CatalogoDetalleDto createdDetalle = catalogoDetalleService.createCatalogoDetalle(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(AppConstants.CATALOGO_DETALLE_CREADO, createdDetalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CatalogoDetalleDto>> updateCatalogoDetalle(@PathVariable Integer id,
            @Valid @RequestBody CatalogoDetalleRequest request) {
        CatalogoDetalleDto updatedDetalle = catalogoDetalleService.updateCatalogoDetalle(id, request);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.CATALOGO_DETALLE_ACTUALIZADO, updatedDetalle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCatalogoDetalle(@PathVariable Integer id) {
        catalogoDetalleService.deleteCatalogoDetalle(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.CATALOGO_DETALLE_ELIMINADO, null));
    }
}
