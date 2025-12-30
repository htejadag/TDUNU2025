package Ms_Reingresante.Ms_Reingresante.repository;

 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ms_Reingresante.Ms_Reingresante.model.entity.ProcesoReingresoModel;


@Repository
public interface ProcesoReingresoRepository extends JpaRepository<ProcesoReingresoModel, Integer>{

}
