package tdunu.MsSeguridad.util;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generarToken(String username,
            List<String> roles,
            List<String> permisos) {

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .claim("permisos", permisos)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
    }

    public String obtenerUsuario(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<GrantedAuthority> getAuthorities(String token) {

        Claims claims = getClaims(token);

        List<String> roles = claims.get("roles", List.class);
        List<String> permisos = claims.get("permisos", List.class);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (roles != null) {
            roles.forEach(r
                    -> authorities.add(new SimpleGrantedAuthority("ROLE_" + r))
            );
        }

        if (permisos != null) {
            permisos.forEach(p
                    -> authorities.add(new SimpleGrantedAuthority(p))
            );
        }

        return authorities;
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
