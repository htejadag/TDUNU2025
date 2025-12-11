package tdunu.MsPosgrado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsPosgrado.model.ExpedienteModel;

@Repository
public interface ExpedienteRepository extends JpaRepository<ExpedienteModel, Integer> {
}
