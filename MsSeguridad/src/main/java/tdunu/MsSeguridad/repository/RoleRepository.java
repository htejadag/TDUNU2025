package tdunu.MsSeguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdunu.MsSeguridad.model.entity.RoleModel;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    Optional<RoleModel> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
