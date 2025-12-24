package tdunu.MsSeguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdunu.MsSeguridad.model.entity.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    boolean existsByNombre(String nombre);
}
