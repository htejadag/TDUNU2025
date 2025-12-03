package tdunu.MsSeguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdunu.MsSeguridad.model.entity.UsuarioModel;
import java.util.Optional;
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findByCodUsuario(String codUsuario);
}


