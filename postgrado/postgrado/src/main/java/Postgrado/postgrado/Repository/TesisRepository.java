package Postgrado.postgrado.Repository;

import Postgrado.postgrado.Model.Tesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TesisRepository extends JpaRepository<Tesis, Integer> {
    List<Tesis> findByActivoTrue();
}
