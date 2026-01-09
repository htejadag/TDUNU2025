package tdunu.MsTitulacion.repository;

import tdunu.MsTitulacion.model.entity.ActaAprobacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ActaRepository extends JpaRepository<ActaAprobacion, Integer> {

    Optional<ActaAprobacion> findByCodigoActa(String codigoActa);
    Optional<ActaAprobacion> findByIdProyecto(Integer idProyecto);
}