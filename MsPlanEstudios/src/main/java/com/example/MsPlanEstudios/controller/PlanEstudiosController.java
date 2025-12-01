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

import com.example.MsPlanEstudios.model.PlanEstudiosModel;
import com.example.MsPlanEstudios.service.PlanEstudiosService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/plan_estudios")
public class PlanEstudiosController {

    @PostConstruct
    public void init() {
        System.out.println(">>> CARGANDO EL CONTROLADOR REAL <<<"+ System.getProperty("user.dir"));
    }

    @Autowired
    PlanEstudiosService planestudiosService;

    // LISTAR
    @GetMapping(value = "/listar")
    public List<PlanEstudiosModel> listar() {
        return planestudiosService.listar();
    }

    // OBTENER
    @GetMapping(value = "/obtenerPorId")
    public PlanEstudiosModel obtenerPorId(@RequestParam(value = "id") Integer id) {
        return planestudiosService.obtenerPorId(id);
    }

    // GUARDAR
    @PostMapping(value = "/guardar")
    public PlanEstudiosModel guardar(@RequestBody PlanEstudiosModel model) {
        return planestudiosService.guardar(model);
    }

    // MODIFICAR
    @PutMapping(value = "/modificar", produces = "application/json")
    public PlanEstudiosModel modificar(@RequestParam(value ="id") Integer id, @RequestBody PlanEstudiosModel model) {
        model.setId(id); // asigna el id antes de actualizar
        return planestudiosService.guardar(model);
    }

    // ELMINAR
    @DeleteMapping(value = "/eliminar", produces = "application/json")
    public String eliminar(@RequestParam(value = "id") Integer id) {
        planestudiosService.eliminar(id);
        return "Eliminado correctamente";
    }

}
