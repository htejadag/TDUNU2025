package Ms_Reingresante.Ms_Reingresante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Ms_Reingresante.Ms_Reingresante.model.entity.resolucionModel;




@Repository
public interface ResolucionRepository extends JpaRepository<resolucionModel, Integer> {

}

