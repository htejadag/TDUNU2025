package tdunu.MsSeguridad.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdunu.MsSeguridad.model.entity.PermissionModel;
import tdunu.MsSeguridad.model.entity.RoleModel;
import tdunu.MsSeguridad.model.request.RoleRequest;
import tdunu.MsSeguridad.model.response.RoleResponse;
import tdunu.MsSeguridad.repository.PermissionRepository;
import tdunu.MsSeguridad.repository.RoleRepository;
import tdunu.MsSeguridad.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public RoleResponse crear(RoleRequest request) {
        if (roleRepository.existsByNombre(request.getNombre())) {
            throw new RuntimeException("El rol ya existe");
        }
        RoleModel role = new RoleModel();
        role.setNombre(request.getNombre());
        role.setDescripcion(request.getDescripcion());
        RoleModel saved = roleRepository.save(role);
        return toResponse(saved);
    }

    @Override
    public RoleResponse actualizar(Long idRole, RoleRequest request) {
        RoleModel role = roleRepository.findById(idRole)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        if (request.getNombre() != null) {
            role.setNombre(request.getNombre());
        }
        if (request.getDescripcion() != null) {
            role.setDescripcion(request.getDescripcion());
        }
        RoleModel updated = roleRepository.save(role);
        return toResponse(updated);
    }

    @Override
    public void eliminar(Long idRole) {
        roleRepository.deleteById(idRole);
    }

    @Override
    public List<RoleResponse> listar() {
        return roleRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public RoleResponse obtenerPorId(Long idRole) {
        RoleModel role = roleRepository.findById(idRole)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return toResponse(role);
    }

    @Override
    public RoleResponse agregarPermiso(Long idRole, Long idPermiso) {
        RoleModel role = roleRepository.findById(idRole)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        PermissionModel perm = permissionRepository.findById(idPermiso)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
        role.getPermisos().add(perm);
        RoleModel updated = roleRepository.save(role);
        return toResponse(updated);
    }

    @Override
    public RoleResponse quitarPermiso(Long idRole, Long idPermiso) {
        RoleModel role = roleRepository.findById(idRole)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        PermissionModel perm = permissionRepository.findById(idPermiso)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
        role.getPermisos().remove(perm);
        RoleModel updated = roleRepository.save(role);
        return toResponse(updated);
    }

    private RoleResponse toResponse(RoleModel role) {
        RoleResponse resp = new RoleResponse();
        resp.setIdRole(role.getIdRole());
        resp.setNombre(role.getNombre());
        resp.setDescripcion(role.getDescripcion());
        resp.setPermisos(role.getPermisos().stream().map(PermissionModel::getNombre).collect(Collectors.toSet()));
        return resp;
    }
}
