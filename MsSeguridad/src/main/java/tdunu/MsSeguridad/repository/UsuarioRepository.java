package tdunu.MsSeguridad.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tdunu.MsSeguridad.model.entity.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByCodUsuario(String codUsuario);

    List<UsuarioModel> findByEstado(Integer estado);

    boolean existsByCodUsuario(String codUsuario);

    Optional<UsuarioModel> findByCorreo(String correo);
}
