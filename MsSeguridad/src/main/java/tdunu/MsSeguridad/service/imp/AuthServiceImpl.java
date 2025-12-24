package tdunu.MsSeguridad.service.imp;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tdunu.MsSeguridad.model.entity.PermissionModel;
import tdunu.MsSeguridad.model.entity.RoleModel;
import tdunu.MsSeguridad.model.entity.UsuarioModel;
import tdunu.MsSeguridad.model.request.LoginRequest;
import tdunu.MsSeguridad.model.response.LoginResponse;
import tdunu.MsSeguridad.repository.UsuarioRepository;
import tdunu.MsSeguridad.service.AuthService;
import tdunu.MsSeguridad.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {

        UsuarioModel user = usuarioRepository
                .findByCodUsuario(request.getCodUsuario())
                .or(() -> usuarioRepository.findByCorreo(request.getCodUsuario()))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getEstado() == 0) {
            throw new RuntimeException("Usuario desactivado");
        }

        if (!passwordEncoder.matches(
                request.getContrasena(),
                user.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        List<String> roles = user.getRoles()
                .stream()
                .map(RoleModel::getNombre)
                .toList();

        List<String> permisos = user.getRoles()
                .stream()
                .flatMap(r -> r.getPermisos().stream())
                .map(PermissionModel::getNombre)
                .distinct()
                .toList();

        String token = jwtUtil.generarToken(
                user.getCodUsuario(),
                roles,
                permisos
        );

        LoginResponse resp = new LoginResponse();
        resp.setToken(token);
        resp.setUsuario(user.getCodUsuario());
        resp.setExpiracion("24h");

        return resp;
    }
}
