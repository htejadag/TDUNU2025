package tdunu.MsSeguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tdunu.MsSeguridad.model.request.PermissionRequest;
import tdunu.MsSeguridad.model.response.PermissionResponse;
import tdunu.MsSeguridad.service.PermissionService;
import tdunu.MsSeguridad.util.ApiRoutes;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.PERMISO.BASE)
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @PostMapping(ApiRoutes.PERMISO.GUARDAR)
    public ResponseEntity<PermissionResponse> crear(@RequestBody PermissionRequest request) {
        return ResponseEntity.ok(permissionService.crear(request));
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @PutMapping(ApiRoutes.PERMISO.EDITAR + "/{idPermiso}")
    public ResponseEntity<PermissionResponse> actualizar(
            @PathVariable Long idPermiso,
            @RequestBody PermissionRequest request
    ) {
        return ResponseEntity.ok(permissionService.actualizar(idPermiso, request));
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @DeleteMapping(ApiRoutes.PERMISO.ELIMINAR + "/{idPermiso}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idPermiso) {
        permissionService.eliminar(idPermiso);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @GetMapping(ApiRoutes.PERMISO.LISTAR)
    public ResponseEntity<List<PermissionResponse>> listar() {
        return ResponseEntity.ok(permissionService.listar());
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @GetMapping("/{idPermiso}")
    public ResponseEntity<PermissionResponse> obtener(@PathVariable Long idPermiso) {
        return ResponseEntity.ok(permissionService.obtenerPorId(idPermiso));
    }
}
