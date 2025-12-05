package com.unu.ms.MsConsejo.controller;

import com.unu.ms.MsConsejo.model.request.MiembroConsejoRequest;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;
import com.unu.ms.MsConsejo.service.MiembroConsejoService;
import com.unu.ms.MsConsejo.util.ApiRoutes;
import com.unu.ms.MsConsejo.util.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.MiembroConsejo.BASE)
@Tag(name = "Miembro Consejo", description = "API para gestión de miembros del consejo")
public class MiembroConsejoController {

    @Autowired
    private MiembroConsejoService miembroConsejoService;

    @Operation(summary = "Listar miembros", description = "Obtiene la lista completa de miembros del consejo")
    @GetMapping(ApiRoutes.MiembroConsejo.LISTAR)
    public ResponseBase<List<MiembroConsejoResponse>> listar() {
        List<MiembroConsejoResponse> lista = miembroConsejoService.listar();
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.MiembroConsejo.OBTENER_POR_ID)
    public ResponseBase<MiembroConsejoResponse> obtenerPorId(@RequestParam Integer id) {
        MiembroConsejoResponse response = miembroConsejoService.obtenerPorId(id);
        if (response != null) {
            return ResponseBase.ok(response);
        }
        return ResponseBase.error("Miembro no encontrado");
    }

    @PostMapping(ApiRoutes.MiembroConsejo.GUARDAR)
    public ResponseBase<MiembroConsejoResponse> guardar(@RequestBody MiembroConsejoRequest request) {
        MiembroConsejoResponse response = miembroConsejoService.guardar(request);
        return ResponseBase.ok("Miembro creado exitosamente", response);
    }

    @PutMapping(ApiRoutes.MiembroConsejo.ACTUALIZAR)
    public ResponseBase<MiembroConsejoResponse> actualizar(@RequestBody MiembroConsejoRequest request) {
        try {
            MiembroConsejoResponse response = miembroConsejoService.actualizar(request);
            return ResponseBase.ok("Miembro actualizado exitosamente", response);
        } catch (IllegalArgumentException e) {
            return ResponseBase.error(e.getMessage());
        }
    }

    @DeleteMapping(ApiRoutes.MiembroConsejo.ELIMINAR)
    public ResponseBase<Void> eliminar(@RequestParam Integer id) {
        miembroConsejoService.eliminar(id);
        return ResponseBase.ok("Miembro eliminado exitosamente", null);
    }

    @GetMapping(ApiRoutes.MiembroConsejo.BUSCAR_POR_CONSEJO)
    public ResponseBase<List<MiembroConsejoResponse>> buscarPorConsejo(@RequestParam Integer idConsejo) {
        List<MiembroConsejoResponse> lista = miembroConsejoService.buscarPorConsejo(idConsejo);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.MiembroConsejo.BUSCAR_POR_PERSONA)
    public ResponseBase<List<MiembroConsejoResponse>> buscarPorPersona(@RequestParam Integer idPersona) {
        List<MiembroConsejoResponse> lista = miembroConsejoService.buscarPorPersona(idPersona);
        return ResponseBase.ok(lista);
    }

    @GetMapping(ApiRoutes.MiembroConsejo.BUSCAR_POR_CARGO)
    public ResponseBase<List<MiembroConsejoResponse>> buscarPorCargo(@RequestParam Integer idCargo) {
        List<MiembroConsejoResponse> lista = miembroConsejoService.buscarPorCargo(idCargo);
        return ResponseBase.ok(lista);
    }
}
