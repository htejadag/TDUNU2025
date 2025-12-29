package com.unu.TDUNU2025.Msbiblioteca.controller;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import com.unu.TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;
import com.unu.TDUNU2025.Msbiblioteca.service.DetalleUsuarioService;
import com.unu.TDUNU2025.Msbiblioteca.util.ApiRoutes;
import com.unu.TDUNU2025.Msbiblioteca.util.Mensaje;
import com.unu.TDUNU2025.Msbiblioteca.util.ResponseBase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/detalle-usuario")
public class DetalleUsuarioController {

    @Autowired
    private DetalleUsuarioService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ResponseBase<List<DetalleUsuarioResponse>>> listar() {
        List<DetalleUsuarioResponse> lista = service.listarTodo().stream()
                .map(d -> modelMapper.map(d, DetalleUsuarioResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<DetalleUsuarioResponse>> obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(d -> ResponseEntity.ok(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_EXITO, modelMapper.map(d, DetalleUsuarioResponse.class))))
                .orElse(new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_ERROR, Mensaje.MENSAJE_NO_ENCONTRADO, null), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ResponseBase<DetalleUsuarioResponse>> guardar(@RequestBody DetalleUsuarioRequest request) {
        DetalleUsuario entity = modelMapper.map(request, DetalleUsuario.class);
        DetalleUsuario guardado = service.guardar(entity);
        DetalleUsuarioResponse res = modelMapper.map(guardado, DetalleUsuarioResponse.class);
        return new ResponseEntity<>(new ResponseBase<>(Mensaje.CODE_OK, Mensaje.MENSAJE_GUARDADO, res), HttpStatus.CREATED);
    }
}