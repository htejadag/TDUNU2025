package Postgrado.postgrado.Repository;

import Postgrado.postgrado.Model.ExpedienteJurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpedienteJuradoRepository extends JpaRepository<ExpedienteJurado, Integer> {
}
