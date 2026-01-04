package unu.td.MsAcademico.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import unu.td.MsAcademico.utils.Messages;
import unu.td.MsAcademico.utils.exceptions.UnauthorizedException;

import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Component
public class AuditorAwareImp implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String authHeader = getHeader();

        try {
            String token = authHeader.substring(7);
            String[] parte = token.split("\\.");
            if (parte.length < 2) {
                throw new UnauthorizedException(Messages.NOT_VALID_TOKEN);
            }

            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(parte[1]));

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> claims = mapper.readValue(payload, Map.class);
            Object idUsuario = claims.get("idUsuario");

            if (idUsuario == null) {
                throw new UnauthorizedException(Messages.NOT_VALID_TOKEN);
            }

            return Optional.of(idUsuario.toString());
        } catch (UnauthorizedException e) {
            throw e;
        } catch (Exception e) {
            throw new UnauthorizedException(Messages.NOT_VALID_TOKEN);
        }
    }

    private static String getHeader() {
        ServletRequestAttributes atributos = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (atributos == null) {
            throw new UnauthorizedException(Messages.NOT_AUTHENTICAED_USER);
        }

        HttpServletRequest request = atributos.getRequest();
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException(Messages.NOT_VALID_TOKEN);
        }
        return authHeader;
    }
}
