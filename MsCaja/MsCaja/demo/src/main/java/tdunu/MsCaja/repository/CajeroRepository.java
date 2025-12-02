package tdunu.MsCaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdunu.MsCaja.model.entity.Cajero;

public interface CajeroRepository extends JpaRepository<Cajero, Integer> {
}