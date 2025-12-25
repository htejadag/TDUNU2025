package tdunu.MsSeguridad.service;

import tdunu.MsSeguridad.model.request.RoleRequest;
import tdunu.MsSeguridad.model.response.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse crear(RoleRequest request);

    RoleResponse actualizar(Long idRole, RoleRequest request);

    void eliminar(Long idRole);

    List<RoleResponse> listar();

    RoleResponse obtenerPorId(Long idRole);

    // asignar y quitar permisos
    RoleResponse agregarPermiso(Long idRole, Long idPermiso);

    RoleResponse quitarPermiso(Long idRole, Long idPermiso);

}
