package com.example.MsCursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MsCursos.model.request.CursoDetalleRequest;
import com.example.MsCursos.model.response.CursoDetalleResponse;
import com.example.MsCursos.util.ApiRoutes;
import com.example.MsCursos.util.ResponseBase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.example.MsCursos.service.CursoDetalleService;

@RestController
@RequestMapping(ApiRoutes.CursoDetalle.BASE)

public class CursoDetalleController {
    @Autowired
    CursoDetalleService cursoDetalleService;

    @GetMapping(value = ApiRoutes.CursoDetalle.LISTAR)
    public ResponseBase<List<CursoDetalleResponse>> listar() {
        List<CursoDetalleResponse> lista = cursoDetalleService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(value = ApiRoutes.CursoDetalle.OBTENER_POR_ID)
    public ResponseBase<CursoDetalleResponse> obtenerPorId(@RequestParam(value = "id") Integer id) {
        CursoDetalleResponse response = cursoDetalleService.obtenerPorId(id);
        return ResponseBase.ok(response);
    }

    @PostMapping(value = ApiRoutes.CursoDetalle.GUARDAR)
    public ResponseBase<CursoDetalleResponse> guardar(@RequestBody CursoDetalleRequest model) {
        CursoDetalleResponse response = cursoDetalleService.guardar(model);
        return ResponseBase.ok(response);
    }

    @DeleteMapping(value = ApiRoutes.CursoDetalle.ELIMINAR)
    public CursoDetalleResponse eliminar(@RequestParam(value = "id") Integer id) {
        cursoDetalleService.eliminar(id);
        return null;
    }

}
