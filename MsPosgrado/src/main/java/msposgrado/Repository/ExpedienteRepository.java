package msposgrado.Repository;

import msposgrado.Model.Expediente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Integer> {
    List<Expediente> findByActivoTrue();
}
