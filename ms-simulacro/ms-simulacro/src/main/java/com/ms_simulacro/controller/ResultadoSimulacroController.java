package com.ms_simulacro.controller;

import com.ms_simulacro.dto.ApiResponse;
import com.ms_simulacro.dto.ResultadoSimulacroDto;
import com.ms_simulacro.service.ResultadoSimulacroService;
import com.ms_simulacro.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.API_PATH + "/resultados-simulacro")
@RequiredArgsConstructor
public class ResultadoSimulacroController {

    private final ResultadoSimulacroService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ResultadoSimulacroDto>>> getAll() {
        List<ResultadoSimulacroDto> list = service.getAll();
        return ResponseEntity.ok(ApiResponse.success(list, AppConstants.SUCCESS_MESSAGE));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResultadoSimulacroDto>> getById(@PathVariable Long id) {
        ResultadoSimulacroDto dto = service.getById(id);
        if (dto != null) {
            return ResponseEntity.ok(ApiResponse.success(dto, AppConstants.SUCCESS_MESSAGE));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(AppConstants.NOT_FOUND_MESSAGE));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ResultadoSimulacroDto>> create(@RequestBody ResultadoSimulacroDto dto) {
        ResultadoSimulacroDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(saved, AppConstants.CREATED_MESSAGE));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResultadoSimulacroDto>> update(@PathVariable Long id,
            @RequestBody ResultadoSimulacroDto dto) {
        ResultadoSimulacroDto updated = service.update(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(ApiResponse.success(updated, AppConstants.UPDATED_MESSAGE));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(AppConstants.NOT_FOUND_MESSAGE));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, AppConstants.DELETED_MESSAGE));
    }
}
