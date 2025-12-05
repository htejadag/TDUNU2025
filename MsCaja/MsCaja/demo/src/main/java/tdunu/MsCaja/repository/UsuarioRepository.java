package tdunu.MsCaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdunu.MsCaja.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}