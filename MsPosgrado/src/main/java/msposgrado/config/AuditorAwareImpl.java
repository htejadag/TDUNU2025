package msposgrado.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Implementación de {@link AuditorAware} utilizada por Spring Data JPA
 * para obtener el usuario responsable de la creación o modificación
 * de una entidad.
 *
 * <p>
 * Esta clase es utilizada por el sistema de auditoría definido en
 * {@link msposgrado.Model.AuditoriaEntity}, permitiendo registrar
 * automáticamente los campos {@code creadoPor} y {@code modificadoPor}.
 * </p>
 *
 * <p>
 * Actualmente retorna un valor fijo ("SYSTEM") para fines de prueba.
 * En una futura implementación con Spring Security, este valor podrá
 * obtenerse desde el contexto de autenticación.
 * </p>
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * Obtiene el identificador del usuario actual que realiza la operación.
     *
     * @return {@link Optional} con el nombre del usuario actual.
     *         Por defecto retorna {@code "SYSTEM"}.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        // 🔹 Valor por defecto mientras no se implemente seguridad
        return Optional.of("SYSTEM");

        /*
         * 🔹 Implementación futura con Spring Security:
         *
         * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         * if (auth == null || !auth.isAuthenticated()) {
         *     return Optional.of("SYSTEM");
         * }
         * return Optional.of(auth.getName());
         */
    }
}