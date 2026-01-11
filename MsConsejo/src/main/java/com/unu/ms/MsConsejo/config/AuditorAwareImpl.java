package com.unu.ms.MsConsejo.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementación de AuditorAware para proporcionar el usuario actual
 * que realiza las operaciones en las entidades auditadas.
 * 
 * IMPORTANTE: Por ahora retorna un ID de usuario fijo (1) como ejemplo.
 * Cuando implementes autenticación (JWT, Spring Security, etc.), 
 * deberás modificar este método para obtener el ID del usuario autenticado.
 */
@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        // TODO: Cuando implementes Spring Security o JWT:
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // if (authentication == null || !authentication.isAuthenticated()) {
        //     return Optional.empty();
        // }
        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // return Optional.of(userDetails.getUserId());
        
        // Por ahora, retornamos un ID de usuario fijo (1)
        // Puedes cambiarlo por null si prefieres dejarlo vacío hasta implementar seguridad
        return Optional.of(1);
    }
}
