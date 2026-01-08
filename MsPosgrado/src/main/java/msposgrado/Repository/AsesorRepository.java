package msposgrado.Repository;

import msposgrado.Model.Asesor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsesorRepository extends JpaRepository<Asesor, Integer> {
    // Esto genera automáticamente el SQL: SELECT * FROM asesor WHERE activo = true || lo usaremos para mostrar solo los activos(borrado lógico)
    List<Asesor> findByActivoTrue();
}
