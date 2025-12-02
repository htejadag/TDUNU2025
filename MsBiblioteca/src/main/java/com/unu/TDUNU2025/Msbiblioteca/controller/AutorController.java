package com.unu.TDUNU2025.Msbiblioteca.controller;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Autor;
import com.unu.TDUNU2025.Msbiblioteca.model.request.AutorRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.AutorResponse;
import com.unu.TDUNU2025.Msbiblioteca.service.AutorService;
import com.unu.TDUNU2025.Msbiblioteca.util.ApiRoutes;
import com.unu.TDUNU2025.Msbiblioteca.util.Mensaje;
import com.unu.TDUNU2025.Msbiblioteca.util.ResponseBase;
import org.modelmapper.ModelMapper; // Importamos ModelMapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiRoutes.RUTA_AUTOR)
public class AutorController {

    @Autowired
    private AutorService autorService;
    
    @Autowired
    private ModelMapper modelMapper; // INYECCIÓN DE MODELMAPPER

    // 1. LISTAR TODOS (GET /api/autor)
    @GetMapping
    public ResponseEntity<ResponseBase<List<AutorResponse>>> listarAutores() {
        List<Autor> autores = autorService.listarAutores();
        
        // Usamos ModelMapper para mapear la lista de Entidades a Response
        List<AutorResponse> responseList = autores.stream()
                .map(autor -> modelMapper.map(autor, AutorResponse.class))
                .collect(Collectors.toList());

        ResponseBase<List<AutorResponse>> response = new ResponseBase<>(
            Mensaje.CODE_OK,
            Mensaje.MENSAJE_EXITO,
            responseList
        );

        return ResponseEntity.ok(response);
    }

    // 2. OBTENER POR ID (GET /api/autor/{id})
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<AutorResponse>> obtenerAutorPorId(@PathVariable Integer id) {
        Optional<Autor> autorOpt = autorService.obtenerAutorPorId(id);

        if (autorOpt.isPresent()) {
            // Usamos ModelMapper
            AutorResponse autorRes = modelMapper.map(autorOpt.get(), AutorResponse.class);
            
            ResponseBase<AutorResponse> response = new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_EXITO,
                autorRes
            );
            return ResponseEntity.ok(response);
        } else {
            ResponseBase<AutorResponse> response = new ResponseBase<>(
                Mensaje.CODE_ERROR,
                Mensaje.MENSAJE_NO_ENCONTRADO,
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // 3. GUARDAR (POST /api/autor)
    @PostMapping
    public ResponseEntity<ResponseBase<AutorResponse>> guardarAutor(@RequestBody AutorRequest request) {
        // Usamos ModelMapper para mapear Request -> Entidad
        Autor autorEntity = modelMapper.map(request, Autor.class);
        
        Autor autorGuardado = autorService.guardarAutor(autorEntity);
        
        // Usamos ModelMapper para mapear Entidad Guardada -> Response
        AutorResponse autorRes = modelMapper.map(autorGuardado, AutorResponse.class);
        
        ResponseBase<AutorResponse> response = new ResponseBase<>(
            Mensaje.CODE_OK,
            Mensaje.MENSAJE_GUARDADO,
            autorRes
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 4. ACTUALIZAR (PUT /api/autor/{id})
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<AutorResponse>> actualizarAutor(@PathVariable Integer id, @RequestBody AutorRequest request) {
        Optional<Autor> autorExistente = autorService.obtenerAutorPorId(id);

        if (autorExistente.isPresent()) {
            // Usamos ModelMapper para mapear Request -> Entidad
            Autor autorEntity = modelMapper.map(request, Autor.class);
            autorEntity.setIdAutor(id); // Mantenemos el ID original
            
            Autor autorActualizado = autorService.guardarAutor(autorEntity);
            
            // Usamos ModelMapper
            AutorResponse autorRes = modelMapper.map(autorActualizado, AutorResponse.class);
            
            ResponseBase<AutorResponse> response = new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_ACTUALIZADO,
                autorRes
            );
            return ResponseEntity.ok(response);
        } else {
            ResponseBase<AutorResponse> response = new ResponseBase<>(
                Mensaje.CODE_ERROR,
                Mensaje.MENSAJE_NO_ENCONTRADO,
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // 5. ELIMINAR (DELETE /api/autor/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarAutor(@PathVariable Integer id) {
        Optional<Autor> autorExistente = autorService.obtenerAutorPorId(id);
        
        if (autorExistente.isPresent()) {
            autorService.eliminarAutor(id);
            
            ResponseBase<Void> response = new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_ELIMINADO,
                null
            );
            return ResponseEntity.ok(response);
        } else {
            ResponseBase<Void> response = new ResponseBase<>(
                Mensaje.CODE_ERROR,
                Mensaje.MENSAJE_NO_ENCONTRADO,
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
