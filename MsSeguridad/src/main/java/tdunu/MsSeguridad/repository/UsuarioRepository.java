package tdunu.MsSeguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdunu.MsSeguridad.model.entity.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

}
