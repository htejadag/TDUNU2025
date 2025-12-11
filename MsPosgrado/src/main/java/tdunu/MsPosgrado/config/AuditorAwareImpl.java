package tdunu.MsPosgrado.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Aquí puedes poner un usuario fijo (por ahora)
        return Optional.of("sistema");
    }
}
