package tdunu.MsSeguridad.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdunu.MsSeguridad.model.entity.PermissionModel;
import tdunu.MsSeguridad.model.request.PermissionRequest;
import tdunu.MsSeguridad.model.response.PermissionResponse;
import tdunu.MsSeguridad.repository.PermissionRepository;
import tdunu.MsSeguridad.service.PermissionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public PermissionResponse crear(PermissionRequest request) {
        PermissionModel permiso = new PermissionModel();
        permiso.setNombre(request.getNombre());
        permiso.setDescripcion(request.getDescripcion());
        PermissionModel saved = permissionRepository.save(permiso);
        return toResponse(saved);
    }

    @Override
    public PermissionResponse actualizar(Long idPermiso, PermissionRequest request) {
        PermissionModel permiso = permissionRepository.findById(idPermiso)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));

        if (request.getNombre() != null) {
            permiso.setNombre(request.getNombre());
        }
        if (request.getDescripcion() != null) {
            permiso.setDescripcion(request.getDescripcion());
        }

        PermissionModel updated = permissionRepository.save(permiso);
        return toResponse(updated);
    }

    @Override
    public void eliminar(Long idPermiso) {
        permissionRepository.deleteById(idPermiso);
    }

    @Override
    public List<PermissionResponse> listar() {
        return permissionRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PermissionResponse obtenerPorId(Long idPermiso) {
        PermissionModel permiso = permissionRepository.findById(idPermiso)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
        return toResponse(permiso);
    }

    private PermissionResponse toResponse(PermissionModel permiso) {
        PermissionResponse resp = new PermissionResponse();
        resp.setIdPermiso(permiso.getIdPermiso());
        resp.setNombre(permiso.getNombre());
        resp.setDescripcion(permiso.getDescripcion());
        return resp;
    }
}
