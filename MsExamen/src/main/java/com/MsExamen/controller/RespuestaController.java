package com.MsExamen.controller;

import com.MsExamen.dto.ApiResponse;
import com.MsExamen.dto.RespuestaDTO;
import com.MsExamen.dto.request.RespuestaRequest;
import com.MsExamen.service.IRespuestaService;
import com.MsExamen.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.RESPUESTAS_ROUTE)
public class RespuestaController {

    @Autowired
    private IRespuestaService respuestaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RespuestaDTO>>> getAllRespuestas() {
        List<RespuestaDTO> respuestas = respuestaService.getAllRespuestas();
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.RESPUESTAS_OBTENIDAS, respuestas));
    }

    @GetMapping("/pregunta/{idPregunta}")
    public ResponseEntity<ApiResponse<List<RespuestaDTO>>> getRespuestasByPregunta(@PathVariable Integer idPregunta) {
        List<RespuestaDTO> respuestas = respuestaService.getRespuestasByPreguntaId(idPregunta);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.RESPUESTAS_OBTENIDAS, respuestas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RespuestaDTO>> getRespuestaById(@PathVariable Integer id) {
        RespuestaDTO respuesta = respuestaService.getRespuestaById(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.RESPUESTA_OBTENIDA, respuesta));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RespuestaDTO>> createRespuesta(
            @Valid @RequestBody RespuestaRequest respuestaRequest) {
        RespuestaDTO createdRespuesta = respuestaService.createRespuesta(respuestaRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(AppConstants.RESPUESTA_CREADA, createdRespuesta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RespuestaDTO>> updateRespuesta(@PathVariable Integer id,
            @Valid @RequestBody RespuestaRequest respuestaRequest) {
        RespuestaDTO updatedRespuesta = respuestaService.updateRespuesta(id, respuestaRequest);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.RESPUESTA_ACTUALIZADA, updatedRespuesta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRespuesta(@PathVariable Integer id) {
        respuestaService.deleteRespuesta(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstants.RESPUESTA_ELIMINADA, null));
    }
}
