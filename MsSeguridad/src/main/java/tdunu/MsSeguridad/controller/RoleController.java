package tdunu.MsSeguridad.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tdunu.MsSeguridad.model.request.RoleRequest;
import tdunu.MsSeguridad.model.response.RoleResponse;
import tdunu.MsSeguridad.service.RoleService;
import tdunu.MsSeguridad.util.ApiRoutes;

@RestController
@RequestMapping(ApiRoutes.ROLE.BASE)
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @PostMapping(ApiRoutes.ROLE.GUARDAR)
    public ResponseEntity<RoleResponse> crear(@RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.crear(request));
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @PutMapping(ApiRoutes.ROLE.EDITAR + "/{idRole}")
    public ResponseEntity<RoleResponse> actualizar(@PathVariable Long idRole, @RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.actualizar(idRole, request));
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @DeleteMapping(ApiRoutes.ROLE.ELIMINAR + "/{idRole}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idRole) {
        roleService.eliminar(idRole);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @GetMapping(ApiRoutes.ROLE.LISTAR)
    public ResponseEntity<List<RoleResponse>> listar() {
        return ResponseEntity.ok(roleService.listar());
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @GetMapping("/{idRole}")
    public ResponseEntity<RoleResponse> obtener(@PathVariable Long idRole) {
        return ResponseEntity.ok(roleService.obtenerPorId(idRole));
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @PostMapping("/{idRole}" + ApiRoutes.ROLE.PERMISOS + "/{idPermiso}")
    public ResponseEntity<RoleResponse> agregarPermiso(
            @PathVariable Long idRole,
            @PathVariable Long idPermiso
    ) {
        return ResponseEntity.ok(roleService.agregarPermiso(idRole, idPermiso));
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @DeleteMapping("/{idRole}" + ApiRoutes.ROLE.PERMISOS + "/{idPermiso}")
    public ResponseEntity<RoleResponse> quitarPermiso(
            @PathVariable Long idRole,
            @PathVariable Long idPermiso
    ) {
        return ResponseEntity.ok(roleService.quitarPermiso(idRole, idPermiso));
    }
}
