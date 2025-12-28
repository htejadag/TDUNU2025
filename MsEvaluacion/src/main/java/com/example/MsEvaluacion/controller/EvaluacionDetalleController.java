package com.example.MsEvaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsEvaluacion.model.entity.EvaluacionDetalleModel;
import com.example.MsEvaluacion.model.request.EDetalleResquest;
import com.example.MsEvaluacion.model.response.EDetalleResponse;
import com.example.MsEvaluacion.services.Imp.EDetalleServiceImpl;
import com.example.MsEvaluacion.util.Routes;

@RestController
@RequestMapping(Routes.evaluacionDetalle.EVALUACIONDETALLE)
public class EvaluacionDetalleController {

    @Autowired
    EDetalleServiceImpl eDetalleService;

    @GetMapping(Routes.evaluacionDetalle.ELIMINAR)
    public void eliminar(String id) {
        eDetalleService.eliminar(id);
    }

    @PostMapping(Routes.evaluacionDetalle.GUARDAR)
    public EDetalleResponse guardar(@RequestBody EDetalleResquest eDetalleRequest) {
        return eDetalleService.guardar(eDetalleRequest);
    }

    @GetMapping(Routes.evaluacionDetalle.LISTAR)
    public List<EDetalleResponse> listar() {
        return eDetalleService.listar();
    }

    @GetMapping(Routes.evaluacionDetalle.OBTENER_POR_ID)
    public EDetalleResponse obtenerPorId(@PathVariable String id) {
        return eDetalleService.obtenerPorId(id);
    }

    @GetMapping(Routes.evaluacionDetalle.LISTAR_POR_EVALUACION)
    public List<EvaluacionDetalleModel> listarPorEvaluacion(@PathVariable String idEvaluacion) {
        return eDetalleService.listarPorEvaluacion(idEvaluacion);
    }

}
