package Postgrado.postgrado.Repository;

import Postgrado.postgrado.Model.Jurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuradoRepository extends JpaRepository<Jurado, Integer> {
}
