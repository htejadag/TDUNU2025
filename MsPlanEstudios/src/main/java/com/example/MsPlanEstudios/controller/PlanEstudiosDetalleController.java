package com.example.MsPlanEstudios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsPlanEstudios.model.request.PlanEstudiosDetalleRequest;
import com.example.MsPlanEstudios.model.request.PlanEstudiosRequest;
import com.example.MsPlanEstudios.model.response.PlanEstudiosDetalleResponse;
import com.example.MsPlanEstudios.model.response.PlanEstudiosResponse;
import com.example.MsPlanEstudios.service.PlanEstudiosDetalleService;
import com.example.MsPlanEstudios.service.PlanEstudiosService;
import com.example.MsPlanEstudios.util.ApiRoutes;

@RestController
@RequestMapping(ApiRoutes.PlanEstudios.BASE_DETALLE)
public class PlanEstudiosDetalleController {
    @Autowired
    PlanEstudiosDetalleService planestudiosdetalleService;

    // LISTAR
    @GetMapping(value = ApiRoutes.PlanEstudios.LISTAR_PLAN_DETALLE)
    public List<PlanEstudiosDetalleResponse> listar() {
        return planestudiosdetalleService.listar();
    }

    // OBTENER
    @GetMapping(value = ApiRoutes.PlanEstudios.OBTENER_POR_ID_PLAN_DETALLE)
    public PlanEstudiosDetalleResponse obtenerPorId(@RequestParam(value = "id") Integer id) {
        return planestudiosdetalleService.obtenerPorId(id);
    }

    // GUARDAR
    @PostMapping(value = ApiRoutes.PlanEstudios.GUARDAR_PLAN_DETALLE)
    public PlanEstudiosDetalleResponse guardar(@RequestBody PlanEstudiosDetalleRequest model) {
        return planestudiosdetalleService.guardar(model);
    }

    // MODIFICAR
    @PutMapping(value = ApiRoutes.PlanEstudios.MODIFICAR_PLAN_DETALLE, produces = "application/json")
    public PlanEstudiosDetalleResponse modificar(@RequestParam(value = "id") Integer id, @RequestBody PlanEstudiosDetalleRequest model) {
        return planestudiosdetalleService.modificar(id,model);
    }

    // ELMINAR
    @DeleteMapping(value = ApiRoutes.PlanEstudios.ELIMINAR_PLAN_DETALLE, produces = "application/json")
    public String eliminar(@RequestParam(value = "id") Integer id) {
        planestudiosdetalleService.eliminar(id);
        return "Eliminado correctamente";
    }
}
