package com.MsExamen.controller;

import com.MsExamen.dto.ApiResponse;
import com.MsExamen.dto.ExamenDTO;
import com.MsExamen.dto.request.ExamenRequest;
import com.MsExamen.service.IExamenService;
import com.MsExamen.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.EXAMENES_ROUTE)
public class ExamenController {

    @Autowired
    private IExamenService examenService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExamenDTO>>> getAllExamenes() {
        List<ExamenDTO> examenes = examenService.getAllExamenes();
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.EXAMENES_OBTENIDOS, examenes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExamenDTO>> getExamenById(@PathVariable Integer id) {
        ExamenDTO examen = examenService.getExamenById(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.EXAMEN_OBTENIDO, examen));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ExamenDTO>> createExamen(@Valid @RequestBody ExamenRequest examenRequest) {
        ExamenDTO createdExamen = examenService.createExamen(examenRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(AppConstants.EXAMEN_CREADO, createdExamen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExamenDTO>> updateExamen(@PathVariable Integer id,
            @Valid @RequestBody ExamenRequest examenRequest) {
        ExamenDTO updatedExamen = examenService.updateExamen(id, examenRequest);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.EXAMEN_ACTUALIZADO, updatedExamen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExamen(@PathVariable Integer id) {
        examenService.deleteExamen(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.EXAMEN_ELIMINADO, null));
    }
}
