package msposgrado.Repository;

import msposgrado.Model.Jurado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuradoRepository extends JpaRepository<Jurado, Integer> {
    List<Jurado> findByActivoTrue();
}
