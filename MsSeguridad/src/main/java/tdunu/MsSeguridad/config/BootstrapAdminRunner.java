package tdunu.MsSeguridad.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tdunu.MsSeguridad.model.entity.RoleModel;
import tdunu.MsSeguridad.model.entity.UsuarioModel;
import tdunu.MsSeguridad.repository.RoleRepository;
import tdunu.MsSeguridad.repository.UsuarioRepository;

@Component
@RequiredArgsConstructor
public class BootstrapAdminRunner implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {

        if (usuarioRepository.existsByCodUsuario("0000000000")) {
            return;
        }

        RoleModel rolAdmin = roleRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("Rol ADMIN no existe"));

        UsuarioModel admin = new UsuarioModel();
        admin.setCodUsuario("0000000000");

        // 🔐 AHORA SÍ
        admin.setContrasena(passwordEncoder.encode("123456"));

        admin.setNombre("Administrador");
        admin.setApellido("Sistema");
        admin.setCorreo("admin@sistema.local");
        admin.setEstado(1);

        admin.getRoles().add(rolAdmin);

        usuarioRepository.save(admin);

        System.out.println("✅ ADMIN creado automáticamente con contraseña encriptada");
    }
}
