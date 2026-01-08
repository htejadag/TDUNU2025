package msposgrado.Repository;

import msposgrado.Model.ExpedienteJurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpedienteJuradoRepository extends JpaRepository<ExpedienteJurado, Integer> {
}
