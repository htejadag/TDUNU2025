package com.example.MsCursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsCursos.model.request.CursoRequest;
import com.example.MsCursos.model.response.CursoResponse;
import com.example.MsCursos.service.CursoService;
import com.example.MsCursos.util.ApiRoutes;
import com.example.MsCursos.util.ResponseBase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(ApiRoutes.Curso.BASE)
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping(value = ApiRoutes.Curso.LISTAR)
    public ResponseBase<List<CursoResponse>> listar() {
        List<CursoResponse> lista = cursoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.Curso.OBTENER_POR_ID)
    public ResponseBase<CursoResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        CursoResponse response = cursoService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.Curso.GUARDAR)
    public ResponseBase<CursoResponse> guardar(@RequestBody CursoRequest model) {
        CursoResponse response = cursoService.guardar(model);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.Curso.ELIMINAR)
    public CursoResponse eliminar(@RequestParam(value = "id") Integer id) {
        cursoService.eliminar(id);
        return null;
    }
}
