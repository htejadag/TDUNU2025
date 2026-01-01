package tdunu.MsTitulacion.repository;

import tdunu.MsTitulacion.model.entity.DictamenPostgrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DictamenRepository extends JpaRepository<DictamenPostgrado, Integer> {
    
    Optional<DictamenPostgrado> findByIdBorrador(Integer idBorrador);
}
