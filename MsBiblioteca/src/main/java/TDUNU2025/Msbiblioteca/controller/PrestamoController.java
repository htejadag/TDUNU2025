package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.PrestamoRequest;
import TDUNU2025.Msbiblioteca.model.response.PrestamoResponse;
import TDUNU2025.Msbiblioteca.service.PrestamoService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase; 

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Prestamo.BASE)
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;

    @GetMapping(ApiRoutes.Prestamo.LISTAR)
    public ResponseEntity<ResponseBase<List<PrestamoResponse>>> listar() {
        return ResponseEntity.ok(new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_EXITO,
                prestamoService.listarPrestamos()
        ));
    }

    @GetMapping(ApiRoutes.Prestamo.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<PrestamoResponse>> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_EXITO,
                prestamoService.obtenerPrestamoPorId(id)
        ));
    }

    @PostMapping(ApiRoutes.Prestamo.GUARDAR)
    public ResponseEntity<ResponseBase<PrestamoResponse>> guardar(@RequestBody PrestamoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_GUARDADO,
                prestamoService.guardarPrestamo(request)
        ));
    }

    @DeleteMapping(ApiRoutes.Prestamo.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Integer id) {
        prestamoService.eliminarPrestamo(id);
        return ResponseEntity.ok(new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_ELIMINADO,
                null
        ));
    }

    @PostMapping("/devolver/{id}") 
    public ResponseEntity<ResponseBase<PrestamoResponse>> devolverLibro(
            @PathVariable Integer id, 
            @RequestParam(required = false) String observaciones) {
            
        PrestamoResponse response = prestamoService.registrarDevolucion(id, observaciones);

        return ResponseEntity.ok(new ResponseBase<>(
                Mensaje.CODE_OK,
                "Libro devuelto correctamente",
                response
        ));
    }
}