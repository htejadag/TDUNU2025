package tdunu.MsSeguridad.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tdunu.MsSeguridad.model.entity.PermissionModel;
import tdunu.MsSeguridad.model.entity.RoleModel;
import tdunu.MsSeguridad.model.entity.UsuarioModel;
import tdunu.MsSeguridad.model.request.UsuarioRequest;
import tdunu.MsSeguridad.model.response.UsuarioResponse;
import tdunu.MsSeguridad.repository.RoleRepository;
import tdunu.MsSeguridad.repository.UsuarioRepository;
import tdunu.MsSeguridad.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceimpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private void validarDatos(UsuarioRequest request, boolean esNuevo, UsuarioModel existente) {

        // Validar código de usuario
        if (esNuevo) {
            if (usuarioRepository.existsByCodUsuario(request.getCodUsuario())) {
                throw new RuntimeException("El código de usuario ya existe. Debe ser único.");
            }
        } else if (request.getCodUsuario() != null
                && !request.getCodUsuario().equals(existente.getCodUsuario())) {

            if (usuarioRepository.existsByCodUsuario(request.getCodUsuario())) {
                throw new RuntimeException("El nuevo código ya existe. Debe ser único.");
            }
        }

        // Validar correo solo si lo envía
        if (request.getCorreo() != null) {
            if (!request.getCorreo().matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
                throw new RuntimeException("El correo debe ser un Gmail válido: ejemplo@gmail.com");
            }
        }

        // Validar celular solo si lo envía
        if (request.getCelular() != null) {
            if (!request.getCelular().matches("^9\\d{8}$")) {
                throw new RuntimeException("El número de celular debe tener 9 dígitos y empezar con 9.");
            }
        }
    }

    @Override
    public UsuarioResponse guardar(UsuarioRequest request) {
        validarDatos(request, true, null);
        UsuarioModel user = new UsuarioModel();
        user.setCodUsuario(request.getCodUsuario());
        user.setContrasena(
                passwordEncoder.encode(request.getContrasena()));
        user.setEstado(1);
        user.setNombre(request.getNombre());
        user.setApellido(request.getApellido());
        user.setCorreo(request.getCorreo());
        user.setCelular(request.getCelular());
        UsuarioModel saved = usuarioRepository.save(user);
        return toResponse(saved);
    }

    @Override
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse obtenerPorId(Long idUsuario) {
        UsuarioModel user = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponse(user);
    }

    @Override
    public UsuarioResponse obtenerPorCodigo(String codUsuario) {
        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponse(user);
    }

    @Override
    public UsuarioResponse editar(String codUsuario, UsuarioRequest request) {

        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        validarDatos(request, false, user);

        if (request.getCodUsuario() != null) {
            user.setCodUsuario(request.getCodUsuario());
        }
        if (request.getContrasena() != null) {
            user.setContrasena(
                    passwordEncoder.encode(request.getContrasena())
            );
        }
        if (request.getEstado() != null) {
            user.setEstado(request.getEstado());
        }
        if (request.getNombre() != null) {
            user.setNombre(request.getNombre());
        }
        if (request.getApellido() != null) {
            user.setApellido(request.getApellido());
        }
        if (request.getCorreo() != null) {
            user.setCorreo(request.getCorreo());
        }
        if (request.getCelular() != null) {
            user.setCelular(request.getCelular());
        }

        UsuarioModel updated = usuarioRepository.save(user);
        return toResponse(updated);
    }

    @Override
    public UsuarioResponse eliminarPorCodigo(String codUsuario) {

        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setEstado(0);

        UsuarioModel updated = usuarioRepository.save(user);

        return toResponse(updated);
    }

    @Override
    public UsuarioResponse cambiarEstado(String codUsuario) {

        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setEstado(user.getEstado() == 1 ? 0 : 1);

        UsuarioModel updated = usuarioRepository.save(user);

        return toResponse(updated);
    }

    @Override
    public List<UsuarioResponse> listarActivos() {
        return usuarioRepository.findByEstado(1)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponse> listarDesactivados() {
        return usuarioRepository.findByEstado(0)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private UsuarioResponse toResponse(UsuarioModel model) {
        UsuarioResponse resp = new UsuarioResponse();
        resp.setIdUsuario(model.getIdUsuario());
        resp.setCodUsuario(model.getCodUsuario());
        resp.setNombre(model.getNombre());
        resp.setApellido(model.getApellido());
        resp.setCorreo(model.getCorreo());
        resp.setCelular(model.getCelular());
        resp.setEstado(model.getEstado());

        resp.setRoles(
                model.getRoles().stream()
                        .map(RoleModel::getNombre)
                        .collect(Collectors.toSet())
        );
        resp.setPermisos(
                model.getRoles().stream()
                        .flatMap(role -> role.getPermisos().stream())
                        .map(PermissionModel::getNombre)
                        .collect(Collectors.toSet())
        );

        return resp;
    }
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UsuarioResponse asignarRole(String codUsuario, Long idRole) {

        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        RoleModel role = roleRepository.findById(idRole)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // Evita duplicado
        boolean yaTiene = user.getRoles().stream()
                .anyMatch(r -> r.getIdRole().equals(idRole));

        if (yaTiene) {
            throw new RuntimeException("El usuario ya tiene este rol");
        }

        user.getRoles().add(role);

        UsuarioModel updated = usuarioRepository.save(user);
        return toResponse(updated);
    }

    @Override
    @Transactional
    public UsuarioResponse quitarRole(String codUsuario, Long idRole) {

        UsuarioModel user = usuarioRepository.findByCodUsuario(codUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean removed = user.getRoles().removeIf(r -> r.getIdRole().equals(idRole));

        if (!removed) {
            throw new RuntimeException("El usuario no tenía ese rol asignado");
        }

        UsuarioModel updated = usuarioRepository.save(user);
        return toResponse(updated);
    }
}
