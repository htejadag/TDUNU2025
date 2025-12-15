package tdunu.MsSeguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdunu.MsSeguridad.model.entity.PermissionModel;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionModel, Long> {

    Optional<PermissionModel> findByNombre(String nombre);
}
