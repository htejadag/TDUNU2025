package com.example.MsEvaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.MsEvaluacion.model.request.EvaluacionRequest;
import com.example.MsEvaluacion.model.response.EvaluacionResponse;
import com.example.MsEvaluacion.services.Imp.EvaluacionServiceImpl;
import com.example.MsEvaluacion.util.Routes;

@RestController
@RequestMapping(Routes.evaluacion.EVALUACION)
public class EvaluacionController {

    @Autowired
    EvaluacionServiceImpl evaluacionService;

    @GetMapping(Routes.evaluacion.LISTAR)
    public List<EvaluacionResponse> listar() {
        return evaluacionService.listar();
    }

    @GetMapping(Routes.evaluacion.OBTENER_POR_ID)
    public EvaluacionResponse obtenerPorId(@PathVariable String id) {
        return evaluacionService.obtenerPorId(id);
    }

    @PostMapping(Routes.evaluacion.GUARDAR)
    public EvaluacionResponse guardar(@RequestBody EvaluacionRequest evaluacion) {
        return evaluacionService.guardar(evaluacion);
    }

    
    public void eliminar(String id) {
        evaluacionService.eliminar(id);
    }

    
    
}
