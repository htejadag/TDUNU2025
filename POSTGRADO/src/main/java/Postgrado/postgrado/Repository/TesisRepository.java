package Postgrado.postgrado.Repository;

import Postgrado.postgrado.Model.Tesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesisRepository extends JpaRepository<Tesis, Integer> {
}
