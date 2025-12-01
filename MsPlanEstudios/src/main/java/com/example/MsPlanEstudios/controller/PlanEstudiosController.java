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
import com.example.MsPlanEstudios.model.request.PlanEstudiosRequest;
import com.example.MsPlanEstudios.model.response.PlanEstudiosResponse;
import com.example.MsPlanEstudios.service.PlanEstudiosService;
import com.example.MsPlanEstudios.util.ApiRoutes;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping(ApiRoutes.PlanEstudios.BASE)
public class PlanEstudiosController {

    @PostConstruct
    public void init() {
        System.out.println(">>> CARGANDO EL CONTROLADOR REAL <<<" + System.getProperty("user.dir"));
    }

    @GetMapping("/error-prueba")
    public String errorPrueba() {
        throw new RuntimeException("Excepción de prueba desde el controller");
    }

    @Autowired
    PlanEstudiosService planestudiosService;

    // LISTAR
    @GetMapping(value = ApiRoutes.PlanEstudios.LISTAR_PLAN)
    public List<PlanEstudiosResponse> listar() {
        /* 
        if(true){
            throw new RuntimeException("PROBANDOOOOOO");
        }
        */
        return planestudiosService.listar();
    }

    // OBTENER
    @GetMapping(value = ApiRoutes.PlanEstudios.OBTENER_POR_ID_PLAN)
    public PlanEstudiosResponse obtenerPorId(@RequestParam(value = "id") Integer id) {
        return planestudiosService.obtenerPorId(id);
    }

    // GUARDAR
    @PostMapping(value = ApiRoutes.PlanEstudios.GUARDAR_PLAN)
    public PlanEstudiosResponse guardar(@RequestBody PlanEstudiosRequest model) {
        return planestudiosService.guardar(model);
    }

    // MODIFICAR
    @PutMapping(value = ApiRoutes.PlanEstudios.MODIFICAR_PLAN, produces = "application/json")
    public PlanEstudiosResponse modificar(@RequestParam(value = "id") Integer id, @RequestBody PlanEstudiosRequest model) {
        return planestudiosService.modificar(id,model);
    }

    // ELMINAR
    @DeleteMapping(value = ApiRoutes.PlanEstudios.ELIMINAR_PLAN, produces = "application/json")
    public String eliminar(@RequestParam(value = "id") Integer id) {
        planestudiosService.eliminar(id);
        return "Eliminado correctamente";
    }

}
