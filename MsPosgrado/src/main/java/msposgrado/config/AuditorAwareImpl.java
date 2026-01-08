package msposgrado.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // 🔹 Por ahora testeando el valor por defecto, CAMBIAR DESPUÉS
        return Optional.of("SYSTEM");

        /*
        🔹 Más adelante, si implementamos Spring Security, podemos hacer esto, pero es mucha vaina xd ya veremos:
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return Optional.of("SYSTEM");
        }
        return Optional.of(auth.getName());
        */
    }
}
