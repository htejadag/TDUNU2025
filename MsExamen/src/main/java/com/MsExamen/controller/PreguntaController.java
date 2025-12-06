package com.MsExamen.controller;

import com.MsExamen.dto.ApiResponse;
import com.MsExamen.dto.PreguntaDTO;
import com.MsExamen.dto.request.PreguntaRequest;
import com.MsExamen.service.IPreguntaService;
import com.MsExamen.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.PREGUNTAS_ROUTE)
public class PreguntaController {

    @Autowired
    private IPreguntaService preguntaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PreguntaDTO>>> getAllPreguntas() {
        List<PreguntaDTO> preguntas = preguntaService.getAllPreguntas();
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.PREGUNTAS_OBTENIDAS, preguntas));
    }

    @GetMapping("/examen/{idExamen}")
    public ResponseEntity<ApiResponse<List<PreguntaDTO>>> getPreguntasByExamen(@PathVariable Integer idExamen) {
        List<PreguntaDTO> preguntas = preguntaService.getPreguntasByExamenId(idExamen);
        // Note: keeping "Preguntas del examen obtenidas exitosamente" as is or adding a
        // constant if desired, defaulting to text for now or adding constant
        // Let's use PREGUNTAS_OBTENIDAS for now or create a new one. The user asked for
        // Constants. I'll stick to PREGUNTAS_OBTENIDAS to be safe or just use the
        // string if it's specific.
        // Actually, let's use the generic success message or the specific one I
        // created? PREGUNTAS_OBTENIDAS works.
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.PREGUNTAS_OBTENIDAS, preguntas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PreguntaDTO>> getPreguntaById(@PathVariable Integer id) {
        PreguntaDTO pregunta = preguntaService.getPreguntaById(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.PREGUNTA_OBTENIDA, pregunta));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PreguntaDTO>> createPregunta(
            @Valid @RequestBody PreguntaRequest preguntaRequest) {
        PreguntaDTO createdPregunta = preguntaService.createPregunta(preguntaRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(AppConstants.PREGUNTA_CREADA, createdPregunta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PreguntaDTO>> updatePregunta(@PathVariable Integer id,
            @Valid @RequestBody PreguntaRequest preguntaRequest) {
        PreguntaDTO updatedPregunta = preguntaService.updatePregunta(id, preguntaRequest);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.PREGUNTA_ACTUALIZADA, updatedPregunta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePregunta(@PathVariable Integer id) {
        preguntaService.deletePregunta(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.PREGUNTA_ELIMINADA, null));
    }
}
