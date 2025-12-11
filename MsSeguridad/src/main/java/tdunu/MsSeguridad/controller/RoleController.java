package tdunu.MsSeguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdunu.MsSeguridad.model.request.RoleRequest;
import tdunu.MsSeguridad.model.response.RoleResponse;
import tdunu.MsSeguridad.service.RoleService;
import tdunu.MsSeguridad.util.ApiRoutes;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.ROLE.BASE)
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping(ApiRoutes.ROLE.GUARDAR)
    public ResponseEntity<RoleResponse> crear(@RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.crear(request));
    }

    @PutMapping(ApiRoutes.ROLE.EDITAR + "/{idRole}")
    public ResponseEntity<RoleResponse> actualizar(@PathVariable Long idRole, @RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.actualizar(idRole, request));
    }

    @DeleteMapping(ApiRoutes.ROLE.ELIMINAR + "/{idRole}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idRole) {
        roleService.eliminar(idRole);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(ApiRoutes.ROLE.LISTAR)
    public ResponseEntity<List<RoleResponse>> listar() {
        return ResponseEntity.ok(roleService.listar());
    }

    @GetMapping("/{idRole}")
    public ResponseEntity<RoleResponse> obtener(@PathVariable Long idRole) {
        return ResponseEntity.ok(roleService.obtenerPorId(idRole));
    }

    @PostMapping("/{idRole}" + ApiRoutes.ROLE.PERMISOS + "/{idPermiso}")
    public ResponseEntity<RoleResponse> agregarPermiso(
            @PathVariable Long idRole,
            @PathVariable Long idPermiso
    ) {
        return ResponseEntity.ok(roleService.agregarPermiso(idRole, idPermiso));
    }

    @DeleteMapping("/{idRole}" + ApiRoutes.ROLE.PERMISOS + "/{idPermiso}")
    public ResponseEntity<RoleResponse> quitarPermiso(
            @PathVariable Long idRole,
            @PathVariable Long idPermiso
    ) {
        return ResponseEntity.ok(roleService.quitarPermiso(idRole, idPermiso));
    }
}
