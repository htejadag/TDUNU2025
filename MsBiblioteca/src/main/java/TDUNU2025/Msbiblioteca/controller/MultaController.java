package TDUNU2025.Msbiblioteca.controller;

import TDUNU2025.Msbiblioteca.model.entity.Multa;
import TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import TDUNU2025.Msbiblioteca.model.response.MultaResponse;
import TDUNU2025.Msbiblioteca.service.MultaService;
import TDUNU2025.Msbiblioteca.util.ApiRoutes;
import TDUNU2025.Msbiblioteca.util.Mensaje;
import TDUNU2025.Msbiblioteca.util.ResponseBase;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiRoutes.Multa.BASE)
public class MultaController {

    @Autowired
    private MultaService multaService;

    @Autowired
    private ModelMapper modelMapper;

    // 1. LISTAR TODOS (GET /api/multa)
    @GetMapping
    public ResponseEntity<ResponseBase<List<MultaResponse>>> listarMultas() {
        List<Multa> lista = multaService.listar();

        List<MultaResponse> responseList = lista.stream()
                .map(m -> modelMapper.map(m, MultaResponse.class))
                .collect(Collectors.toList());

        ResponseBase<List<MultaResponse>> response = new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_EXITO,
                responseList
        );

        return ResponseEntity.ok(response);
    }

    // 2. OBTENER POR ID (GET /api/multa/{id})
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<MultaResponse>> obtenerMulta(@PathVariable Integer id) {
        Optional<Multa> multaOpt = multaService.obtener(id);

        if (multaOpt.isPresent()) {
            MultaResponse multaRes = modelMapper.map(multaOpt.get(), MultaResponse.class);

            ResponseBase<MultaResponse> response = new ResponseBase<>(
                    Mensaje.CODE_OK,
                    Mensaje.MENSAJE_EXITO,
                    multaRes
            );

            return ResponseEntity.ok(response);
        } else {
            ResponseBase<MultaResponse> response = new ResponseBase<>(
                    Mensaje.CODE_ERROR,
                    Mensaje.MENSAJE_NO_ENCONTRADO,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // 3. GUARDAR (POST /api/multa)
    @PostMapping
    public ResponseEntity<ResponseBase<MultaResponse>> registrarMulta(@RequestBody MultaRequest request) {

        Multa multaEntity = modelMapper.map(request, Multa.class);
        Multa multaGuardada = multaService.registrar(multaEntity);

        MultaResponse multaRes = modelMapper.map(multaGuardada, MultaResponse.class);

        ResponseBase<MultaResponse> response = new ResponseBase<>(
                Mensaje.CODE_OK,
                Mensaje.MENSAJE_GUARDADO,
                multaRes
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 4. ACTUALIZAR (PUT /api/multa/{id})
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<MultaResponse>> actualizarMulta(
            @PathVariable Integer id,
            @RequestBody MultaRequest request
    ) {

        Optional<Multa> multaExistente = multaService.obtener(id);

        if (multaExistente.isPresent()) {

            Multa multaEntity = modelMapper.map(request, Multa.class);
            multaEntity.setIdMulta(id);

            Multa multaActualizada = multaService.actualizar(multaEntity);

            MultaResponse multaRes = modelMapper.map(multaActualizada, MultaResponse.class);

            ResponseBase<MultaResponse> response = new ResponseBase<>(
                    Mensaje.CODE_OK,
                    Mensaje.MENSAJE_ACTUALIZADO,
                    multaRes
            );

            return ResponseEntity.ok(response);

        } else {
            ResponseBase<MultaResponse> response = new ResponseBase<>(
                    Mensaje.CODE_ERROR,
                    Mensaje.MENSAJE_NO_ENCONTRADO,
                    null
            );

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // 5. ELIMINAR (DELETE /api/multa/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarMulta(@PathVariable Integer id) {

        Optional<Multa> multaExistente = multaService.obtener(id);

        if (multaExistente.isPresent()) {
            multaService.eliminar(id);

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
