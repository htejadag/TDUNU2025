package tdunu.MsSeguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdunu.MsSeguridad.model.request.PermissionRequest;
import tdunu.MsSeguridad.model.response.PermissionResponse;
import tdunu.MsSeguridad.service.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/permisos")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionResponse> crear(@RequestBody PermissionRequest request) {
        return ResponseEntity.ok(permissionService.crear(request));
    }

    @PutMapping("/{idPermiso}")
    public ResponseEntity<PermissionResponse> actualizar(
            @PathVariable Long idPermiso,
            @RequestBody PermissionRequest request
    ) {
        return ResponseEntity.ok(permissionService.actualizar(idPermiso, request));
    }

    @DeleteMapping("/{idPermiso}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idPermiso) {
        permissionService.eliminar(idPermiso);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponse>> listar() {
        return ResponseEntity.ok(permissionService.listar());
    }

    @GetMapping("/{idPermiso}")
    public ResponseEntity<PermissionResponse> obtener(@PathVariable Long idPermiso) {
        return ResponseEntity.ok(permissionService.obtenerPorId(idPermiso));
    }
}
