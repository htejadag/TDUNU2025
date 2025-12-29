package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.entity.Prestamo;
import TDUNU2025.Msbiblioteca.model.request.PrestamoRequest;
import TDUNU2025.Msbiblioteca.model.response.PrestamoResponse;
import TDUNU2025.Msbiblioteca.service.PrestamoService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiRoutes.Prestamo.BASE)
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private ModelMapper modelMapper;

    // 1. LISTAR TODOS
    @GetMapping
    public ResponseEntity<ResponseBase<List<PrestamoResponse>>> listarPrestamos() {
        List<Prestamo> prestamos = prestamoService.listarPrestamos();
        
        List<PrestamoResponse> responseList = prestamos.stream()
                .map(prestamo -> modelMapper.map(prestamo, PrestamoResponse.class))
                .collect(Collectors.toList());

        ResponseBase<List<PrestamoResponse>> response = new ResponseBase<>(
            Mensaje.CODE_OK,
            Mensaje.MENSAJE_EXITO,
            responseList
        );

        return ResponseEntity.ok(response);
    }

    // 2. OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<PrestamoResponse>> obtenerPrestamoPorId(@PathVariable Integer id) {
        Optional<Prestamo> prestamoOpt = prestamoService.obtenerPrestamoPorId(id);

        if (prestamoOpt.isPresent()) {
            PrestamoResponse prestamoRes = modelMapper.map(prestamoOpt.get(), PrestamoResponse.class);
            
            ResponseBase<PrestamoResponse> response = new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_EXITO,
                prestamoRes
            );
            return ResponseEntity.ok(response);
        } else {
            ResponseBase<PrestamoResponse> response = new ResponseBase<>(
                Mensaje.CODE_ERROR,
                Mensaje.MENSAJE_NO_ENCONTRADO,
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // 3. GUARDAR (POST)
    @PostMapping
    public ResponseEntity<ResponseBase<PrestamoResponse>> guardarPrestamo(@RequestBody PrestamoRequest request) {
        // Mapeo Request -> Entidad
        Prestamo prestamoEntity = modelMapper.map(request, Prestamo.class);
        
        // Regla de negocio simple: Si no mandan fecha de préstamo, ponemos la actual
        if (prestamoEntity.getFechaPrestamo() == null) {
            prestamoEntity.setFechaPrestamo(LocalDate.now());
        }

        Prestamo prestamoGuardado = prestamoService.guardarPrestamo(prestamoEntity);
        
        // Mapeo Entidad -> Response
        PrestamoResponse prestamoRes = modelMapper.map(prestamoGuardado, PrestamoResponse.class);
        
        ResponseBase<PrestamoResponse> response = new ResponseBase<>(
            Mensaje.CODE_OK,
            Mensaje.MENSAJE_GUARDADO,
            prestamoRes
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 4. ACTUALIZAR (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<PrestamoResponse>> actualizarPrestamo(@PathVariable Integer id, @RequestBody PrestamoRequest request) {
        Optional<Prestamo> prestamoExistente = prestamoService.obtenerPrestamoPorId(id);

        if (prestamoExistente.isPresent()) {
            Prestamo prestamoEntity = modelMapper.map(request, Prestamo.class);
            prestamoEntity.setIdPrestamo(id); // Aseguramos el ID
            
            // Mantenemos la fecha original si no viene en el request
            if (prestamoEntity.getFechaPrestamo() == null) {
                prestamoEntity.setFechaPrestamo(prestamoExistente.get().getFechaPrestamo());
            }

            Prestamo prestamoActualizado = prestamoService.guardarPrestamo(prestamoEntity);
            
            PrestamoResponse prestamoRes = modelMapper.map(prestamoActualizado, PrestamoResponse.class);
            
            ResponseBase<PrestamoResponse> response = new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_ACTUALIZADO,
                prestamoRes
            );
            return ResponseEntity.ok(response);
        } else {
            ResponseBase<PrestamoResponse> response = new ResponseBase<>(
                Mensaje.CODE_ERROR,
                Mensaje.MENSAJE_NO_ENCONTRADO,
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // 5. ELIMINAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarPrestamo(@PathVariable Integer id) {
        Optional<Prestamo> prestamoExistente = prestamoService.obtenerPrestamoPorId(id);
        
        if (prestamoExistente.isPresent()) {
            prestamoService.eliminarPrestamo(id);
            
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