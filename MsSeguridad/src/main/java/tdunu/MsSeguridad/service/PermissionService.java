package tdunu.MsSeguridad.service;

import tdunu.MsSeguridad.model.request.PermissionRequest;
import tdunu.MsSeguridad.model.response.PermissionResponse;

import java.util.List;

public interface PermissionService {

    PermissionResponse crear(PermissionRequest request);

    PermissionResponse actualizar(Long idPermiso, PermissionRequest request);

    void eliminar(Long idPermiso);

    List<PermissionResponse> listar();

    PermissionResponse obtenerPorId(Long idPermiso);
}
