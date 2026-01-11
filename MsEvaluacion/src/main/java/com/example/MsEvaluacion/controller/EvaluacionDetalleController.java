package com.example.MsEvaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsEvaluacion.services.IEDetalleService; 
import com.example.MsEvaluacion.model.request.EDetalleResquest;
import com.example.MsEvaluacion.model.response.EDetalleResponse;
import com.example.MsEvaluacion.util.Routes;

@RestController
@RequestMapping(Routes.evaluacionDetalle.EVALUACIONDETALLE)
public class EvaluacionDetalleController {

    @Autowired
    private IEDetalleService eDetalleService; 

    @GetMapping(Routes.evaluacionDetalle.ELIMINAR)
    public ResponseEntity<Void> eliminar(@PathVariable String id) { 
        eDetalleService.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(Routes.evaluacionDetalle.GUARDAR)
    public ResponseEntity<EDetalleResponse> guardar(@RequestBody EDetalleResquest eDetalleRequest) {
        return ResponseEntity.ok(eDetalleService.guardar(eDetalleRequest));
    }

    @GetMapping(Routes.evaluacionDetalle.LISTAR)
    public ResponseEntity<List<EDetalleResponse>> listar() {
        return ResponseEntity.ok(eDetalleService.listar());
    }

    @GetMapping(Routes.evaluacionDetalle.OBTENER_POR_ID)
    public ResponseEntity<EDetalleResponse> obtenerPorId(@PathVariable String id) {
        EDetalleResponse response = eDetalleService.obtenerPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(Routes.evaluacionDetalle.LISTAR_POR_EVALUACION)
    public ResponseEntity<List<EDetalleResponse>> listarPorEvaluacion(@PathVariable String id) {
        return ResponseEntity.ok(eDetalleService.listarPorEvaluacion(id));
    }
}
