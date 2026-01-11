package tdunu.MsSeguridad.service.imp;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tdunu.MsSeguridad.model.entity.PermissionModel;
import tdunu.MsSeguridad.model.entity.RoleModel;
import tdunu.MsSeguridad.model.request.RoleRequest;
import tdunu.MsSeguridad.model.response.RoleResponse;
import tdunu.MsSeguridad.repository.PermissionRepository;
import tdunu.MsSeguridad.repository.RoleRepository;
import tdunu.MsSeguridad.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RedisTemplate<String, List<RoleResponse>> redisTemplate;

    private static final String KEY_ROLES = "catalogo:roles";

    @Override
    public RoleResponse crear(RoleRequest request) {
        if (roleRepository.existsByNombre(request.getNombre())) {
            throw new IllegalArgumentException("El rol ya existe");
        }
        RoleModel role = new RoleModel();
        role.setNombre(request.getNombre());
        role.setDescripcion(request.getDescripcion());
        RoleModel saved = roleRepository.save(role);
        if (Boolean.TRUE.equals(redisTemplate.hasKey(KEY_ROLES))) {
            redisTemplate.delete(KEY_ROLES);
        }
        return toResponse(saved);
    }

    @Override
    public RoleResponse actualizar(Long idRole, RoleRequest request) {
        RoleModel role = roleRepository.findById(idRole)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
        if (request.getNombre() != null) {
            role.setNombre(request.getNombre());
        }
        if (request.getDescripcion() != null) {
            role.setDescripcion(request.getDescripcion());
        }
        RoleModel updated = roleRepository.save(role);
        if (Boolean.TRUE.equals(redisTemplate.hasKey(KEY_ROLES))) {
            redisTemplate.delete(KEY_ROLES);
        }
        return toResponse(updated);
    }

    @Override
    public void eliminar(Long idRole) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(KEY_ROLES))) {
            redisTemplate.delete(KEY_ROLES);
        }
        roleRepository.deleteById(idRole);
    }

    @Override
    public List<RoleResponse> listar() {

        if (Boolean.TRUE.equals(redisTemplate.hasKey(KEY_ROLES))) {
            return (List<RoleResponse>) redisTemplate.opsForValue().get(KEY_ROLES);
        }

        List<RoleResponse> lista = roleRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

        redisTemplate.opsForValue().set(
                KEY_ROLES,
                lista,
                Duration.ofMinutes(10)
        );

        return lista;
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
