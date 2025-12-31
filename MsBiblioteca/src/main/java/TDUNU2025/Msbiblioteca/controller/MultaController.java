package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import TDUNU2025.Msbiblioteca.model.response.MultaResponse;
import TDUNU2025.Msbiblioteca.service.MultaService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.Multa.BASE)
@RequiredArgsConstructor
public class MultaController {

    private final MultaService multaService;

    @GetMapping(ApiRoutes.Multa.LISTAR)
    public ResponseEntity<ResponseBase<List<MultaResponse>>> listar() {
        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_EXITO,
                        multaService.listar()
                )
        );
    }

    @GetMapping(ApiRoutes.Multa.OBTENER_POR_ID)
    public ResponseEntity<ResponseBase<MultaResponse>> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_EXITO,
                        multaService.obtener(id)
                )
        );
    }

    @PostMapping(ApiRoutes.Multa.GUARDAR)
    public ResponseEntity<ResponseBase<MultaResponse>> registrar(@RequestBody MultaRequest request) {
        MultaResponse response = multaService.registrar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_GUARDADO,
                        response
                )
        );
    }


    @PutMapping(ApiRoutes.Multa.ACTUALIZAR)
    public ResponseEntity<ResponseBase<MultaResponse>> actualizar(
            @PathVariable Integer id,
            @RequestBody MultaRequest request) {

        MultaResponse response = multaService.actualizar(id, request);

        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_ACTUALIZADO,
                        response
                )
        );
    }

 
    @DeleteMapping(ApiRoutes.Multa.ELIMINAR)
    public ResponseEntity<ResponseBase<Void>> eliminar(@PathVariable Integer id) {
        multaService.eliminar(id);

        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        Mensaje.MENSAJE_ELIMINADO,
                        null
                )
        );
    }


    @PostMapping("/pagar/{id}")
    public ResponseEntity<ResponseBase<MultaResponse>> pagarMulta(@PathVariable Integer id) {

        MultaResponse response = multaService.registrarPago(id);

        return ResponseEntity.ok(
                new ResponseBase<>(
                        Mensaje.CODE_OK,
                        "Multa pagada correctamente",
                        response
                )
        );
    }
}