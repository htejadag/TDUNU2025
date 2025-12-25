package Ms_Reingresante.Ms_Reingresante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Ms_Reingresante.Ms_Reingresante.model.entity.SolicitudReingresoModel;

@Repository
public interface SolicitudReingresoRepository extends JpaRepository<SolicitudReingresoModel, Integer> {
    // Puedes agregar métodos custom aquí si los necesitas
    // Ejemplo: List<SolicitudReingresoModel> findByIdProceso(Integer idProceso);
}